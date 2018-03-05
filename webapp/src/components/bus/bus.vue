<template>

</template>

<script type="text/ecmascript-6">
	import Vue from 'vue'

    export default new Vue({
        name: 'bus',
        data () {
            return {
                index: '',
                sub_index: '',
				url: '',
				showPop: false
            }
        },
        methods: {
            choose (data) {
                this.index = data.index
                this.sub_index = data.sub_index
				this.url = data.url
            },
			pop (show, content, type){
                this.showPop = show
			}
        },
        created () {

        },
        components: {

        }
    })
</script>

<style>

</style>