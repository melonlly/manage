<template>

	<div class="dock">
		<transition name="tip" mode="out-in">
			<a class="center up" v-show="showTip" @click.prevent="showDock"><i>^</i></a>
		</transition>
		<ul>
			<li @click="select(dock)" :class="{active: dock === active}" class="center wave" :style="{width: 72 / docks.length + '%'}" v-for="dock in docks">
				{{dock.text}}
			</li>
		</ul>
	</div>

</template>

<script type="text/ecmascript-6">
    export default {
        props: {
            docks: {
                type: Array
			},
			default: {
			    type: Number,
				default: 0 // 默认选中第一个
			}
		},
        name: 'dock',
        data () {
            return {
                active: {}, // 当前选中dock
                showTip: false // 小箭头是否显示
			}
        },
        methods: {
            select (dock) {
                this.active = dock
                this.$emit('select', dock)
            },
            showDock () {
                this.showTip = false
                document.getElementsByClassName('dock')[0].style.marginTop = ''
			},
			hideDock () {
                document.getElementsByClassName('dock')[0].style.marginTop = this.$getAttributes(document.getElementsByClassName('dock')[0], 'height') + 'px'
                this.showTip = true
			}
        },
        created () {

        },
		mounted () {
            this.select(this.docks[this.default])
		},
        components: {
            
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "../../common/stylus/base.styl";
	@import "dock.styl";
</style>