<template>

	<div id="index" :style="{ height: $height() + 'px' }">

		<div class="header">
			<logo></logo>
			<user></user>
		</div>

		<div class="left" :style="{ height: $height() - 64 - 5 + 'px' }">
			<nvg :nvgs="mains" v-if="mains"></nvg>
		</div>

		<Vmain :style="{ height: $height() - 64 - 5 + 'px' }"></Vmain>

		<!--<div class="footer">-->
			<!--<dock ref="dock" :docks="docks" @select="selectDock" v-if="docks"></dock>-->
		<!--</div>-->

	</div>

</template>

<script type="text/ecmascript-6">
	import dock from 'components/dock/dock'
	import logo from 'components/logo/logo'
	import user from 'components/user/user'
	import nvg from 'components/nvg/nvg'
	import Vmain from 'components/Vmain/Vmain'

	export default {
		name: 'index',
		data () {
			return {
			    mains: null
			}
		},
		methods: {
		    /*
		     * 滚动事件
		     * 	scroll_top_before: 0, // 滚动条滚动前scrollTop
			 *	scroll_top_now: 0 // 滚动条滚动后scrollTop
		     */
//            scroll (e) {
//                const _this = this
//				this.scroll_top_now = window.scrollY
//				// 向下滚动
//				if(this.scroll_top_now > this.scroll_top_before){
//                    setTimeout(() => {
//                        const dock = _this.$refs.dock
//						if(!dock.showTip){
//                            dock.hideDock()
//						}
//					}, 100)
//				}else{ // 向上滚动
//
//				}
//				setTimeout(() => { this.scroll_top_before = this.scroll_top_now }, 0)
//			},
			// 选择一级菜单
            selectDock (dock) {

			}
		},
		created () {
		    this.$http.post('/menu', {}).then(res => {
                this.$menu(res.data.data)
                this.mains = this.menu
			}).catch(error => {
                console.log(error)
			})
		},
		mounted () {
//		    window.addEventListener('scroll', this.scroll)
		},
		components: {
            dock, logo, user, nvg, Vmain
		}
	}
</script>

<style lang="stylus" rel="stylesheet/stylus">
	@import "../../common/stylus/mixin.styl";
	@import "index.styl";
</style>
