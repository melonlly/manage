<template>

	<div class="main">
		<VForm style="height: 20%" :required="required" :def="def" :feilds="feilds" :operates="operates"></VForm>
		<list style="height: 80%" ref="list" :options="options" :columns="columns" @filter="filter" @before="before" @after="after"></list>
	</div>

</template>

<script type="text/ecmascript-6">
    import list from 'components/list/list'
    import VForm from 'components/VForm/VForm'
    import bus from 'components/bus/bus'

    export default {
        name: 'news',
        data () {
            return {
                username: '',
                params: {},
                curId: '' // 当前编辑用户id
			}
        },
        methods: {
            before () {},
            after (res) {},
            // 过滤处理数据集
            filter (data) {
                const _this = this
                data.forEach(item => {

                })
                this.data = data
                return data
            },
            // list查询
            search (params) {
                params.page = 1
                params.size = this.$refs.list.size
                this.params = params
                this.$refs.list.params = params
                this.$refs.list.load()
            },
			// 添加弹窗
			addPop (params) {
                bus.$emit('pop', true, {
                    type: 'VForm',
                    required: true,
                    feilds: [
                        {name: 'name', text: '名 称', require: true, br: true},
                        {name: 'content', text: '内 容', require: true, tag: 'textarea', br: true}
                    ],
                    operates: [
                        {
                            name: '添加',
                            type: 'add',
                            func: 'add'
                        }
                    ]
                }, 'component')
			},
			// 添加
			add (params) {
				bus.$emit('pop', false)
                const _this = this
				params.publisher = _this.username
                this.$http.post('/news/add', params).then(res => {
                    alert('添加成功！');
                }).always((res, error) => {
                    if(error){
                        alert('添加失败！');
                        console.log(error)
                    }else{
                        _this.$refs.list.params = _this.params
                        _this.$refs.list.load()
                    }
                })
			},
            // 修改弹窗
            editPop (params) {
                this.curId = params.id
                console.log(params)
                bus.$emit('pop', true, {
                    type: 'VForm',
                    required: true,
                    feilds: [
                        {
                            name: 'name',
							text: '名 称',
							value: params.name,
							require: true,
							br: true
						},
                        {
                            name: 'content',
							text: '内 容',
                            value: params.content,
							require: true,
							tag: 'textarea',
							br: true
                        }
                    ],
                    operates: [
                        {
                            name: '修改',
                            type: 'update',
                            func: 'edit'
                        }
                    ]
                }, 'component')
            },
			edit (params) {
                bus.$emit('pop', false)
                params.id = this.curId
                const _this = this
				params.updater = _this.username
                this.$http.post('/news/update', params).then(res => {
                    alert('修改成功！');
                }).always((res, error) => {
                    if(error){
                        alert('修改失败！');
                        console.log(error)
                    }else{
                        _this.$refs.list.params = _this.params
                        _this.$refs.list.load()
                    }
                })
			},
			remove (params) {
                const _this = this
                bus.$emit('pop', true, {
                    text: '是否确认删除？',
                    okFunc () {
                        _this.$http.post('/news/remove', {
                            id: params.id
                        }).then(res => {
                            alert('删除成功！');
                        }).always((res, error) => {
                            if(error){
                                alert('删除失败！');
                                console.log(error)
                            }else{
                                _this.$refs.list.params = _this.params
                                _this.$refs.list.load()
                            }
                        })
                    }
                }, 'confirm')
			},
            detail (params) {
                bus.$emit('pop', true, {
                    title: params.name,
					date: params.create_time,
                    text: params.content,
					width: '1000px',
					height: '500px'
                }, 'alter')
			}
        },
        created () {
            let user = this.getItem('user')
            this.username = user.name

            this.options = {
                url: '/news/list',
                params: {
                    page: 1,
                    size: 10
                },
                isPage: true,
            }
            this.columns = [
                {
                    name: 'id',
                    text: 'id',
                    hide: true
                },{name: 'name', text: '名称'},
				{name: 'create_time', text: '创建时间'},
                {name: 'update_time', text: '更新时间'},
                {name: 'publisher', text: '发布者'},
                {name: 'updater', text: '更新者'},
				{name: 'content', text: '内容'},
                {
                    name: '$operate',
                    text: '操作',
                    operate: ['remove', 'editPop', 'detail']
                }
            ]
            user.role == 2 ? this.columns[this.columns.length - 1].operate.splice(0, 2) : ''

            this.params = this.options.params

			this.required = false
            this.def = this.options.params
            this.feilds = [
                {name: 'name', text: '名 称'},
                {name: 'publisher', text: '发 布 者'},
                {name: 'updater', text: '更 新 者'},
                {
                    name: 'start',
                    text: '开始日期',
                    component: 'datepicker'
                },{
                    name: 'end',
                    text: '结束日期',
                    component: 'datepicker'
                }
            ]
            this.operates = [
                {
                    name: '查询',
                    type: 'search',
                    func: 'search',
                    auto: true
                },
                {
                    name: '重置',
                    type: 'reset'
                },
                {
                    name: '添加',
                    type: 'add',
					func: 'addPop'
                }
            ]
            user.role == 2 ? this.operates.pop() : ''
        },
        components: {
            list, VForm
        }
    }
</script>

<style lang="stylus">

</style>