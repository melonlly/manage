<template>
	<div class="main">
		<div class="table_box">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th v-for="th in columns" v-show="!th.hide">{{th.text}}</th>
				</tr>
			</table>
		</div>
		<div class="no_data" v-show="">
			<i></i>
			<p>暂无数据</p>
		</div>
		<div class="pages">
			<div class="num">
				<span>每页显示</span>
				<drop :entries="sizes" :selected="setSize"></drop>
			</div>
			<div class="stat">
				<span><em class="active">1</em></span>
				<span>跳转至</span>
				<input type="text" class="text"/>
				<span>页</span>
				<a href="javascript:;" class="enter">确定</a>
			</div>
		</div>
	</div>
</template>

<script type="text/ecmascript-6">
	import drop from 'components/drop/drop'

	export default {
	    props: ['type', 'options', 'columns'],
		name: 'list',
		data () {
			return {
			    url: this.options.url, // 请求链接
                params: this.options.params, // 请求参数
                totalRows: 0, // 总行数
                totalPages: 0, // 总页数
				index: this.options.params.index, // 当前页码
				size: this.options.params.size, // 每页大小
                sizes: [{text: 10}, {text: 20}, {text: 30}],
				no_data: false
			}
		},
		methods: {
	        // 设置每页大小
			setSize (value) { this.size = value }
		},
		created () {
	        console.log(this.type)
			console.log(this.options)
			console.log(this.columns)

//			this.$http.post(this.url, this.params).then(res => {
//
//			}).catch(error => {
//
//			})

		},
		components: {
            drop
		}
	}
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "list.styl";
</style>