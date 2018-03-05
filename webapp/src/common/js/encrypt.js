import CryptoJS from 'crypto-js'

const key = 'melon';

export default {
    install (Vue) {
        Vue.prototype.encrypt = source => {
            if(source){
                let encryptedData = CryptoJS.AES.encrypt(source, key);
                return encryptedData.toString()
            }else{
                return
            }
        }

        Vue.prototype.decrypt = Ciphertext => {
            if(Ciphertext){
                let decryptedData = CryptoJS.AES.decrypt(Ciphertext, key);
                return decryptedData.toString(CryptoJS.enc.Utf8);
            }else{
                return
            }
        }

        Vue.prototype.getMD5 = source => {
            if(source){
                return CryptoJS.MD5(source).toString()
            }else{
                return
            }
        }
    }
}