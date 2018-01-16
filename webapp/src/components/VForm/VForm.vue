<template>

	<div class="VForm">
		<div class="feild">
			<template v-for="feild in _feilds">
				<div v-if="feild.component">
					<span>{{feild.text}}:</span>
					<component :is="feild.component"
							   :feild="feild.name"
							   :entries="feild.entries"
							   :width="feild.width"
							   :default="feild.default"
							   :value="feild.value"
							   :readonly="feild.readonly"
							   @setValue="setValue"
					></component>
				</div>
				<div v-else>
					<span>{{feild.text}}:</span>
					<input type="text" :value="feild.value || feild.default || ''">
				</div>
			</template>
		</div>
		<div class="operate">
			<template v-for="operate in _operates">
				<button @click="doIt(operate)" :class="operate.type">{{operate.name}}</button>
			</template>
		</div>
	</div>

</template>

<script type="text/ecmascript-6">
	import drop from 'components/drop/drop'

    export default {
        props: ['def', 'feilds', 'operates'],
        name: 'VForm',
        data () {
            return {
                param: this.def || {},
				_feilds: this.feilds,
				_operates: this.operates
			}
        },
        methods: {
            // 字段赋值
            setValue (feild) {
                this.param[feild.name] = feild.value

			},
			// 操作按钮事件
            doIt (operate) {
				if(operate.func && operate.type !== 'reset'){
                    this.$emit(operate.func, {}) // 调用按钮对应的事件
				}else{
				    // 表单重置，分别调用表单组件的reset
				    if(operate.type === 'reset'){
						this._feilds.forEach(feild => {
                            console.log(feild.value, feild.default)
                            feild.value = feild.default || ''
                        })
					}
				}
			}
        },
        created () {
            this._feilds = this.feilds
			this._operates = this.operates
        },
        components: {
            drop
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "VForm.styl";
</style>