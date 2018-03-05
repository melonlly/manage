import Vue from 'vue'
import mixin from 'common/js/mixin' // 自定义全局组件
import http from 'common/js/http' // 通讯层
import encrypt from 'common/js/encrypt' // 加密组件
import cache from 'common/js/cache' // 缓存组件
import eNum from 'common/js/enum' // 数据字典
import Login from 'components/login/login.vue'
import router from './router/index'
import 'common/stylus/index.styl'

Vue.config.productionTip = false

// 挂载组件
Vue.use(mixin)
Vue.use(http)
Vue.use(encrypt)
Vue.use(cache) // cache依赖encrypt
Vue.use(eNum)

// 拓展Vue实例，添加App组件（根组件）
Vue.extend({})

new Vue({
    el: '#app',
    router,
    template: '<router-view/>',
    components: {Login}
})
