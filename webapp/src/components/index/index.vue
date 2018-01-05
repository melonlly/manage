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
				<list :type="list_type" :options="list_ops" :columns="list_cols"></list>
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
            func1 (val) { console.log(val) },
            actionRender () { console.log('actionRender') }
		},
		created () {
		    this.list_type = 'table'
			this.list_ops = {
		        url: '/list',
				params: {
		            index: 1,
					size: 10
				},
				pages: true,
				before: this.before, // 请求前事件
				after: this.after // 加载完成后事件
			}
			this.list_cols = [
				{
				    name: 'col1',
					text: '字段1',
					func: this.func1
				},{
                    name: 'col2',
                    text: '字段2'
				},{
                    name: 'col3',
                    text: '字段3',
					hide: true
				},{
                    name: '$operat',
                    text: '操作',
					func: this.actionRender
				}
			]
		},
		components: {
            list
		}
	}
</script>

<style lang="stylus" rel="stylesheet/stylus">
	@import "index.styl";
</style>