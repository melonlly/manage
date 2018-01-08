package com.manage.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity //开启spring security的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //security安全配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置spring 默认加载iframe
        http.headers().frameOptions().disable();
        http
                .authorizeRequests()
                //默认不受限制的访问资源路径
                .antMatchers("/login","/style.css","/laycss/*","/js/*","/image/*","/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //登录页面
                .loginPage("/login")
                //登陆错误页面
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    @Autowired
    //设置系统默认的登录用户名和密码以及角色
    //AuthenticationManagerBuilder 验证管理或生成的工厂类
    //建立系统使用者信息( principal )的过程。使用者可以是一个用户、设备、或者其他可以在我们的应用中执行某种操作的其他系统
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("administrator").password("111111").roles("USER");
    }

    //设置系统注销后跳转指定页面
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}