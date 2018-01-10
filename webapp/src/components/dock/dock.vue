<template>

	<div class="dock">
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
			}
        },
        methods: {
            select (dock) {
                this.active = dock
				this.$emit('select', dock)
			}
        },
        created () {
			this.select(this.docks[this.default])
        },
        components: {
            
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus">
	@import "dock.styl";
</style>