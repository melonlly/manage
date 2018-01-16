<template>
	<div class="drop" :style="{ width: width }" @mouseleave="leave">
		<div class="input" @click="showUl">
			<input type="text" :value="text" :readonly="readonly"><i :class="{ close: show }"></i>
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
				text: this.default ? this.default.text : '',
				_value: this.value || this.default ? (this.default.value || this.default.text) : ''
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
                this.text = item.text
				this._value = item.value || item.text
				this.show = false
//                this.$parent[this.feild] = this.value
				this.$emit('selected', {
                    name: this.feild,
					value: this._value
				})
			},
			// 重置（置为空或置为默认值）
			reset () {
                this.text = this.default ? this.default.text : ''
				this._value = this.default ? (this.default.value || this.default.text) : ''
			}
        },
//		watch : {
//            _value () {
//
//			}
//		},
        created () {

        },
        components: {
            
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "drop.styl";
</style>