<template>
    <h1 class="my-5 text-3xl text-center text-white font-PassionOne">{{ title }}</h1>

    <div class="flex flex-row flex-wrap items-center justify-center w-11/12 gap-8 mx-auto">
        <CardClientAdmin v-for="client in $store.state.clients" :key="client.id" :client="client" />
    </div>
</template>

<script setup>
import CardClientAdmin from "../AdminClientCard/CardClientAdmin.vue";

import { defineProps } from "vue";

const props = defineProps({
    title: {
        type: String,
        required: true
    }
});


</script>
