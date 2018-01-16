<template>

	<div class="nvg">
		<ul>
			<li class="nav center" @mouseover.stop="onNav(index)" @mouseleave="outNav(index)" :style="{width: 100 / nvgs.length + '%'}" v-for="(nvg, index) in nvgs">
				<a class="text">{{nvg.text}}</a>
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
            return {}
        },
        methods: {
            onNav (index) {
                let sub = document.getElementsByClassName('sub')[index]
                sub.style.visibility = ''
                sub.style.marginTop = ''
			},
			outNav (index) {
                let sub = document.getElementsByClassName('sub')[index]
                sub.style.marginTop = '-' + sub.clientHeight + 'px'
			}
        },
        created () {
            
        },
		mounted () {
            let subs = document.getElementsByClassName('sub')
            for(let sub of subs){
                sub.style.visibility = 'hidden'
                sub.style.marginTop = '-' + sub.clientHeight + 'px'
            }
            let texts = document.getElementsByClassName('text')
			for(let text of texts){
                text.style.lineHeight = text.parentNode.clientHeight + 'px'
			}
		},
        components: {
            
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "nvg.styl";
</style>