import Vue from 'vue'
import mixin from 'common/js/mixin' // 自定义全局组件
import App from './App'
import Login from 'components/login/login.vue'
import router from './router/index'
import 'common/stylus/index.styl'

Vue.config.productionTip = false

// 挂载组件
Vue.use(mixin)
Vue.use(router)

// 拓展Vue实例，添加App组件（根组件）
Vue.extend({
    App,
    Login
})

new Vue({
    el: '#app',
    template: '<Login/>',
    components: {Login}
})