<template>

	<div class="VForm">
		<div class="feilds">
			<template v-for="feild in _feilds">
				<div :class="feild.br ? 'feild-cpt' : 'feild-cpt center'" v-if="feild.component">
					<span class="text">{{feild.text}}:</span>
					<component class="cpt" :is="feild.component"
							   :feild="feild.name"
							   :entries="feild.entries"
							   :width="feild.width"
							   :default="feild.default"
							   :value="feild.value"
							   :noDrop="feild.noDrop"
							   :readonly="feild.readonly"
							   @setValue="setValue"

							   :date="startTime"
							   :limit="limit"
					></component>
				</div>
				<div :class="feild.br ? 'feild-input' : 'feild-input center'" v-else-if="!feild.tag">
					<span class="text">{{feild.text}}:</span>
					<input class="input" type="text" :readonly="feild.readonly" :value="feild.value" @input="setValue(feild, $event)">
				</div>
				<div :class="feild.br ? 'feild-text' : 'feild-text center'" v-else>
					<span class="text">{{feild.text}}:</span>
					<textarea @input="setValue(feild, $event)" :value="feild.value"></textarea>
				</div>
			</template>
		</div>
		<div class="operates">
			<div class="operate" @click="doIt(operate)" v-for="operate in _operates">
				<a :class="operate.type">{{operate.name}}</a>
			</div>
		</div>
	</div>

</template>

<script type="text/ecmascript-6">
	import drop from 'components/drop/drop'
	import datepicker from 'components/datepicker/datepicker'

    export default {
        props: ['required', 'def', 'feilds', 'operates'],
        name: 'VForm',
        data () {
            return {
                params: this.def || {},
                startTime: {},
				limit: []
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
				        _this.$parent.search(_this.params)
//                        _this.$emit('search', _this.params)
					}
				})
			},
			// 操作按钮事件
            doIt (operate) {
                let _this = this
                if(_this.required){
                    // 校验必填字段
                    _this.feilds.every(feild => {
					    if(feild.require){
                            if(_this.params[feild.name]){
                                return true
							}else {
                                alert(feild.text + '为必填项！')
                                return false
							}
						}else{
					        return true
						}
					})
                    return false
                }
				if(operate.type && operate.type !== 'reset'){
                    _this.$parent[operate.func](_this.params)
//                    _this.$emit(operate.func, _this.params) // 调用按钮对应的事件
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
            drop, datepicker
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "VForm.styl";
</style>