<template>
  <CardsClientAdmin title="Clientes"></CardsClientAdmin>

  <div class="flex flex-col justify-center w-full ">
    <div class="flex justify-center w-full gap-2 mt-5 ">
      <router-link to="/" class="p-1 text-base text-center text-white border rounded border-neutral-400">Adicionar
        Cliente</router-link>

      <router-link to="/products" class="p-1 text-base text-center text-white border rounded border-neutral-400">Ver
        Produtos</router-link>
    </div>

    <FooterNav class="flex justify-center w-full"></FooterNav>
  </div>
</template>

<script setup>

import { onBeforeMount } from "vue";
import { useStore } from "vuex";
import FooterNav from "../components/FooterNav.vue";
import CardsClientAdmin from "../components/AdminClientCard/CardsClientAdmin.vue";

const store = useStore();

onBeforeMount(() => {
  store.dispatch("getClients");
});

</script>