<template>

	<div class="dock">
		<transition name="tip" mode="out-in">
			<a class="center up" v-show="showTip" @click.prevent="showDock"><i>^</i></a>
		</transition>
		<ul>
			<li @click="select(dock)" :class="{active: dock === active}" class="center" :style="{width: 72 / docks.length + '%'}" v-for="dock in docks">
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
                showTip: true // 小箭头是否显示
			}
        },
        methods: {
            select (dock) {
                this.active = dock
                this.$emit('select', dock)
                document.getElementsByClassName('dock')[0].style.marginTop = document.getElementsByClassName('dock')[0].clientHeight + 'px'
                this.showTip = true
            },
            showDock () {
                this.showTip = false
                document.getElementsByClassName('dock')[0].style.marginTop = ''
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
	@import "dock.styl";
</style>