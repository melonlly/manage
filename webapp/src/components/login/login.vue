<template>

	<div id="login" :style="{ height: $height() }">
		<div class="login-box">
			<div class="user">
				<div class="account">
					<i>iiiiii</i>
					<input type="text">
				</div>
				<div class="password">
					<i>iiiiii</i>
					<input type="text">
				</div>
			</div>
			<div class="login-in">
				Login in
			</div>
		</div>
	</div>

</template>

<script type="text/ecmascript-6">
export default {
    name: 'login',
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
	@import "login.styl";
</style>