<template>
	<div class="main">
		<div class="table_box">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th v-for="th in columns" v-if="!th.hide">{{th.text}}</th>
				</tr>
				<tr v-for="tr in datalist">
					<td v-for="(value, key) in tr" v-if="!columns.find(item => item.name === key).hide">{{value}}</td>
				</tr>
			</table>
		</div>
		<div class="no_data" v-show="no_data">
			<i></i>
			<p>暂无数据</p>
		</div>
		<div class="pages" v-show="pages">
			<div class="num">
				<span>每页显示</span>
				<drop :entries="sizes" :width="'100px'" :default="sizes[0]" @selected="setSize"></drop>
			</div>
			<div class="stat">
				<span class="indexs">
					<a href="javascript:;" class="pre"></a>
					<em class="active">1</em>
					<em>2</em>
					<em>3</em>
					<span style="margin: 0 10px">...</span>
					<em>6</em>
					<em>7</em>
					<em>8</em>
					<a href="javascript:;" class="next"></a>
				</span>
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
	import oprate from 'components/oprate/oprate'

	export default {
	    props: ['type', 'options', 'columns'],
		name: 'list',
		data () {
			return {
			    url: this.options.url, // 请求链接
                params: this.options.params, // 请求参数
                pages: this.options.pages, // 是否分页
                totalRows: 0, // 总行数
                totalPages: 0, // 总页数
				index: this.options.params.index, // 当前页码
				size: this.options.params.size, // 每页大小
                sizes: [{text: 10}, {text: 20}, {text: 30}],
				no_data: false,
				datalist: []
			}
		},
		methods: {
	        // 设置每页大小
			setSize (value) { this.size = value },
			// 是否隐藏字段
            hideCol (key) {
			    console.log(key)
//			    return !this.columns.find(item => item.name === key).hide
			}
		},
		created () {
	        console.log(this.type)
			console.log(this.options)
			console.log(this.columns)

//			this.$http.post(this.url, this.params).then(res => {
//				this.datalist = res.datalist
//				if(this.datalist){
//                    this.datalist = this.$emit('filter', this.datalist)
//				}
//			}).catch(error => {
//
//			})

			this.datalist = [{
	            col1: "1",
                col2: "2",
                col3: "3",
			}, {
                col1: "3",
                col2: "2",
                col3: "1",
			}]
            this.$emit('filter', this.datalist)
			console.log(this.$parent.datalist)
			this.datalist = this.$parent.datalist
			console.log(this.datalist)
		},
		components: {
            drop,
            oprate
		}
	}
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "list.styl";
</style>