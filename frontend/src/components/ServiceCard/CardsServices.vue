<template>
    <h1 class="my-4 text-3xl text-center text-white font-Sriracha servicos" id="Serviços">Serviços</h1>
    <section class="flex flex-row flex-wrap items-center justify-center w-11/12 gap-8 m-auto">

        <CardService class="border-blue-600 border-s-2"
            :itensList="['Solda do Chip', 'Reparo de Componestes', 'eparo em Terminais dos Processadores']"
            title="Hardware">
        </CardService>
        <CardService class="border-green-600 border-s-2"
            :itensList="['Instalaçao de Sistemas Operacionais', 'Recuperaçao e Backup de Arquivos', 'Gravaçao de Bios']"
            title="Sistema">
        </CardService>
        <CardService class="border-red-600 border-s-2"
            :itensList="['Reparo em Playstation 3, 4 e 5', 'Reparo em Xbox 360, One, X', 'Reparo em Nintendo Switch']"
            title="VideoGame">
        </CardService>
        <CardService class="border-cyan-600 border-s-2"
            :itensList="['Atualizaçao de Softwares', 'Reparo de Componestes', 'Troca de Tela']" title="Celulares">
        </CardService>
    </section>
</template>

<script setup>
import CardService from "../ServiceCard/CardService.vue";
</script>