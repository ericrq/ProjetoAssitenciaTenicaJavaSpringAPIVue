<template>
    <div class="rounded-xl bg-zinc-800 w-96">
        <h2 class="mt-2 text-xl text-center text-white font-Sriracha">{{ props.title }}
        </h2>
        <ul class="p-3">
            <li v-for="itensList in props.itensList" class="text-base text-white list-disc list-inside font-PassionOne">{{
                itensList }}</li>
        </ul>
    </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps({
    itensList: {
        type: Array,
        required: true
    },
    title: {
        type: String,
        required: true
    }
});
</script>