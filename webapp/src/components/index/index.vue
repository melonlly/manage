<template>

	<div id="index" :style="{ height: $height() }">

		<div class="header">
			<div class="logo">logo</div>
			<div class="nav-sub">
				<ul>
					<li>nav-sub-1</li>
					<li>nav-sub-2</li>
					<li>nav-sub-3</li>
					<li>nav-sub-4</li>
				</ul>
			</div>
			<div class="user">user</div>
		</div>

		<div class="main">
			<div class="content">
				<!--<list :type="list_type" :options="list_ops" :columns="list_cols" @filter="filter" @before="before" @after="after"></list>-->
			</div>
		</div>

		<div class="footer">
			<div class="nav-main">
				<ul>
					<li>nav-main-1</li>
					<li>nav-main-2</li>
					<li>nav-main-3</li>
					<li>nav-main-4</li>
					<li>nav-main-5</li>
					<li>nav-main-6</li>
				</ul>
			</div>
		</div>

	</div>

</template>

<script type="text/ecmascript-6">
	import list from 'components/list/list'

	export default {
		name: 'index',
		data () {
			return {}
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
//		    this.list_type = 'table'
//			this.list_ops = {
//		        url: '/hello/json',
//				params: {
//		            index: 1,
//					size: 10
//				},
//                isPage: false,
//			}
//			this.list_cols = [
//				{
//				    name: 'id',
//					text: '字段1',
//                    hide: true
//				},{
//                    name: 'name',
//                    text: '字段2'
//				},{
//                    name: 'password',
//                    text: '字段3',
//				},{
//                    name: '$operate',
//                    text: '操作',
//					operate: ['remove', 'edit']
//				}
//			]
		},
		components: {
            list
		}
	}
</script>

<style lang="stylus" rel="stylesheet/stylus">
	@import "index.styl";
</style>