<template>

</template>

<script type="text/ecmascript-6">
	import Vue from 'vue'

    export default new Vue({
        name: 'bus',
        data () {
            return {
                index: '',
                sub_index: ''
            }
        },
        methods: {
            choose (data) {
                console.log('bus.vue', data)
                this.index = data.index
                this.sub_index = data.sub_index
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