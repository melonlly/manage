<template>

	<div class="main">
		<VForm height="30%" :def="def" :feilds="feilds" :operates="operates" @search="search" @file="file" @import="v_import" @export="v_export"></VForm>
		<list height="70%" ref="list" :options="options" :columns="columns" @filter="filter" @before="before" @after="after"></list>
	</div>

</template>

<script type="text/ecmascript-6">
    import list from 'components/list/list'
    import VForm from 'components/VForm/VForm'
	import bus from 'components/bus/bus'

    export default {
        name: 'Vmain',
        data () {
            return {
                index: '',
				sub_index: ''
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
                this.$refs.list.params = params
                this.$refs.list.load()
            },
            // 文件上传
            file () {
                console.log('file')
            },
            // 导入
            v_import () {
                console.log('v_import')
            },
            // 导出
            v_export () {
                console.log('v_export')
            }
        },
        created () {
            this.options = {
                url: '/list',
                params: {
                    index: 1,
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
                    name: 'password',
                    text: '密码',
                },{
                    name: 'type',
                    text: '类型',
                },{
                    name: '$operate',
                    text: '操作',
                    operate: ['remove', 'edit']
                }
            ]

            this.def = this.options.params
            this.feilds = [
                {
                    name: 'name',
                    text: '名称',
                    default: '111'
                },{
                    name: 'type',
                    text: '类型',
                    component: 'drop',
                    entries: [{text: '类型1', value: '1'}, {text: '类型2', value: '2'}, {text: '类型3', value: '3'}],
                    default: '1',
                    readonly: true
                },{
                    name: 'password',
                    text: '密码',
                    component: 'drop',
                    entries: [{text: '密码1', value: 'password1'}, {text: '密码2', value: 'password2'}],
                    default: 'password1',
                    readonly: true
                },{
                    name: 'date',
                    text: '日期',
                    component: 'datepicker'
                }
            ]
            this.operates = [
                {
                    name: '查询',
                    type: 'search',
                    func: 'search',
                    auto: true
                },{
                    name: '重置',
                    type: 'reset'
                },{
                    name: '上传',
                    type: 'file',
                    func: 'file'
                },{
                    name: '导入',
                    type: 'import',
                    func: 'import'
                },{
                    name: '导出',
                    type: 'export',
                    func: 'export'
                }
            ]

			bus.$on('choose', data => {
				this.index = data.index
				this.sub_index = data.sub_index
			})
        },
        components: {
            list, VForm
        }
    }
</script>

<style lang="stylus">
	@import "Vmain.styl";
</style>