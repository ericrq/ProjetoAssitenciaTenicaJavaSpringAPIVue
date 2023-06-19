<template>
    <CardsProductAdmin @updateProduct="updateProduct" @isDeleteProduct="isDeleteProduct" title="Produtos">
    </CardsProductAdmin>

    <alertError @closeAlert="closeAlert" v-if="isAlertError" :feedbackMessage="feedbackMessage" />
    <alertSuccess @closeAlert="closeAlert" v-if="isAlertSuccess" :feedbackMessage="feedbackMessage" />

    <div class="flex flex-col items-center justify-center w-full">
        <form class="flex flex-col items-center justify-center w-10/12" v-if="modalProductAdd"
            @submit.stop.prevent="addProduct">
            <h1 class="mb-2 text-3xl text-white font-Sriracha" id="Formulario">Formulario</h1>

            <div class="flex flex-col items-center w-full">
                <label for="product_type" class="text-base text-white font-PassionOne">Produto</label>
                <input name="product_type" placeholder="Nome do Produto"
                    class="w-full max-w-sm px-1 mb-2 text-base text-white bg-gray-900 border rounded font-PassionOne border-neutral-400 placeholder:text-center placeholder:text-white placeholder:font-Passion"
                    type="text" v-model="product_type">
            </div>

            <input
                class="w-full max-w-sm p-1 text-base text-center text-white bg-gray-900 border rounded cursor-pointer font-PassionOne border-neutral-400"
                type="submit" name="btn-cadastrar" value="Adicionar Produto">
        </form>

        <div class="flex justify-center w-full gap-2 mt-5">
            <button class="p-1 text-base text-center text-white border rounded border-neutral-400"
                @click="showModalProductAdd">Adicionar
                Produto</button>
            <router-link to="/clients" class="p-1 text-base text-center text-white border rounded border-neutral-400">Ver
                Clientes</router-link>
        </div>

        <FooterNav class="flex justify-center w-full"></FooterNav>
    </div>
</template>

<script setup>

import FooterNav from "../components/FooterNav.vue";
import CardsProductAdmin from "../components/AdminProductCard/CardsProductAdmin.vue";

import alertError from "../components/alerts/alertError.vue";
import alertSuccess from "../components/alerts/alertSuccess.vue";

import { ref, onBeforeMount } from "vue";
import { useStore } from "vuex";

onBeforeMount(() => {
    store.dispatch("getProducts");
});

const modalProductAdd = ref(false);
const isUpdateProduct = ref(false);

const product_type = ref("");
const product_id = ref("");

const isAlertError = ref(false);
const isAlertSuccess = ref(false);
const feedbackMessage = ref("");

const store = useStore();

const showModalProductAdd = () => {
    modalProductAdd.value = !modalProductAdd.value;
};

const closeAlert = () => {
    isAlertError.value = false;
    isAlertSuccess.value = false;
}
const updateProduct = (payload) => {
    modalProductAdd.value = payload.showModalProductAdd;
    product_type.value = payload.data.product_type;
    isUpdateProduct.value = payload.isUpdateProduct;
    product_id.value = payload.id;
};

const isDeleteProduct = () => {
    alertSuccess.value = false;
    if (isDeleteProduct) {
        isAlertSuccess.value = true;
        feedbackMessage.value = "Produto deletado com sucesso!";
    };
}

const addProduct = () => {
    if (!product_type.value) return;

    if (isUpdateProduct.value) {

        const payload = {
            id: product_id.value,
            data: {
                product_type: product_type.value,
            },
        };

        store.dispatch("updateProduct", payload)
            .then(() => {
                isAlertError.value = false;
                isAlertSuccess.value = true;
                feedbackMessage.value = "Produto atualizado com sucesso!";
            })
            .catch((err) => {
                isAlertError.value = true;
                feedbackMessage.value = err.response.data;
            }).finally(() => {
                product_type.value = "";
                modalProductAdd.value = false;
                store.dispatch("getProducts");
            });
        return;
    }

    store.dispatch("addProduct", {
        product_type: product_type.value,
    }).then(() => {
        isAlertError.value = false;
        isAlertSuccess.value = true;
        feedbackMessage.value = "Produto adicionado com sucesso!";
    }).catch((err) => {
        isAlertError.value = true;
        feedbackMessage.value = err.response.data;
    }).finally(() => {
        product_type.value = "";
        modalProductAdd.value = false;
        store.dispatch("getProducts");
    });
}
</script>