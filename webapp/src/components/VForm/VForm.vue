<template>

	<div class="VForm">
		<div class="feild">
			<template v-for="feild in feilds">
				<!--<template v-if="feild.component">-->
					<!--<component :is="feild.component"-->
							   <!--:entries=""-->
							   <!--:width="''"-->
							   <!--:default=""-->
							   <!--:readonly=""-->
							   <!--@selected=""-->
					<!--&gt;</component>-->
				<!--</template>-->
				<!--<template v-else>-->
					{{feild.text}}: <input type="text" :value="feild.default">
				<!--</template>-->
			</template>
		</div>
		<div class="operate">
			<template v-for="operate in operates">
				<button @click="doIt(operate)" :class="operate.type">{{operate.name}}</button>
			</template>
		</div>
	</div>

</template>

<script type="text/ecmascript-6">
    export default {
        props: ['feilds', 'operates'],
        name: 'VForm',
        data () {
            return {}
        },
        methods: {
            doIt (operate) {
                console.log(operate)
				if(operate.func && operate.type !== 'reset'){
                    this.$emit(operate.func, {}) // 调用按钮对应的事件
				}else{
				    // 表单重置，分别调用表单组件的reset
				    if(operate.type === 'reset'){

					}
				}
			}
        },
        created () {
            
        },
        components: {
            
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "VForm.styl";
</style>