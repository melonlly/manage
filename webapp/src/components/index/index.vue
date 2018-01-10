<template>

	<div id="index" :style="{ height: $height() }">

		<div class="header">
			<logo></logo>
			<nvg :nvgs="nvgs" v-if="nvgs"></nvg>
			<user></user>
		</div>

		<div class="main">
			<div class="content">
				<list :options="list_ops" :columns="list_cols" @filter="filter" @before="before" @after="after"></list>
			</div>
		</div>

		<div class="footer">
			<dock :docks="docks" @select="selectDock" v-if="docks"></dock>
		</div>

	</div>

</template>

<script type="text/ecmascript-6">
	import list from 'components/list/list'
	import dock from 'components/dock/dock'
	import logo from 'components/logo/logo'
	import user from 'components/user/user'
	import nvg from 'components/nvg/nvg'

	export default {
		name: 'index',
		data () {
			return {
			    docks: null,
				nvgs: null
			}
		},
		methods: {
            before () { console.log('before') },
			after (res) { console.log(res) },
			// 过滤处理数据集
            filter (data) {
                const _this = this
                data.forEach(item => {

				})
                this.data = data
                return data
			},
			// 选择一级菜单
            selectDock (dock) {
				this.nvgs = dock.sub
			}
		},
		created () {
		    this.$http.post('/menu', {}).then(res => {
                this.$menu(res.data.data)
                this.docks = this.menu
			}).catch(error => {
                console.log(error)
			})

			this.list_ops = {
		        url: '/list',
				params: {
		            index: 1,
					size: 10
				},
                isPage: true,
			}
			this.list_cols = [
				{
				    name: 'id',
					text: '字段1',
                    hide: true
				},{
                    name: 'name',
                    text: '字段2'
				},{
                    name: 'password',
                    text: '字段3',
				},{
                    name: '$operate',
                    text: '操作',
					operate: ['remove', 'edit']
				}
			]
		},
		components: {
            list, dock, logo, user, nvg
		}
	}
</script>

<style lang="stylus" rel="stylesheet/stylus">
	@import "index.styl";
</style>