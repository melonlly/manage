<template>

	<div id="login" :style="{ height: $height() + 'px' }">
		<div class="login-box">
			<div class="title">
				学生管理系统
			</div>
			<div class="user">
				<div class="account">
					<i></i>
					<input v-model="account" type="text">
					<span v-show="!account">!!!</span>
				</div>
				<div class="password">
					<i></i>
					<input v-model="password" type="password">
					<span v-show="!password">!!!</span>
				</div>
			</div>
			<div class="login-in" @keyup.enter="login" @click="login">
				登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
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
					this.$http.post('/login', {
					    account: this.account,
//						password: this.password
						password: this.getMD5(this.password)
					}).then(res => {
					    this.setItem('user', res.getData())
						this.$router.push({
                            name: 'index'
						})
					}).catch(error => {
						console.log(error)
					})
				}
			}
		},
		created () {
			this.removeItem('user')
		},
		components: {

		}
	}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
	@import "login.styl";
</style>