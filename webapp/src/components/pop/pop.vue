<template>

	<div class="pop center" @click="close($event)">
		<template v-if="content && popType">
			<div class="content" v-if="popType === 'component'">
				<!--<div class="title"></div>-->
				<component style="height: 100%" :is="content.type"
						   :require="content.required"
						   :def="content.def"
						   :feilds="content.feilds"
						   :operates="content.operates"
				></component>
			</div>
			<div class="confirm" v-else-if="popType === 'confirm'">
				<div class="text">
					{{content.text}}
				</div>
				<div class="btn">
					<span class="ok" @click="ok">确认</span>
					<span class="no" @click="no">取消</span>
				</div>
			</div>
			<div class="alter" :style="{ width: content.width, height: content.height }" v-else-if="popType === 'alter'">
				<div class="title">
					<span class="text">{{content.title}}</span>
					<span class="date" v-if="content.date">{{content.date}}</span>
				</div>
				<div class="text">
					{{content.text}}
				</div>
			</div>
		</template>
	</div>

</template>

<script type="text/ecmascript-6">
    import VForm from 'components/VForm/VForm'
    import bus from 'components/bus/bus'

    export default {
        props: ['content', 'popType'],
        name: 'pop',
        data () {
            return {}
        },
        methods: {
			add (params) {
                this.$parent.add(params)
            },
            edit (params) {
			    console.log(params)
                this.$parent.edit(params)
            },
			close (e) {
			    if(e.target.getAttribute('class') === 'pop center'){
                    bus.$emit('pop', false)
                }
			},
			ok () {
                bus.$emit('pop', false)
				if(this.content.okFunc){
                    this.content.okFunc()
				}
			},
			no () {
                bus.$emit('pop', false)
                if(this.content.noFunc){
                    this.content.noFunc()
                }
			}
        },
        created () {

        },
        components: {
            VForm
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scope>
	@import "../../common/stylus/mixin.styl";
	@import "pop.styl";
</style>