<template>

	<div class="dock">
		<ul>
			<li :style="{width: 72 / docks.length + '%'}" v-for="dock in docks">{{dock}}</li>
		</ul>
	</div>

</template>

<script type="text/ecmascript-6">
    export default {
        props: ['docks'],
        name: 'dock',
        data () {
            return {}
        },
        methods: {
            
        },
        created () {
            
        },
        components: {
            
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus">
	@import "dock.styl";
</style>