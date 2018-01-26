<template>

	<div class="nvg">
		<ul>
			<span class="cursor" v-show="cursor"></span>
			<li class="center" @mouseover="onNav(index)" @mouseleave="outNav(index)" :style="{width: 100 / nvgs.length + '%'}" v-for="(nvg, index) in nvgs">
				<a class="text wave">{{nvg.text}}</a>
				<div class="sub" v-if="nvg.sub">
					<div class="sub-cursor" v-show="sub_cursor"></div>
					<span class="center item" v-for="(sub, sub_index) in nvg.sub" @mouseover="onItem(index, sub_index)" @mouseleave="outItem(index, sub_index)">
						<a>{{sub.text}}</a>
					</span>
				</div>
			</li>
		</ul>
	</div>

</template>

<script type="text/ecmascript-6">
    export default {
        props: ['nvgs'],
        name: 'nvg',
        data () {
            return {
                li_width: 0,
				item_height: 0,
                cursor: false,
                sub_cursor: false
			}
        },
        methods: {
            onNav (index) {
                document.getElementsByClassName('cursor')[0].style.marginLeft = this.li_width * index + 'px'
                this.cursor = true
                let sub = document.getElementsByClassName('sub')[index]
                sub.style.visibility = ''
			},
			outNav (index) {
                document.getElementsByClassName('cursor')[0].style.marginLeft = '0px'
                this.cursor = false
                let sub = document.getElementsByClassName('sub')[index]
                sub.style.visibility = 'hidden'
			},
			onItem (index, sub_index) {
                document.getElementsByClassName('sub-cursor')[index].style.marginTop = this.item_height * sub_index + 'px'
                this.sub_cursor = true
			},
			outItem (index, sub_index) {
                document.getElementsByClassName('sub-cursor')[index].style.marginTop = '0px'
                this.sub_cursor = false
			}
        },
        created () {
            
        },
		mounted () {
            const navHeight = this.$getAttributes(document.getElementsByClassName('nvg')[0], 'height') + 'px'
            for(let sub of document.getElementsByClassName('sub')){
                sub.style.visibility = 'hidden'
				sub.style.top = navHeight
            }
			for(let text of document.getElementsByClassName('text')){
                text.style.lineHeight = navHeight
			}
			this.li_width = this.$getAttributes(document.getElementsByClassName('text')[0], 'width')
			document.getElementsByClassName('cursor')[0].style.width = this.li_width + 'px'
			this.item_height = this.$getAttributes(document.getElementsByClassName('item')[0], 'height')
			for(let sub_cursor of document.getElementsByClassName('sub-cursor')){
			    sub_cursor.style.height = this.item_height + 'px'
			}
		},
        components: {
            
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "../../common/stylus/base.styl";
	@import "nvg.styl";
</style>