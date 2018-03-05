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

	const getExam = vue => {
        switch (vue.params.exam){
            case '1': return 'week'
            case '2': return 'middle'
            case '3': return 'final'
        }
	}

    export default {
        name: 'score',
        data () {
            return {
                options: {},
                columns: {},
                required: '',
                def: {},
                feilds: {},
                operates: {},
                params: {},
				name: '',
                curId: '' // 当前编辑id
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
                let user = this.getItem('user')
                params.page = 1
                params.size = this.$refs.list.size
                this.params = params
				this.name = params.name
				switch(params.exam){
					case '1':
					    this.options.url = '/week/list'
                        this.columns = [
                            {
                                name: 'id',
                                text: 'id',
                                hide: true
                            },{name: 'name', text: '名称'},
                            {name: 'week1', text: '周1'},
                            {name: 'week2', text: '周2'},
                            {name: 'week3', text: '周3'},
                            {name: 'week4', text: '周4'},
                            {name: 'week5', text: '周5'},
                            {name: 'week6', text: '周6'},
                            {name: 'week7', text: '周7'},
                            {name: 'week8', text: '周8'},
                            {name: 'week9', text: '周9'},
                            {name: 'week10', text: '周10'},
                            {name: 'week11', text: '周11'},
                            {name: 'week12', text: '周12'},
                            {name: 'week13', text: '周13'},
                            {name: 'week14', text: '周14'},
                            {
                                name: '$operate',
                                text: '操作',
                                operate: ['remove', 'editPop']
                            }
                        ]
                        user.role == 2 ? this.columns.pop() : ''
						break
					case '2':
					    this.options.url = '/middle/list'
                        this.columns = [
                            {
                                name: 'id',
                                text: 'id',
                                hide: true
                            },{name: 'name', text: '名称'},
                            {name: 'english', text: '英语'},
                            {name: 'oral', text: '口语'},
                            {name: 'social', text: 'Social'},
                            {name: 'science', text: 'Science'},
                            {name: 'chinese', text: '语文'},
                            {name: 'maths', text: '数学'},
                            {name: 'physical', text: '物理'},
                            {
                                name: '$operate',
                                text: '操作',
                                operate: ['remove', 'editPop']
                            }
                        ]
                        user.role == 2 ? this.columns.pop() : ''
                        break
					case '3':
                        this.options.url = '/final/list'
                        this.columns = [
                            {
                                name: 'id',
                                text: 'id',
                                hide: true
                            },{name: 'name', text: '名称'},
                            {name: 'english', text: '英语'},
                            {name: 'oral', text: '口语'},
                            {name: 'social', text: 'Social'},
                            {name: 'science', text: 'Science'},
                            {name: 'chinese', text: '语文'},
                            {name: 'maths', text: '数学'},
                            {name: 'maths1', text: 'Maths'},
                            {name: 'physical', text: '物理'},
                            {name: 'physical1', text: 'Physical'},
							{name: 'geography', text: '地理'},
                            {name: 'music', text: '音乐'},
                            {
                                name: '$operate',
                                text: '操作',
                                operate: ['remove', 'editPop']
                            }
                        ]
                        user.role == 2 ? this.columns.pop() : ''
                        break
				}
                this.$refs.list.params = params
                this.$refs.list.load()
            },
            // 添加弹窗
            addPop (params) {
                bus.$emit('pop', true, {
                    type: 'VForm',
                    required: true,
                    feilds: (() => {
                        switch(params.exam){
                            case '1':
                                return [
                                    {name: 'name', text: '姓名'},
                                    {name: 'week1', text: '周1'},
                                    {name: 'week2', text: '周2'},
                                    {name: 'week3', text: '周3'},
                                    {name: 'week4', text: '周4'},
                                    {name: 'week5', text: '周5'},
                                    {name: 'week6', text: '周6'},
                                    {name: 'week7', text: '周7'},
                                    {name: 'week8', text: '周8'},
                                    {name: 'week9', text: '周9'},
                                    {name: 'week10', text: '周10'},
                                    {name: 'week11', text: '周11'},
                                    {name: 'week12', text: '周12'},
                                    {name: 'week13', text: '周13'},
                                    {name: 'week14', text: '周14'}
                                ]
                            case '2':
                                return [
                                    {name: 'name', text: '姓名'},
                                    {name: 'english', text: '英语'},
                                    {name: 'oral', text: '口语'},
                                    {name: 'social', text: 'social'},
                                    {name: 'science', text: 'science'},
                                    {name: 'chinese', text: '语文'},
                                    {name: 'maths', text: '数学'},
                                    {name: 'physical', text: '物理'}
                                ]
                            case '3':
                                return [
                                    {name: 'name', text: '姓名'},
                                    {name: 'english', text: '英语'},
                                    {name: 'oral', text: '口语'},
                                    {name: 'social', text: 'Social'},
                                    {name: 'science', text: 'Science'},
                                    {name: 'chinese', text: '语文'},
                                    {name: 'maths', text: '数学'},
                                    {name: 'maths1', text: 'Maths'},
                                    {name: 'physical', text: '物理'},
                                    {name: 'physical1', text: 'Physical'},
                                    {name: 'geography', text: '地理'},
                                    {name: 'music', text: '音乐'}
                                ]
						}
					})(),
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
                this.$http.post('/' + getExam(_this) + '/add', params).then(res => {
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
				this.name = params.name
                bus.$emit('pop', true, {
                    type: 'VForm',
                    required: true,
                    feilds: (() => {
                        switch(this.params.exam){
                            case '1':
                                return [
                                    {name: 'week1', text: '周1', value: params.week1},
                                    {name: 'week2', text: '周2', value: params.week2},
                                    {name: 'week3', text: '周3', value: params.week3},
                                    {name: 'week4', text: '周4', value: params.week4},
                                    {name: 'week5', text: '周5', value: params.week5},
                                    {name: 'week6', text: '周6', value: params.week6},
                                    {name: 'week7', text: '周7', value: params.week7},
                                    {name: 'week8', text: '周8', value: params.week8},
                                    {name: 'week9', text: '周9', value: params.week9},
                                    {name: 'week10', text: '周10', value: params.week10},
                                    {name: 'week11', text: '周11', value: params.week11},
                                    {name: 'week12', text: '周12', value: params.week12},
                                    {name: 'week13', text: '周13', value: params.week13},
                                    {name: 'week14', text: '周14', value: params.week14}
                                ]
                            case '2':
                                return [
                                    {name: 'english', text: '英语', value: params.english},
                                    {name: 'oral', text: '口语', value: params.oral},
                                    {name: 'social', text: 'Social', value: params.social},
                                    {name: 'science', text: 'Science', value: params.science},
                                    {name: 'chinese', text: '语文', value: params.chinese},
                                    {name: 'maths', text: '数学', value: params.maths},
                                    {name: 'physical', text: '物理', value: params.physical}
                                ]
                            case '3':
                                return [
                                    {name: 'english', text: '英语', value: params.english},
                                    {name: 'oral', text: '口语', value: params.oral},
                                    {name: 'social', text: 'Social', value: params.social},
                                    {name: 'science', text: 'Science', value: params.science},
                                    {name: 'chinese', text: '语文', value: params.chinese},
                                    {name: 'maths', text: '数学', value: params.maths},
                                    {name: 'maths1', text: 'Maths', value: params.maths1},
                                    {name: 'physical', text: '物理', value: params.physical},
                                    {name: 'physical1', text: 'Physical', value: params.physical1},
                                    {name: 'geography', text: '地理', value: params.geography},
                                    {name: 'music', text: '音乐', value: params.music}
                                ]
                        }
                    })(),
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
				params.name = this.name
                const _this = this
                this.$http.post('/' + getExam(_this) + '/update', params).then(res => {
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
                        _this.$http.post('/' + getExam(_this) + '/remove', {
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
                url: '/week/list',
                params: {
                    name: user.role == 2 ? user.relation : '',
					exam: "1",
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
				{name: 'week1', text: '周1'},
				{name: 'week2', text: '周2'},
				{name: 'week3', text: '周3'},
                {name: 'week4', text: '周4'},
                {name: 'week5', text: '周5'},
                {name: 'week6', text: '周6'},
                {name: 'week7', text: '周7'},
                {name: 'week8', text: '周8'},
                {name: 'week9', text: '周9'},
                {name: 'week10', text: '周10'},
                {name: 'week11', text: '周11'},
                {name: 'week12', text: '周12'},
                {name: 'week13', text: '周13'},
                {name: 'week14', text: '周14'},
				{
                    name: '$operate',
                    text: '操作',
                    operate: ['remove', 'editPop']
                }
            ]
            user.role == 2 ? this.columns.pop() : ''

            this.params = this.options.params
			this.name = this.options.params.name

            this.required = false
            this.def = this.options.params
            this.feilds = [
                {
                    name: 'name',
					text: '名称',
                    default: user.role == 2 ? user.relation : '',
                    readonly: user.role == 2
				},
				{
                    name: 'exam',
                    text: '考试',
                    component: 'drop',
                    entries: this.ENUM.exam,
                    default: '1',
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
            user.role == 2 ? this.operates.pop() : ''
        },
        components: {
            list, VForm
        }
    }
</script>

<style lang="stylus">

</style>