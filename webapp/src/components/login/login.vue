<template>

	<div id="login" :style="{ height: $height() }">
		<div class="login-box">
			<div class="title">
				Manage
			</div>
			<div class="user">
				<div class="account">
					<i>iiiiii</i>
					<input v-model="account" type="text">
					<span v-show="!account">!!!</span>
				</div>
				<div class="password">
					<i>iiiiii</i>
					<input v-model="password" type="password">
					<span v-show="!password">!!!</span>
				</div>
			</div>
			<div class="login-in" @click="login">
				Login in
			</div>
		</div>
	</div>

</template>

<script type="text/ecmascript-6">
	export default {
		name: 'login',
		data () {
			return {
				account: '',
				password: ''
			}
		},
		methods: {
			login () {
				if(this.account && this.password){
	//				this.$http.post('/login', data).then(res => {
						this.$router.push({ path: 'index' })
	//				}).catch(error => {
	//
	//				})
				}
			}
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