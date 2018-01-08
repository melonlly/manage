require('es6-promise').polyfill() // 支持promise
import axios from 'axios'

const Axios = axios.create({
    baseURL: 'http://127.0.0.1:7777',
    timeout: 20000,
})

// 请求拦截器
Axios.interceptors.request.use(req => {
    if (req.method === 'post') {
        // json 转 FormData
        const formData = new FormData()
        Object.keys(req.data).forEach(key => formData.append(key, req.data[key]))
        req.data = formData
    }

    if (localStorage.token) {
        req.headers.Authorization = localStorage.token
    }
    return req
}, error => {
    alert('错误的传参')
    return Promise.reject(error)
})

// 响应拦截器
Axios.interceptors.response.use(res => {
    if (res.code !== this.ERR_OK) {
        alert(res.error)
        return Promise.reject(res)
    }
}, error => {
    const code = error.response.status
    switch (code) {
        // 401 说明 token 验证失败 可以直接跳转到登录页面，重新登录获取 token
        case 401: {

        }
        case 404: {

        }
    }

    return Promise.reject(error.response.data)
})

export default {
    install: (Vue, options) => {
        // 成功
        Vue.prototype.ERR_OK = 0

        // 计算页面高度
        Vue.prototype.$height = () => window.innerHeight + "px"

        // axios
        Vue.prototype.$http = Axios
    }
}