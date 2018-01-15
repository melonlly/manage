<template>

	<div id="index" :style="{ height: $height() }">

		<div class="header">
			<logo></logo>
			<nvg :nvgs="nvgs" v-if="nvgs"></nvg>
			<user></user>
		</div>

		<div class="main">
			<div class="content">
				<VForm :feilds="feilds" :operates="operates" @search="search" @file="file" @import="v_import" @export="v_export"></VForm>
				<list :options="options" :columns="columns" @filter="filter" @before="before" @after="after"></list>
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
	import VForm from 'components/VForm/VForm'

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
			},
			// list查询
            search () {
                console.log('search')
			},
			// 文件上传
            file () {
                console.log('file')
			},
			// 导入
            v_import () {
                console.log('v_import')
			},
			// 导出
            v_export () {
                console.log('v_export')
			}
		},
		created () {
		    this.$http.post('/menu', {}).then(res => {
                this.$menu(res.data.data)
                this.docks = this.menu
			}).catch(error => {
                console.log(error)
			})

			this.options = {
		        url: '/list',
				params: {
		            index: 1,
					size: 10
				},
                isPage: true,
			}
			this.columns = [
				{
				    name: 'id',
					text: 'id',
                    hide: true
				},{
                    name: 'name',
                    text: '名称'
				},{
                    name: 'password',
                    text: '密码',
				},{
                    name: 'type',
                    text: '类型',
                },{
                    name: '$operate',
                    text: '操作',
					operate: ['remove', 'edit']
				}
			]

			this.feilds = [
				{
				    name: 'name',
					text: '名称',
					default: '111'
				},{
                    name: 'type',
                    text: '类型',
//                    component: 'drop',
                    default: '1'
				}
			]
			this.operates = [
			    {
			        name: '查询',
			        type: 'search',
					func: 'search'
				},{
                    name: '重置',
		        	type: 'reset'
				},{
                    name: '上传',
		        	type: 'file',
                    func: 'file'
				},{
                    name: '导入',
                    type: 'import',
                    func: 'import'
                },{
                    name: '导出',
		        	type: 'export',
                    func: 'export'
				}
			]
		},
		components: {
            list, dock, logo, user, nvg, VForm
		}
	}
</script>

<style lang="stylus" rel="stylesheet/stylus">
	@import "index.styl";
</style>