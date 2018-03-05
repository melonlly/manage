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
        name: 'users',
        data () {
            return {
                pop: false,
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
                        {name: 'name', text: '名 称', require: true},
                        {name: 'password', text: '密 码', require: true},
                        {name: 'relation', text: '关系人'},
                        {name: 'phone', text: '手机号'},
                        {
                            name: 'is_admin',
                            text: '管理员',
                            component: 'drop',
                            entries: this.ENUM.isOrNot,
                            readonly: true
                        },
                        {
                            name: 'status',
                            text: '是否有效',
                            component: 'drop',
                            entries: this.ENUM.isOrNot,
                            readonly: true
                        },
                        {
                            name: 'role',
                            text: '角色',
                            component: 'drop',
                            entries: this.ENUM.role,
                            readonly: true
                        }
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
				params.password = this.getMD5(params.password)
				const _this = this
                this.$http.post('/user/add', params).then(res => {
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
                bus.$emit('pop', true, {
                    type: 'VForm',
                    required: true,
                    feilds: [
                        {name: 'name', text: '名 称', value: params.name, require: true},
                        {name: 'password', text: '密 码', require: true},
                        {name: 'relation', text: '关系人', value: params.relation},
                        {name: 'phone', text: '手机号', value: params.phone},
                        {
                            name: 'is_admin',
                            text: '管理员',
                            component: 'drop',
                            entries: this.ENUM.isOrNot,
                            default: params.is_admin + '',
                            readonly: true
                        },
                        {
                            name: 'status',
                            text: '是否有效',
                            component: 'drop',
                            entries: this.ENUM.isOrNot,
                            default: params.status + '',
                            readonly: true
                        },
                        {
                            name: 'role',
                            text: '角色',
                            component: 'drop',
                            entries: this.ENUM.role,
                            default: params.role + '',
                            readonly: true
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
                params.password = this.getMD5(params.password)
				params.id = this.curId
                const _this = this
                this.$http.post('/user/update', params).then(res => {
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
                        _this.$http.post('/user/remove', {
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
            }
        },
        created () {
            let user = this.getItem('user')
            this.options = {
                url: '/user/list',
                params: {
                    name: user.role == 2 ? user.name: '',
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
                {name: 'is_admin', text: '是否管理员', enum: 'isOrNot'},
                {name: 'status', text: '是否有效', enum: 'isOrNot'},
                {name: 'role', text: '角色', enum: 'role'},
                {name: 'relation', text: '关系人'},
                {name: 'phone', text: '手机号'},
				{
                    name: '$operate',
                    text: '操作',
                    operate: ['remove', 'editPop']
                }
            ]

			this.params = this.options.params

            this.required = false
            this.def = this.options.params
            this.feilds = [
                {
                    name: 'name',
					text: '名称',
                    value: user.role == 2 ? user.name : '',
                    readonly: user.role == 2
				},
                {name: 'relation', text: '关系人'},
				{
                    name: 'is_admin',
                    text: '管理员',
                    component: 'drop',
                    entries: this.ENUM.isOrNot,
                    default: '',
                    readonly: true
                },
                {
                    name: 'status',
                    text: '是否有效',
                    component: 'drop',
                    entries: this.ENUM.isOrNot,
                    default: '',
                    readonly: true
                },
                {
                    name: 'role',
                    text: '角色',
                    component: 'drop',
                    entries: this.ENUM.role,
					default: user.role == 2 ? 1: '',
					noDrop: user.role == 2,
                    readonly: true
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
        },
        components: {
            list, VForm
        }
    }
</script>

<style lang="stylus">

</style>