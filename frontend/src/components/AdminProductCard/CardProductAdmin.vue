<template>
    <div class="border-blue-500 rounded-xl bg-zinc-800 w-96 border-s-2">
        <h2 class="my-2 text-xl text-center text-white font-Sriracha">
            {{ props.product.product_type }}
        </h2>

        <div class="w-full border"></div>

        <div class="flex flex-col items-center justify-center gap-4 mx-6 my-2 md:flex-row">
            <a class="flex justify-between w-full gap-4 cursor-pointer md:w-1/2 bg-zinc-800" @click="updateProduct">
                Editar Produto
                <abbr title="editar registro">
                    <i class="fas fa-edit"></i>
                </abbr>
            </a>
            <a class="flex justify-between w-full gap-4 text-red-400 cursor-pointer md:w-1/2 bg-zinc-800"
                @click="deleteProduct">
                Excluir Produto
                <abbr title="excluir registro">
                    <i class="fas fa-trash-alt"></i>
                </abbr>
            </a>
        </div>
    </div>
</template>

<script setup>
import { useStore } from "vuex";
import { defineProps, ref } from "vue";
import { reactive, defineEmits } from "vue";

const props = defineProps({
    product: {
        type: Object,
        default: () => ({})
    }
});

const emits = defineEmits(["updateProduct", "isDeleteProduct"]);

const store = useStore();

const product_type = ref(props.product.product_type);

const deleteProduct = () => {
    store.dispatch("deleteProduct", props.product.id);
    const isDeleteProduct = ref(false);
    emits("isDeleteProduct", isDeleteProduct);
};

const updateProduct = () => {
    const payload = reactive({
        id: props.product.id,
        showModalProductAdd: true,
        isUpdateProduct: true,
        data: {
            product_type: product_type.value
        }
    });

    emits("updateProduct", payload);
};

</script>