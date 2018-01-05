<template>
	<div class="drop">
		<strong>{{text}}<i @click="showUl"></i></strong>
		<ul class="drop_ul" v-show="show">
			<li @click="select(item)" v-for="item in entries"><a href="javascript:;">{{item.text}}</a></li>
		</ul>
	</div>
</template>

<script type="text/ecmascript-6">
    export default {
        props: ['entries', 'selected'],
        name: 'drop',
        data () {
            return {
                show: false,
				text: '',
				value: ''
			}
        },
        methods: {
            showUl () {
                this.show = !this.show
			},
            select (item) {
                this.text = item.text
				this.value = item.value
                this.selected(this.value)
			}
        },
        created () {
            
        },
        components: {
            
        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "drop.styl";
</style>