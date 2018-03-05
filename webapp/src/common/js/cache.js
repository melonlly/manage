/**
 * Created by melon on 2018/2/8.
 */

export default {
    install (Vue) {
        Vue.prototype.setItem = (key, data) => {
            if(data){
                data = Vue.prototype.encrypt(JSON.stringify(data))
                sessionStorage.setItem(key, data)
            }
        }

        Vue.prototype.getItem = key => {
            if(key){
                let data = sessionStorage.getItem(key)
                if(data){
                    let jsonStr = Vue.prototype.decrypt(data)
                    return JSON.parse(jsonStr)
                }else{
                    return
                }
            }
        }

        Vue.prototype.removeItem = key => {
            if(key){
                sessionStorage.removeItem(key)
            }
        }
    }
}