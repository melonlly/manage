<template>

	<div id="index" :style="{ height: $height() + 'px' }">

		<div class="header">
			<i class="list" @click="showList"></i>
			<!--<logo></logo>-->
			<user></user>
		</div>

		<div class="left" :style="{ height: $height() - 64 - 5 + 'px' }">
			<nvg :nvgs="mains" :use="use" v-if="mains"></nvg>
		</div>

		<component ref="cpt" :style="{ height: $height() - 64 - 5 + 'px' }"
					:is="center ? center : ''"></component>

		<!--<Vmain :style="{ height: $height() - 64 - 5 + 'px' }"></Vmain>-->

		<!--<div class="footer">-->
			<!--<dock ref="dock" :docks="docks" @select="selectDock" v-if="docks"></dock>-->
		<!--</div>-->

		<!-- 弹窗 -->
		<pop v-show="pop" :content="content" :popType="popType"></pop>
	</div>

</template>

<script type="text/ecmascript-6">
	import dock from 'components/dock/dock'
	import logo from 'components/logo/logo'
	import nvg from 'components/nvg/nvg'
    //	import Vmain from 'components/Vmain/Vmain'
    import news from 'components/business/news/news'
    import daily from 'components/business/daily/daily'
    import score from 'components/business/score/score'
    import users from 'components/business/users/users'
	import lesson from 'components/business/schedule/lesson'
    import user from 'components/business/user/user'
    import bus from 'components/bus/bus'
    import pop from 'components/pop/pop'

	export default {
		name: 'index',
		data () {
			return {
			    mains: null,
				center: null,
				// 二级导航使用字段
				use: {
			        text: 'name',
					sub: {
                        id: 'id',
                        text: 'name',
                        value: 'url'
                    }
				},
				pop: false,
                content: null,
                popType: '',
				show: true
			}
		},
		methods: {
            showList () {
                this.show = !this.show
			}
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
//			}
		},
		created () {
		    let _this = this
			let user = this.getItem('user')
			// 超管
			if(user){
                this.$http.post('/catalog/all', {}).then(res => {
                    let list = res.getData('list')
					if(user.role == 2){
						let i = ''
                        list.forEach((catalog, index) => {
                            if(catalog.name === '权限管理'){
                                i = index
							}
						})
						if(i){
						    list.splice(i, 1)
						}
					}
                    this.$menu(list)
                    this.mains = this.menu
                }).catch(error => {
                    console.log(error)
                })
			}else{
			    this.$router.push({
					name: 'login'
				})
			}

            bus.$on('choose', data => {
                this.center = data.url
            })

			bus.$on('pop', (show, content, type) => {
			    this.pop = show
				this.content = content
				this.popType = type
				if(content && type === 'component'){
                    this.content.operates.forEach(op => {
                        _this[op.func] = params => {
                            _this.$refs.cpt[op.func](params)
                        }
                    })
				}
			})
		},
		mounted () {
//		    window.addEventListener('scroll', this.scroll)
		},
		components: {
            dock, logo, user, nvg, news, daily, score, users, lesson, pop
		}
	}
</script>

<style lang="stylus" rel="stylesheet/stylus">
	@import "../../common/stylus/mixin.styl";
	@import "index.styl";
</style>
