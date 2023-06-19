<template>
    <alertError @closeAlert="closeAlert" v-if="isAlertError" :feedbackMessage="feedbackMessage" />
    <alertSuccess @closeAlert="closeAlert" v-if="isAlertSuccess" />

    <section class="flex flex-col items-center mt-4">
        <h1 class="text-3xl text-white font-Sriracha" id="Formulario">Formulario</h1>
        <p class="w-full mx-6 mb-4 text-base text-center text-white font-PassionOne">Considere fazer um simples e rapido
            registro
            para obter um
            possivel
            reparo em seu
            equipamento.
        </p>
        <form class="flex flex-col items-center w-10/12 mb-5" @submit.stop.prevent="addClient()">

            <div class="flex flex-col items-center w-full">
                <label for="name" class="text-base text-white font-PassionOne">Nome</label>
                <input name="name" placeholder="Digite Seu Nome" min="4" max="32" autocomplete="off"
                    class="w-full max-w-sm px-1 mb-2 text-base text-white bg-gray-900 border rounded font-PassionOne border-neutral-400 placeholder:text-center placeholder:text-white placeholder:font-Passion"
                    type="text" v-model="name" required>
            </div>

            <div class="flex flex-col items-center w-full">
                <label for="phone" class="text-base text-white font-PassionOne">Telefone</label>
                <input name="phone" placeholder="(xx) xxxxx-xxxx" autocomplete="off"
                    class="w-full max-w-sm px-1 mb-2 text-base text-white bg-gray-900 border rounded font-PassionOne border-neutral-400 placeholder:text-center placeholder:text-white placeholder:font-Passion"
                    type="tel" v-model="phone" required>
            </div>

            <div class="flex flex-col items-center w-full">
                <label for="address" class="text-base text-white font-PassionOne">Endereço</label>
                <input name="address" placeholder="Rua Numero Bairro Cidade CEP" autocomplete="off"
                    class="w-full max-w-sm px-1 mb-2 text-base text-white bg-gray-900 border rounded font-PassionOne border-neutral-400 placeholder:text-center placeholder:text-white placeholder:font-Passion"
                    type="text" v-model="address" required>
            </div>

            <div class="flex flex-col items-center w-full">
                <label for="cpf" class="text-base text-white font-PassionOne">CPF</label>
                <input name="cpf" placeholder="xxx.xxx.xxx-xx" autocomplete="off"
                    class="w-full max-w-sm px-1 mb-2 text-base text-white bg-gray-900 border rounded font-PassionOne border-neutral-400 placeholder:text-center placeholder:text-white placeholder:font-Passion"
                    type="text" v-model="cpf" required>
            </div>

            <div class="flex flex-col items-center w-full">
                <label for="email" class="text-base text-white font-PassionOne">Email</label>
                <input name="email" placeholder="example@example.com"
                    class="w-full max-w-sm px-1 mb-2 text-base text-white bg-gray-900 border rounded font-PassionOne border-neutral-400 placeholder:text-center placeholder:text-white placeholder:font-Passion"
                    type="text" v-model="email" required autocomplete="off">
            </div>

            <label for="defect" class="text-base text-white font-PassionOne">Defeito</label>
            <textarea maxlength="255" autocomplete="off"
                class="w-full h-32 max-w-sm px-1 mb-2 text-base text-white bg-gray-900 border rounded resize-none font-PassionOne border-neutral-400 placeholder:text-center placeholder:text-white"
                name="defect" id="defect" cols="30" rows="10" placeholder="Descreva o Defeito Do Equipamento"
                v-model="defect" required>
            </textarea>

            <label class="text-base text-white font-PassionOne" for="defeito">Tipo Do Produto</label>
            <select v-model="product_type" required autocomplete="off"
                class="w-full max-w-sm mb-5 text-base text-center text-white bg-gray-900 border rounded font-PassionOne border-neutral-400"
                name="tipoProduto">
                <option value>Selecione um Tipo</option>
                <option v-for="product in store.state.products" autocomplete="off" :key="product.id"
                    :value="product.product_type" v-text="product.product_type"></option>
            </select>

            <input
                class="w-full max-w-sm p-1 text-base text-center text-white bg-gray-900 border rounded cursor-pointer font-PassionOne border-neutral-400"
                type="submit" name="btn-cadastrar" :value="isUpdateClient ? 'Atualizar' : 'Cadastrar'">
        </form>

        <router-link to="/clients"
            class="w-10/12 max-w-sm p-1 mb-5 text-base text-center text-white bg-gray-900 border rounded cursor-pointer font-PassionOne border-neutral-400">Ver
            Clientes</router-link>
    </section>
</template>

<script setup>
import { ref, defineProps } from "vue";
import { useStore } from "vuex";
import alertError from "../components/alerts/alertError.vue";
import alertSuccess from "../components/alerts/alertSuccess.vue";

const name = ref("");
const phone = ref("");
const address = ref("");
const cpf = ref("");
const email = ref("");
const defect = ref("");
const product_type = ref("");

const store = useStore();

const isUpdateClient = ref(false);
const isAlertError = ref(false);
const isAlertSuccess = ref(false);
const feedbackMessage = ref("");

const closeAlert = () => {
    isAlertError.value = false;
    isAlertSuccess.value = false;
}

const props = defineProps({
    id: {
        type: Number,
        required: true,
    },
});

store.dispatch("getProducts")

const addClient = () => {
    if (!name.value || !phone.value || !address.value || !cpf.value || !email.value || !defect.value || !product_type.value) {
        return;
    }

    if (isUpdateClient.value) {
        const payload = {
            id: props.id,
            data: {
                name: name.value,
                phone: phone.value,
                address: address.value,
                cpf: cpf.value,
                email: email.value,
                defect: defect.value,
                product_type: product_type.value,
            },
        }

        store.dispatch("updateClient", payload)
            .then(() => {
                isAlertError.value = false;
                isAlertSuccess.value = true;
            }).catch((err) => {
                isAlertError.value = true;
                feedbackMessage.value = err.message.data;
            }).finally(() => {
                name.value = "";
                phone.value = "";
                address.value = "";
                cpf.value = "";
                email.value = "";
                defect.value = "";
                product_type.value = "";
            });
        return;
    }

    store.dispatch("addClient", {
        name: name.value,
        phone: phone.value,
        address: address.value,
        cpf: cpf.value,
        email: email.value,
        defect: defect.value,
        product_type: product_type.value,
    }).then(() => {
        isAlertError.value = false;
        isAlertSuccess.value = true;
    }).catch((err) => {
        isAlertError.value = true;
        feedbackMessage.value = err.response.data;
    }).finally(() => {
        name.value = "";
        phone.value = "";
        address.value = "";
        cpf.value = "";
        email.value = "";
        defect.value = "";
        product_type.value = "";
    });
}


if (props.id) {
    isUpdateClient.value = true
    store.dispatch("getClientById", props.id).
        then(() => {
            name.value = store.state.client.name
            phone.value = store.state.client.phone
            address.value = store.state.client.address
            cpf.value = store.state.client.cpf
            email.value = store.state.client.email
            defect.value = store.state.client.defect
            product_type.value = store.state.client.product_type
        })
}


</script>
