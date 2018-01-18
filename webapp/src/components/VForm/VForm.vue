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
					<input type="text" :value="feild.value" @input="setValue(feild, $event)">
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
                params: this.def || {}
			}
        },
        methods: {
            // 字段赋值
            setValue (feild, e) {
                let _this = this
				_this._feilds.forEach(_feild => {
                    if(_feild.name === feild.name){
                        if(_feild.component){
                            _this.params[feild.name] = feild.value
                        }else{
                            _this.params[feild.name] = e.target.value
                            _feild.value = e.target.value
                        }
                    }
				})
                _this._operates.forEach(operate => {
				    if(operate.func === 'search' && operate.auto){
                        _this.$emit('search', _this.params)
					}
				})
			},
			// 操作按钮事件
            doIt (operate) {
                let _this = this
				if(operate.func && operate.type !== 'reset'){
                    _this.$emit(operate.func, _this.params) // 调用按钮对应的事件
				}else{
				    // 表单重置，分别调用表单组件的reset
				    if(operate.type === 'reset'){
                        _this._feilds.forEach(feild => {
							if(!feild.component){
                                feild.value = feild.default || ''
							}
                        })
                        _this.$children.forEach(cpt => {
                            cpt.reset()
                        })
					}
				}
			}
        },
        created () {
            this.feilds.forEach(feild => {
                feild.value = feild.value || feild.default || ''
                this.params[feild.name] = feild.value
            })
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