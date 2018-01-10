<template>

	<div class="nvg">
		<ul>
			<li class="center" @mouseover="onNav(index)" :style="{width: 100 / nvgs.length + '%'}" v-for="(nvg, index) in nvgs">
				<a>{{nvg.text}}</a>
				<div class="sub" v-if="nvg.sub">
					<ul>
						<li class="center" v-for="sub in nvg.sub">
							<a>{{sub.text}}</a>
						</li>
					</ul>
				</div>
			</li>
		</ul>
	</div>

</template>

<script type="text/ecmascript-6">
    export default {
        props: ['nvgs'],
        name: 'nvg',
        data () {
            return {
                _index: null
			}
        },
        methods: {
            onNav (index) {
                this._index = index
			}
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