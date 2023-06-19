<template>
  <HeaderNav></HeaderNav>

  <SliderShow></SliderShow>

  <CardsServices></CardsServices>

  <FormRegister :id="idClient"></FormRegister>

  <FooterNav></FooterNav>
</template>

<script setup>

import SliderShow from "../components/SliderShow.vue";
import HeaderNav from "../components/HeaderNav.vue";
import FooterNav from "../components/FooterNav.vue";
import CardsServices from "../components/ServiceCard/CardsServices.vue";
import FormRegister from "../components/FormRegister.vue";


import { useRouter } from "vue-router";

const router = useRouter();

const idClient = router.currentRoute.value.query.id;


</script>