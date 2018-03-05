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
        name: 'lesson',
        data () {
            return {
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
                        {name: 'semester', text: '学 期', require: true},
                        {name: 'period', text: 'period'},
                        {name: 'time', text: '时间'},
                        {name: 'day1', text: '星期一'},
                        {name: 'day2', text: '星期二'},
                        {name: 'day3', text: '星期三'},
                        {name: 'day4', text: '星期四'},
                        {name: 'day5', text: '星期五'}
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
                this.$http.post('/lesson/add', params).then(res => {
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
                        {name: 'semester', text: '学 期', require: true, value: params.semester},
                        {name: 'period', text: 'period', value: params.period},
                        {name: 'time', text: 'time', value: params.time},
                        {name: 'day1', text: '星期一', value: params.day1},
                        {name: 'day2', text: '星期二', value: params.day2},
                        {name: 'day3', text: '星期三', value: params.day3},
                        {name: 'day4', text: '星期四', value: params.day4},
                        {name: 'day5', text: '星期五', value: params.day5}
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
                this.$http.post('/lesson/update', params).then(res => {
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
                        _this.$http.post('/lesson/remove', {
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
                url: '/lesson/list',
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
                },{name: 'semester', text: '学 期'},
                {name: 'period', text: 'period'},
                {name: 'time', text: '时间'},
                {name: 'day1', text: '星期一'},
                {name: 'day2', text: '星期二'},
                {name: 'day3', text: '星期三'},
                {name: 'day4', text: '星期四'},
                {name: 'day5', text: '星期五'},
				{
                    name: '$operate',
                    text: '操作',
                    operate: ['remove', 'edit']
                }
            ]
            user.role == 2 ? this.columns.pop() : ''

            this.params = this.options.params

            this.required = false
            this.def = this.options.params
            this.feilds = [
                {name: 'semester', text: '学 期'}
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