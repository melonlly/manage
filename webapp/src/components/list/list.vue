<template>
	<div class="main">
		<div class="table_box">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th v-for="th in columns" v-if="!th.hide">{{th.text}}</th>
				</tr>
				<tr v-for="tr in data">
					<td v-for="(value, key) in tr" v-if="!columns.find(item => item.name === key).hide">{{value}}</td>
					<td v-if="hasOperate()">
						<a class="icon_edit" v-if="operate('edit')"></a>
						<a class="icon_remove" v-if="operate('remove')"></a>
						<a class="icon_export" v-if="operate('export')"></a>
					</td>
				</tr>
			</table>
		</div>
		<div class="no_data" v-if="no_data">
			<i></i>
			<p>暂无数据</p>
		</div>
		<div class="pages" v-if="isPage">
			<div class="num">
				<span>每页显示</span>
				<drop :entries="sizes" :width="'100px'" :default="sizes[0]" @selected="setSize"></drop>
			</div>
			<div class="stat">
				<span class="indexs">
					<a class="pre" @click.self.prevent="pre" v-if="index > 1"></a>
					<template v-if="pages <= 6">
						<em @click="toPage(i)" v-for="i in [1,2,3,4,5,6]" :class="{active: index === i}" v-if="pages > i - 1">{{i}}</em>
					</template>
					<template v-else>
						<template v-if="index < 4">
							<em @click="toPage(i)" v-for="i in [1,2,3]" :class="{active: index === i}">{{i}}</em>
							<span>...</span>
						</template>
						<template v-else-if="pages - index >= 5">
							<em @click="toPage(i)" v-for="i in [index - 1,index,index + 1]" :class="{active: index === i}">{{i}}</em>
							<span>...</span>
						</template>
						<template v-else>
							<em @click="toPage(i)" v-for="i in [pages - 5,pages - 4,pages - 3]" :class="{active: index === i}">{{i}}</em>
						</template>
						<em @click="toPage(i)" v-for="i in [pages - 2,pages - 1,pages]" :class="{active: index === i}">{{i}}</em>
					</template>
					<a class="next" @click.self.prevent="next" v-if="index < pages"></a>
				</span>
				<span>跳转至</span>
				<input type="number" class="text" ref="target"/>
				<span>页</span>
				<a class="enter" @click.self.prevent="toPage($refs.target.value)">确定</a>
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
                isPage: this.options.isPage, // 是否分页
                total: 0, // 总行数
                pages: 0, // 总页数
				index: this.options.params.index, // 当前页码
				size: this.options.params.size, // 每页大小
                sizes: [{text: 10}, {text: 20}, {text: 30}],
				no_data: false,
                data: []
			}
		},
		methods: {
	        // 设置每页大小
			setSize (value) {
			    this.size = value
                this.load()
			},
			// 是否有操作项
			hasOperate () {
                return this.columns.find(item => item.name === '$operate')
			},
			// 操作
			operate (op) {
                return this.columns.find(item => item.name === '$operate').operate.indexOf(op) > -1
			},
			// 上一页
			pre () {
                this.index = this.index - 1
                this.load()
			},
			// 下一页
			next () {
			    this.index = this.index + 1
                this.load()
			},
			// 跳转到某一页
            toPage (page) {
				if(page){
                    this.index = parseInt(page)
                    this.load()
				}else{
				    alert('请先输入跳转的页数')
				}
			},
			load () {
                this.$emit('before')
                this.$http.post(this.url, this.params).then(res => {
                    console.log(res)
                    if(res.code === this.ERR_OK){
                        this.data = res.data
                        if(this.data){
                            this.data = this.$emit('filter', this.data)
                            this.data = this.$parent.data
                        }else{
                            this.no_data = true
						}
                    }
                    this.$emit('after', res)
                }).catch(error => {
                    alert('请求异常')
                })
			}
		},
		created () {
            this.load()
		},
		components: {
            drop
		}
	}
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "list.styl";
</style>