import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'home',
    component: function () {
      return import(/* webpackChunkName: "home" */ '../views/HomeView.vue')
    }
  },
  {
    path: '/clients',
    name: 'clients',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: function () {
      return import(/* webpackChunkName: "admin" */ '../views/AdminView.vue')
    }
  },
  {
    path: '/products',
    name: 'products',
    component: function () {
      return import(/* webpackChunkName: "product" */ '../views/ProductView.vue')
    }
  },
  {
    path: '/admin',
    name: 'admin',
    component: function () {
      return import(/* webpackChunkName: "admin" */ '../views/FormLogin.vue')
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})


export default router
