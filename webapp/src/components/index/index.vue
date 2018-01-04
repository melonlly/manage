<template>

	<div id="index" :style="{ height: $height }">

		<div class="header">
			<div class="logo">logo</div>
			<div class="nav-sub">
				<ul>
					<li>nav-sub-1</li>
					<li>nav-sub-2</li>
					<li>nav-sub-3</li>
					<li>nav-sub-4</li>
				</ul>
			</div>
			<div class="user">user</div>
		</div>

		<div class="main">
			<div class="content">content</div>
		</div>

		<div class="footer">
			<div class="nav-main">
				<ul>
					<li>nav-main-1</li>
					<li>nav-main-2</li>
					<li>nav-main-3</li>
					<li>nav-main-4</li>
					<li>nav-main-5</li>
					<li>nav-main-6</li>
				</ul>
			</div>
		</div>

	</div>

</template>

<script type="text/ecmascript-6">
export default {
    name: 'index',
    data () {
        return {}
    },
    methods: {

    },
    created () {

    },
    components: {
        
    }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
	@import "index.styl";
</style>