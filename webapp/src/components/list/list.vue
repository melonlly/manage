<template>
	<div class="list">
		<div class="table_box">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th v-for="th in columns" v-if="!th.hide">{{th.text}}</th>
				</tr>
				<tr v-for="tr in data">
					<td v-for="th in columns" v-if="th.name !== '$operate' && !th.hide">
						{{th.enum ? enumToValue(th.enum, tr[th.name]) : tr[th.name]}}
					</td>
					<td v-if="hasOperate()">
						<a class="icon_detail" v-if="operate('detail')" @click="doIt('detail', tr)"></a>
						<a class="icon_edit" v-if="operate('editPop')" @click="doIt('editPop', tr)"></a>
						<a class="icon_remove" v-if="operate('remove')" @click="doIt('remove', tr)"></a>
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
				<drop :feild="'size'" :entries="sizes" :width="'100px'" :default="'10'" :readonly="true" @setValue="setSize"></drop>
			</div>
			<div class="stat">
				<span class="indexs">
					<a class="pre" @click.self.prevent="pre" v-if="page > 1"></a>
					<template v-if="pages <= 6">
						<em @click="toPage(i)" v-for="i in [1,2,3,4,5,6]" :class="{active: page === i}" v-if="pages > i - 1">{{i}}</em>
					</template>
					<template v-else>
						<template v-if="page < 4">
							<em @click="toPage(i)" v-for="i in [1,2,3]" :class="{active: page === i}">{{i}}</em>
							<span>...</span>
						</template>
						<template v-else-if="pages - page >= 5">
							<em @click="toPage(i)" v-for="i in [page - 2,page - 1,page]" :class="{active: page === i}">{{i}}</em>
							<span>...</span>
						</template>
						<template v-else>
							<em @click="toPage(i)" v-for="i in [pages - 5,pages - 4,pages - 3]" :class="{active: page === i}">{{i}}</em>
						</template>
						<em @click="toPage(i)" v-for="i in [pages - 2,pages - 1,pages]" :class="{active: page === i}">{{i}}</em>
					</template>
					<a class="next" @click.self.prevent="next" v-if="page < pages"></a>
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
	    props: {
	        type: {
	            type: String,
				default: 'table'
			},
			options: {
	            type: Object
			},
			columns: {
				type: Array
			}
		},
		name: 'list',
		data () {
			return {
			    url: this.options.url, // 请求链接
                params: this.options.params, // 请求参数
                isPage: this.options.isPage, // 是否分页
                total: "", // 总行数
                pages: "", // 总页数
				page: this.options.params.page, // 当前页码
				size: this.options.params.size, // 每页大小
                sizes: [{text: '10', value: '10'}, {text: '20', value: '20'}, {text: '30', value: '30'}],
				no_data: false,
                data: []
			}
		},
		methods: {
	        // 设置每页大小
			setSize (size) {
				this.params[size.name] = size.value
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
			doIt (op, data) {
				this.$parent[op](data)
			},
			// 上一页
			pre () {
                this.page = this.page - 1
                this.load()
			},
			// 下一页
			next () {
			    this.page = this.page + 1
                this.load()
			},
			// 跳转到某一页
            toPage (toPage) {
				if(toPage){
                    this.page = parseInt(toPage)
                    this.load()
				}else{
				    alert('请先输入跳转的页数')
				}
			},
			load () {
                this.$emit('before')
				this.url = this.options.url
				this.params = this.options.params
                this.$http.post(this.url, this.params).then(res => {
                    this.data = res.getData('list')
                    if(this.data){
                        this.data = this.$emit('filter', this.data)
                        this.data = this.$parent.data
                        this.total = parseInt(res.data.total)
                        this.pages = parseInt(res.data.pages)
                        this.page = parseInt(res.data.page)
                        this.size = parseInt(res.data.size)
                    }else{
                        this.no_data = true
                    }
                    this.$emit('after', res)
                }).catch(error => {
                    console.log(error)
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
	@import "../../common/stylus/mixin.styl";
	@import "list.styl";
</style>