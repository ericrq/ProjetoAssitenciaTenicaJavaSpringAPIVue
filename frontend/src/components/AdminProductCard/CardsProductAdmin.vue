<template>
    <h1 class="my-5 text-3xl text-center text-white font-PassionOne">{{ title }}</h1>

    <div class="flex flex-row flex-wrap items-center justify-center w-11/12 gap-8 mx-auto">
        <CardProductAdmin v-for="product in $store.state.products" :key="product.id" :product="product"
            @updateProduct="emitUpdate" @isDeleteProduct="isDeleteProduct" />
    </div>
</template>

<script setup>
import CardProductAdmin from "../AdminProductCard/CardProductAdmin.vue";

import { defineProps, defineEmits } from "vue";

const props = defineProps({
    title: {
        type: String,
        required: true
    }
});


const emits = defineEmits(["updateProduct", "isDeleteProduct"]);

function emitUpdate(data) {
    emits("updateProduct", data);
}

function isDeleteProduct() {
    emits("isDeleteProduct");
}
</script>
