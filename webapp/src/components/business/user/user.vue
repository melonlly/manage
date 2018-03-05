<template>

	<div class="user">
		<div class="content center">
			<span class="text">
				您好，{{name}}{{role}}
			</span>
			<i class="exit" @click="exit"></i>
		</div>
	</div>

</template>

<script type="text/ecmascript-6">
    export default {
        name: 'user',
        data () {
            return {
                name: '',
				role: ''
			}
        },
        methods: {
            exit () {
                this.removeItem('user')
                this.$router.push({
                    name: 'login'
                })
			}
        },
        created () {
            let user = this.getItem('user')
            if(user){
                this.name = user.name
				this.role = this.enumToValue('role', user.role)
            }else{
                this.$router.push({
                    name: 'login'
                })
            }
        },
        components: {

        }
    }
</script>

<style lang="stylus" ref="stylesheet/stylus" scoped>
	@import "../../../common/stylus/mixin.styl";
	@import "user.styl";
</style>
