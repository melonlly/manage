<template>

	<div class="nvg">
		<ul>
			<li class="center" :style="{width: 100 / nvgs.length + '%'}" v-for="nvg in nvgs">
				<a>{{nvg.text}}</a>

			</li>
		</ul>
	</div>

</template>

<script type="text/ecmascript-6">
    export default {
        props: ['nvgs'],
        name: 'nvg',
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
	@import "nvg.styl";
</style>