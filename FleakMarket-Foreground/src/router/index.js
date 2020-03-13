import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/index.vue'
import login from '@/pages/login.vue'
import productDetails from '@/pages/ProductDetails.vue'
import productType from '@/pages/productType.vue'
import mine from '@/pages/User/mine.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/productDetails',
      name: 'productDetails',
      component: productDetails
    },
    {
      path: '/productType',
      name: 'productType',
      component: productType
    },
    {
      path: '/mine',
      name: 'mine',
      component: mine
    }
  ]
})

