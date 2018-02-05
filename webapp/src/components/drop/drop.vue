<template>
	<div class="drop" :style="{ width: width }" @mouseleave="leave">
		<div class="input" @click="showUl">
			<input type="text" :value="current.text" :readonly="readonly"><i :class="{ open: show }"></i>
		</div>
		<transition name="drop">
			<ul class="drop_ul" v-show="show">
				<li @click="select(item)" v-for="item in entries"><a>{{item.text}}</a></li>
			</ul>
		</transition>
	</div>
</template>

<script type="text/ecmascript-6">
    export default {
        props: ['feild', 'entries', 'width', 'default', 'value', 'readonly'],
        name: 'drop',
        data () {
            return {
                show: false,
				current: {
                    text: '',
                    _value: ''
				}
			}
        },
        methods: {
            // 是否显示下拉
            showUl () {
                this.show = !this.show
			},
			// 鼠标离开下拉组件，隐藏下拉框
			leave () {
                this.show = false
			},
            select (item) {
                this.current = {
                    text: item.text,
                    value: item.value || item.text
				}
				this.show = false
//                this.$parent[this.feild] = this.value
				this.$emit('setValue', {
                    name: this.feild,
					value: this.current.value
				})
			},
			// 重置（置为空或置为默认值）
			reset () {
				let item = this.entries.find(item => item.value === this.default)
                this.current = {
                    text: item ? item.text : this.default || '',
                    value: this.value || this.default || ''
                }
			}
        },
        created () {
            this.reset()
        },
        components: {
            
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "../../common/stylus/mixin.styl";
	@import "drop.styl";
</style>