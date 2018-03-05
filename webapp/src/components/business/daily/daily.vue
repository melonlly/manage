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
        name: 'daily',
        data () {
            return {
                params: {},
				curName: '', // 当前编辑用户名称
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
                        {
                            name: 'lesson',
                            text: '上 课',
                            component: 'drop',
                            entries: this.ENUM.lesson,
                            readonly: true
                        },{
                            name: 'phone',
                            text: '手 机',
                            component: 'drop',
                            entries: this.ENUM.phone,
                            readonly: true
                        },{
                            name: 'homework',
                            text: '作 业',
                            component: 'drop',
                            entries: this.ENUM.homework,
                            readonly: true
                        },{
                            name: 'daily',
                            text: '考 勤',
                            component: 'drop',
                            entries: this.ENUM.daily,
                            readonly: true
                        },{
                            name: 'clean',
                            text: '卫 生',
                            component: 'drop',
                            entries: this.ENUM.clean,
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
                const _this = this
                this.$http.post('/daily/add', params).then(res => {
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
				this.curName = params.name
                bus.$emit('pop', true, {
                    type: 'VForm',
                    required: true,
                    feilds: [
                        {
                            name: 'lesson',
                            text: '上 课',
                            component: 'drop',
                            entries: this.ENUM.lesson,
                            default: params.lesson,
                            readonly: true
                        },{
                            name: 'phone',
                            text: '手 机',
                            component: 'drop',
                            entries: this.ENUM.phone,
                            default: params.phone,
                            readonly: true
                        },{
                            name: 'homework',
                            text: '作 业',
                            component: 'drop',
                            entries: this.ENUM.homework,
                            default: params.homework,
                            readonly: true
                        },{
                            name: 'daily',
                            text: '考 勤',
                            component: 'drop',
                            entries: this.ENUM.daily,
                            default: params.daily,
                            readonly: true
                        },{
                            name: 'clean',
                            text: '卫 生',
                            component: 'drop',
                            entries: this.ENUM.clean,
                            default: params.clean,
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
                params.id = this.curId
				params.name = this.curName
                const _this = this
                this.$http.post('/daily/update', params).then(res => {
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
                        _this.$http.post('/daily/remove', {
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
            sum (params) {
                let sum = 0
                this.$refs.list.data.forEach(item => {
                    sum += item.clean
					sum += item.daily
					sum += item.homework
					sum += item.lesson
					sum += item.phone
				})
				alert("总分数：" + sum)
			}
        },
        created () {
            let user = this.getItem('user')

            this.options = {
                url: '/daily/list',
                params: {
                    name: user.role == 2 ? user.relation : '',
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
                },{
                    name: 'name',
                    text: '名称'
                },{
                    name: 'lesson',
                    text: '上课',
					enum: 'lesson'
                },{
                    name: 'phone',
                    text: '手机',
					enum: 'phone'
                },{
                    name: 'homework',
                    text: '作业',
					enum: 'homework'
                },{
                    name: 'daily',
                    text: '考勤',
					enum: 'daily'
                },{
                    name: 'clean',
                    text: '卫生',
					enum: 'daily'
                },{
                    name: 'date',
                    text: '日期'
                },{
                    name: '$operate',
                    text: '操作',
                    operate: ['remove', 'editPop']
                }
            ]
            user.role == 2 ? this.columns.pop() : ''

            this.params = this.options.params

            this.required = false
            this.def = this.options.params
            this.feilds = [
                {
                    name: 'name',
                    text: '名 称',
                    default: user.role == 2 ? user.relation : '',
                    readonly: user.role == 2
                },{
                    name: 'lesson',
                    text: '上 课',
                    component: 'drop',
                    entries: this.ENUM.lesson,
                    readonly: true
                },{
                    name: 'phone',
                    text: '手 机',
                    component: 'drop',
                    entries: this.ENUM.phone,
                    readonly: true
                },{
                    name: 'homework',
                    text: '作 业',
                    component: 'drop',
                    entries: this.ENUM.homework,
                    readonly: true
                },{
                    name: 'daily',
                    text: '考 勤',
                    component: 'drop',
                    entries: this.ENUM.daily,
                    readonly: true
                },{
                    name: 'clean',
                    text: '卫 生',
                    component: 'drop',
                    entries: this.ENUM.clean,
                    readonly: true
                },{
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
				},
                {
                    name: '统计',
                    type: 'sum',
                    func: 'sum'
                }
            ]
            user.role == 2 ? this.operates.splice(2, 1) : ''
        },
        components: {
            list, VForm
        }
    }
</script>

<style lang="stylus">

</style>