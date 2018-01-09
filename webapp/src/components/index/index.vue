<template>

	<div id="index" :style="{ height: $height() }">

		<div class="header">
			<logo></logo>
			<nvg></nvg>
			<user></user>
		</div>

		<div class="main">
			<div class="content">
				<list :type="list_type" :options="list_ops" :columns="list_cols" @filter="filter" @before="before" @after="after"></list>
			</div>
		</div>

		<div class="footer">
			<dock :docks="docks"></dock>
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
                menu: [],
			    docks: []
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
			}
		},
		created () {
		    this.$http.post('/menu', {}).then(res => {
                if(res.data.code === this.ERR_OK){
                    this.menu = res.data.data
					this.menu.forEach(item => {
                        this.docks.push(item.text)
                    })
                }
			}).catch(error => {

			})

		    this.list_type = 'table'
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