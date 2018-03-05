/**
 * Created by melon on 2018/2/13.
 */
export default {
    install (Vue) {
        Vue.prototype.ENUM = {
            'isOrNot': [{text: '全部', value: ''}, {text: '否', value: '0'}, {text: '是', value: '1'}],
            'role': [{text: '全部', value: ''}, {text: '老师', value: '1'}, {text: '家长', value: '2'}],
            'exam': [{text: '英语周测成绩', value: '1'}, {text: '期中考试成绩', value: '2'}, {text: '期末考试成绩', value: '3'}],
            'lesson': [{text: '全部', value: ''}, {text: '好(4分)', value: '4'}, {text: '良(3分)', value: '3'}, {text: '差(1分)', value: '1'}],
            'phone': [{text: '全部', value: ''}, {text: '上交(4分)', value: '4'}, {text: '未上交(0分)', value: '0'}, {text: '未带(4分)', value: '4'}],
            'homework': [{text: '全部', value: ''}, {text: '上交(4分)', value: '4'}, {text: '迟交(3分)', value: '3'}, {text: '未交(0分)', value: '0'}],
            'daily': [{text: '全部', value: ''}, {text: '到勤(4分)', value: '4'}, {text: '缺勤(0分)', value: '0'}],
            'clean': [{text: '全部', value: ''}, {text: '值日(4分)', value: '4'}, {text: '未值日(0分)', value: '0'}]
        }

        Vue.prototype.enumToValue = (name, value) => {
            value = value + ''
            let result = value || ''
            if(name && value){
                Vue.prototype.ENUM[name].forEach(item => {
                    if(item.value === value + ""){
                        result = item.text
                        return
                    }
                })
                return result
            }else {
                return result
            }
        }
    }
}