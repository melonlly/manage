import Vue from 'vue'
import Router from 'vue-router'
import login from 'components/login/login.vue'
import index from 'components/index/index.vue'

Vue.use(Router)

export default new Router({
    // 利用 history.pushState API 来完成 URL 跳转而无须重新加载页面（默认hash 模式 —— 使用 URL 的 hash 来模拟一个完整的 URL，于是当 URL 改变时，页面不会重新加载）
    mode: 'history',
    routes: [
        {
            name: 'login',
            path: '/',
            component: login
        },
        {
            name: 'index',
            path: '/index',
            component: index
        }
    ]
})
