<template>
	<div class="drop" :style="{ width: width }" @mouseleave="leave">
		<strong @click="showUl">{{text}}<i :class="{ close: show }"></i></strong>
		<transition name="drop">
			<ul class="drop_ul" v-show="show">
				<li @click="select(item)" v-for="item in entries"><a href="javascript:;">{{item.text}}</a></li>
			</ul>
		</transition>
	</div>
</template>

<script type="text/ecmascript-6">
    export default {
        props: ['entries', 'width', 'default'],
        name: 'drop',
        data () {
            return {
                show: false,
				text: this.default ? this.default.text : '',
				value: this.default ? (this.default.value || this.default.text) : ''
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
				this.value = item.value || item.text
				this.show = false
			}
        },
        created () {

        },
		mounted () {

		},
        components: {
            
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "drop.styl";
</style>