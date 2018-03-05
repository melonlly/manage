require('es6-promise').polyfill() // 支持promise
require("es6-promise-always") // 支持always
import axios from 'axios'

const ERR_OK = 0

const Axios = axios.create({
    baseURL: '/api',
    method: 'post', // 默认get
    // 即将被发送的自定义请求头
    headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/json'
    },
    withCredentials: false, // 跨域请求时是否需要使用凭证
    // `auth` 表示应该使用 HTTP 基础验证，并提供凭据
    // 这将设置一个 `Authorization` 头，覆写掉现有的任意使用 `headers` 设置的自定义 `Authorization`头
    // auth: {
    //     username: 'admin',
    //     password: 'admin'
    // },
    timeout: 20000,
    responseType: 'json', // 服务器响应的数据类型，可以是 'arraybuffer', 'blob', 'document', 'json', 'text', 'stream'
    // 允许为上传处理进度事件
    onUploadProgress: function (progressEvent) {},
    // 允许为下载处理进度事件
    onDownloadProgress: function (progressEvent) {},
    maxContentLength: 2000, // 定义允许的响应内容的最大尺寸
    // 定义代理服务器的主机名称和端口
    // proxy: {
    //     host: '127.0.0.1',
    //     port: '9898',
    //     auth: {
    //         username: 'admin',
    //         password: 'admin'
    //     }
    // }
})

// 请求拦截器
Axios.interceptors.request.use(req => {
    // console.log(req)
    if (req.method === 'post') {
        // json 转 FormData
        // const formData = new FormData()
        // Object.keys(req.data).forEach(key => formData.append(key, req.data[key]))
        // req.data = formData

        // req.method = 'GET' // for mock server
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
    if (res.data.code === ERR_OK) {
        res.getData = type => type === 'list' ? res.data.data : res.data.data[0]
        return res
    } else {
        alert(res.data.error)
        return Promise.reject(res.data.error)
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
    install (Vue) {
        // 成功
        Vue.prototype.ERR_OK = ERR_OK
        // axios
        Vue.prototype.$http = Axios
    }
}