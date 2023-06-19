import { createStore } from 'vuex'
import axios from 'axios'

const apiProduct = axios.create({
  baseURL: 'http://localhost:8082/api'
})

const apiClient = axios.create({
  baseURL: 'http://localhost:8082/api'
})

export default createStore({
  state: {
    products: [],
    clients: [],
    client: [],
    admin: []
  },
  getters: {},
  mutations: {
    // client
    storeClients(state, payload) {
      state.clients = payload
    },

    storeClient(state, payload) {
      const index = state.clients.findIndex((todo) => todo.id === payload.id)
      if (index >= 0) {
        state.clients.splice(index, 1, payload)
      } else {
        state.clients.push(payload)
      }
    },

    deleteClient: (state, id) => {
      const index = state.clients.findIndex(todo => todo.id === id)

      if (index >= 0) {
        state.clients.splice(index, 1)
      }
    },

    storeClientById(state, payload) {
      state.client = payload
    },

    // product
    storeProducts(state, payload) {
      state.products = payload
    },

    storeProduct(state, payload) {
      const index = state.products.findIndex((todo) => todo.id === payload.id)

      if (index >= 0) {
        state.products.splice(index, 1, payload)
      } else {
        state.products.push(payload)
      }
    },

    deleteProduct: (state, id) => {
      const index = state.products.findIndex(todo => todo.id === id)

      if (index >= 0) {
        state.products.splice(index, 1)
      }
    },

    // login admin
    storeAdmin(state, payload) {
      state.admin = payload
    }
  },

  actions: {
    // client
    addClient({ commit }, data) {
      return apiClient.post('/client', data).
        then(() => {
          commit('storeClient', data)
        });
    },

    getClients({ commit }) {
      return apiClient.get('/client').
        then((response) => {
          commit('storeClients', response.data)
        }).catch((error) => {
          console.log(error)
        });
    },

    deleteClient({ commit }, id) {
      return apiClient.delete(`/client/${id}`).
        then(() => {
          commit('deleteClient', id)
        });
    },

    updateClient({ commit }, { id, data }) {
      return apiClient.put(`/client/${id}`, data).
        then((response) => {
          commit('storeClient', response.data)
        });
    },

    getClientById({ commit }, id) {
      return apiClient.get(`/client/${id}`).
        then((response) => {
          commit('storeClientById', response.data)
        }).catch((error) => {
          console.log(error)
        });
    },

    // product
    addProduct({ commit }, data) {
      return apiProduct.post('/product', data).
        then(() => {
          commit('storeProduct', data)
        });
    },

    getProducts({ commit }) {
      return apiProduct.get('/product').
        then((response) => {
          commit('storeProducts', response.data)
        }).catch((error) => {
          console.log(error)
        });
    },

    deleteProduct({ commit }, id) {
      return apiProduct.delete(`/product/${id}`).
        then(() => {
          commit('deleteProduct', id)
        });
    },

    updateProduct({ commit }, { id, data }) {
      return apiProduct.put(`/product/${id}`, data).
        then((response) => {
          commit('storeProduct', response.data)
        });
    },

    // login admin
    loginAdmin({ commit }, data) {
      return apiProduct.post('/loginAdmin', data).
        then((response) => {
          commit('storeAdmin', response.data)
        });
    }
  },

  modules: {}
})
