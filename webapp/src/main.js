import Vue from 'vue'
import mixin from 'common/js/mixin' // 自定义全局组件
import Login from 'components/login/login.vue'
import router from './router/index'
import 'common/stylus/index.styl'

Vue.config.productionTip = false

// 挂载组件
Vue.use(mixin)

// 拓展Vue实例，添加App组件（根组件）
Vue.extend({})

new Vue({
    el: '#app',
    router,
    template: '<router-view/>',
    components: {Login}
})