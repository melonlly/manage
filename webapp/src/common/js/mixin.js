const getAttributes = (ele, property, pseudo) => window.getComputedStyle(ele, pseudo).getPropertyValue(property).replace('px', '')

const getWaveTarget = target => {
    if (target.nodeName === 'BODY') {
        return null
    } else {
        if (target.className.indexOf('wave') < 0) {
            if (target.parentNode.className.indexOf('wave') < 0) {
                return getWaveTarget(target.parentNode)
            } else {
                return target.parentNode
            }
        } else {
            return target
        }
    }
}

// wave
document.addEventListener('mousedown', e => {
    let target = getWaveTarget(e.target)
    if (target) {
        const x = e.clientX
        const y = e.clientY
        const width = Number(getAttributes(target, 'width'))
        const height = Number(getAttributes(target, 'height'))
        const t_left = target.getBoundingClientRect().left
        const t_top = target.getBoundingClientRect().top
        // 相对父级
        let left = x - t_left
        let top = y - t_top
        let right = width - left
        let bottom = height - top
        // 到四个角的距离
        let toLeftTop = Math.sqrt(Math.pow(left, 2) + Math.pow(top, 2))
        let toRightTop = Math.sqrt(Math.pow(right, 2) + Math.pow(top, 2))
        let toLeftBottom = Math.sqrt(Math.pow(left, 2) + Math.pow(bottom, 2))
        let toRightBottom = Math.sqrt(Math.pow(right, 2) + Math.pow(bottom, 2))
        const max = Math.max(toLeftTop, toRightTop, toLeftBottom, toRightBottom)

        let div = document.createElement('div')
        div.className = 'ripple'
        div.style.left = left - max + 'px'
        div.style.top = top - max + 'px'
        div.style.width = max * 2 + 'px'
        div.style.height = max * 2 + 'px'
        target.appendChild(div)
        window.setTimeout(() => {
            target.removeChild(div)
        }, 300)
    }
})
// document.addEventListener('mouseup', e => {
//     let target = e.target
//     let parent = target.parentNode
//     if(target.className.indexOf('wave') < 0){
//         if(parent.className.indexOf('wave') < 0){
//             return false
//         }else{
//             target = parent
//         }
//     }
//     window.setTimeout(() => {
//         let nodes = target.childNodes
//         for(let node of nodes){
//             if(node.className === 'ripple'){
//                 target.removeChild(node)
//             }
//         }
//     }, 100)
// })

export default {
    install (Vue) {
        // 计算页面高度
        Vue.prototype.$height = () => window.innerHeight

        /**
         * 获取元素属性（只读）
         * @param ele       dom元素
         * @param property  属性名
         * @param pseudo    伪类名
         */
        Vue.prototype.$getAttributes = getAttributes

        // 菜单目录
        Vue.prototype.menu = []

        // 获取或赋值菜单目录
        Vue.prototype.$menu = menu => menu ? Vue.prototype.menu = menu : Vue.prototype.menu

        // 当前组件document（只能在mounted中初始化，created中还未生成dom）
        Vue.prototype.$doc = component => component.$vnode.elm
    }
}
