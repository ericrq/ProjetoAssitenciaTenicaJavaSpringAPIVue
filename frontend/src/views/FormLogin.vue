<template>
    <body class="flex flex-col items-center justify-center w-screen h-screen text-gray-700">

        <h1 class="text-2xl font-bold text-white">Login Admin</h1>
        <form class="flex flex-col p-12 mt-12 bg-white rounded shadow-lg" @submit.stop.prevent="loginAdmin()">
            <label class="text-sm font-semibold" for="usernameField">Username</label>
            <input class="flex items-center w-64 h-12 px-4 mt-2 bg-gray-200 rounded focus:outline-none focus:ring-2"
                type="text">
            <label class="mt-3 text-sm font-semibold" for="passwordField">Senha</label>
            <input class="flex items-center w-64 h-12 px-4 mt-2 bg-gray-200 rounded focus:outline-none focus:ring-2"
                type="password">
            <button
                class="flex items-center justify-center w-64 h-12 px-6 mt-8 text-sm font-semibold text-blue-100 bg-blue-600 rounded hover:bg-blue-700">Login</button>
        </form>
    </body>
</template>

<script setup>
import { ref } from 'vue';
import { useStore } from 'vuex';

const store = useStore();

const username = ref('');
const password = ref('');

const payload = {
    username: username.value,
    password: password.value
}

const loginAdmin = () => {
    store.dispatch('loginAdmin', payload);
}

</script>