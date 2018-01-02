<template>

	<div id="app" :style="{ height: height() }">

		<div class="header">
			<div class="logo">logo</div>
			<div class="nav-sub">nav-sub</div>
			<div class="user">user</div>
		</div>

		<div class="main">
			<div class="filter">filter</div>
			<div class="content">content</div>
		</div>

		<div class="footer">
			<div class="nav-main">nav-main</div>
		</div>

	</div>

</template>

<script type="text/ecmascript-6">
export default {
    name: 'app',
    data () {
        return {}
    },
    methods: {
		// 计算页面高度
        height () {
            return window.innerHeight + "px"
        }
    },
    created () {

    },
    components: {
        
    }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
	@import "app.styl";
</style>