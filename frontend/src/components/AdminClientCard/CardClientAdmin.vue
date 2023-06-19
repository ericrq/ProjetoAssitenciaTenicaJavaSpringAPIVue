<template>
    <div class="border-blue-500 rounded-xl bg-zinc-800 w-96 border-s-2">
        <h2 class="my-2 text-xl text-center text-white font-Sriracha">
            {{ props.client.name }}
        </h2>

        <div class="w-full border"></div>

        <ul class="p-3">
            <li class="text-base text-white list-disc list-inside font-PassionOne">{{ props.client.phone }}</li>
            <li class="text-base text-white list-disc list-inside font-PassionOne">{{ props.client.address }}</li>
            <li class="text-base text-white list-disc list-inside font-PassionOne">{{ props.client.email }}</li>
            <li class="text-base text-white list-disc list-inside font-PassionOne">{{ props.client.cpf }}</li>
            <li class="text-base text-white list-disc list-inside font-PassionOne">{{ props.client.defect }}</li>
            <li class="text-base text-white list-disc list-inside font-PassionOne">{{ props.client.product_type }}</li>
        </ul>

        <div class="w-full border"></div>

        <div class="flex flex-col items-center justify-center gap-4 mx-6 my-2 md:flex-row">
            <button class="flex justify-between w-full gap-4 cursor-pointer md:w-1/2 bg-zinc-800" @click="updateClient">
                Editar Produto <abbr title="editar registro">
                    <i class="fas fa-edit"></i>
                </abbr>
            </button>

            <button class="flex justify-between w-full gap-4 text-red-400 cursor-pointer md:w-1/2 bg-zinc-800"
                @click="deleteClient"> Excluir Produto
                <abbr title="excluir registro">
                    <i class="fas fa-trash-alt"></i>
                </abbr>
            </button>
        </div>
    </div>
</template>

<script setup>
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { defineProps, defineEmits } from "vue";

const props = defineProps({
    client: {
        type: Object,
        default: () => ({})
    }
});

const emits = defineEmits(["updateClient", "deleteClient"]);

const store = useStore();
const router = useRouter();

const deleteClient = () => {
    store.dispatch("deleteClient", props.client.id);
};

const updateClient = () => {
    router.push({
        name: "home",
        query: {
            id: props.client.id,
        }
    });
}

</script>