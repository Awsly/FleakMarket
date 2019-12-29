import Vue from 'vue'
import Vuex from 'vuex'
import Router from 'vue-router'
import index from '@/pages/index.vue'
import login from '@/pages/login.vue'
import productDetails from '@/pages/ProductDetails.vue'
import productType from '@/pages/productType.vue'

Vue.use(Router)
Vue.use(Vuex)

const store = new Vuex.Store({
  //strict:true,  // 开启严格模式  确保state 中的数据只能 mutations 修改
  state:{
    count:0
  },
  mutations:{

  },
  actions:{

  },
  getters:{

  },
  modules:{

  }
})

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/login/:name',
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
    }
  ]
})

