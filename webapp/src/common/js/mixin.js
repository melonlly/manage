export default {
    install: (Vue, options) => {
        // 成功
        Vue.prototype.ERR_OK = 0

        // 计算页面高度
        Vue.prototype.$height = () => window.innerHeight + "px"
    }
}