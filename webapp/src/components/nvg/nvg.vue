<template>

	<div class="nvg">
		<ul>
			<span class="cursor" v-show="cursor"></span>
			<li @mouseover="onNav(index, $event)" @mouseleave="outNav(index, $event)" v-for="(nvg, index) in nvgs">
				<div class="item wave color" @click="openSub(index, nvg.sub.length, $event.target.parentNode)">
					<a class="text">{{nvg.text}}</a>
					<i class="drop" v-if="nvg.sub"></i>
				</div>
				<div class="sub" v-if="nvg.sub">
					<span class="center item wave color" :class="{ focus: cur_index === index && cur_sub_index === sub_index }" @click="chooseIt(index, sub_index)" v-for="(sub, sub_index) in nvg.sub">
						<a>{{sub.text}}</a>
					</span>
				</div>
			</li>
		</ul>
	</div>

</template>

<script type="text/ecmascript-6">
	import bus from 'components/bus/bus'

    export default {
        props: ['nvgs'],
        name: 'nvg',
        data () {
            return {
                cursor: false,
                cur_index: 0,
                cur_sub_index: 0
            }
        },
        methods: {
            openSub (index, size, li) {
                let drop = li.querySelector('.drop')
				let sub = li.querySelector('.sub')
                let className = drop.className
				if(className.indexOf('open') > -1){
                    className = className.replace('open', '')
					sub.style.height = '0'
				}else{
				    className += ' open'
                    sub.style.height = this.li_height * size + 'px'
				}
				if(className.startsWith(' ')){
				    className = className.substring(1)
				}
				drop.className = className
            },
            onNav (index, e) {
                let li = this.document.getElementsByTagName('li')[index]
                this.document.getElementsByClassName('cursor')[0].style.marginTop =
                    li.getBoundingClientRect().top -
                    this.document.getBoundingClientRect().top + 'px'
                this.cursor = true
				let item = li.querySelector('.item')
				if(item.className.indexOf('hover') < 0){
                    item.className += ' hover'
                    if(item.className.startsWith(' ')){
                        item.className = item.className.substring(1)
                    }
                }
            },
            outNav (index, e) {
                let li = this.document.getElementsByTagName('li')[index]
                this.document.getElementsByClassName('cursor')[0].style.marginTop = '0px'
                this.cursor = false
                let item = li.querySelector('.item')
                item.className = item.className.replace('hover', '')
            },
            chooseIt (index, sub_index) {
                this.cur_index = index
				this.cur_sub_index = sub_index
				bus.$emit('choose', {
				    index, sub_index
				})
			}
        },
        created () {

        },
        mounted () {
            this.document = this.$doc(this)
            let index = this.cur_index
			let sub_index = this.cur_sub_index
            this.li_height = this.$getAttributes(this.document.querySelector('li .item'), 'height')
            this.document.getElementsByClassName('cursor')[0].style.height = this.li_height + 'px'
            this.openSub(index, this.nvgs[index].sub.length, this.document.getElementsByTagName('li')[0])
            bus.$emit('choose', {
                index, sub_index
            })
        },
        components: {}
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "../../common/stylus/mixin.styl";
	@import "nvg.styl";
</style>
