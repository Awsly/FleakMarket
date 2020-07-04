import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import state from './state'
import mutations from './mutations'
import action from './action'
import getters from './getters'

export default new Vuex.Store({
  strict: true,
  state,//状态
  mutations,//包含多个更新state函数的对象
  action,//包含多个对应事件回调函数的对象
  getters//包含多个getter计算属性函数对象
})
