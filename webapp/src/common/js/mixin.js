require('es6-promise').polyfill() // 支持promise
import axios from 'axios'

const ERR_OK = "0"

const Axios = axios.create({
    baseURL: '/api',
    timeout: 20000
})

// 请求拦截器
Axios.interceptors.request.use(req => {
    // console.log(req)
    if (req.method === 'post') {
        // json 转 FormData
        // const formData = new FormData()
        // Object.keys(req.data).forEach(key => formData.append(key, req.data[key]))
        // req.data = formData

        req.method = 'GET'
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
    // console.log(res)
    if (res.status !== 200) {
        return Promise.reject(res)
    }
    if(res.data.code === ERR_OK){
        return res
    }else{
        alert(res.data.error)
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

// wave
document.addEventListener('mousedown', e => {
    console.log(e)
    let target = e.target
    let parent = target.parentNode
    if(target.className.indexOf('wave') < 0){
        if(parent.className.indexOf('wave') < 0){
            return false
        }else{
            target = parent
        }
    }
    const x = e.clientX
    const y = e.clientY
    const width = Vue.prototype.$getAttributes(target, 'width')
    const height = Vue.prototype.$getAttributes(target, 'height')
    const p_left = Vue.prototype.$getAttributes(target, 'offsetLeft')
    const p_top = Vue.prototype.$getAttributes(target, 'offsetTop')
    const left = x - p_left
    const top = y - p_top
    let div = document.createElement('div')
    div.className = 'ripple'
    div.style.left = left + 'px'
    div.style.top = top + 'px'
    target.appendChild(div)
})
document.addEventListener('mouseup', e => {
    console.log(e)
})

export default {
    install: (Vue, options) => {
        // 成功
        Vue.prototype.ERR_OK = ERR_OK

        // 计算页面高度
        Vue.prototype.$height = () => window.innerHeight + "px"

        /**
         * 获取元素属性（只读）
         * @param ele       dom元素
         * @param property  属性名
         * @param pseudo    伪类名
         */
        Vue.prototype.$getAttributes = (ele, property, pseudo) => window.getComputedStyle(ele, pseudo).getPropertyValue(property).replace('px', '')

        // axios
        Vue.prototype.$http = Axios

        // 菜单目录
        Vue.prototype.menu = []

        // 获取或赋值菜单目录
        Vue.prototype.$menu = menu => menu ? Vue.prototype.menu = menu : Vue.prototype.menu
    }
}