package com.master.user.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Classname WebSecurityConfig
 * @Description
 *  authorizeRequests： security全注解配置实现的权限
 *  antMatchers： 拦截路径
 *  permitAll： 全部放行
 *  authenticated： 认证后访问
 *  .and().csrf().disable()：表示使csrf失效
 *
 * @Date 2021/5/8 15:32
 * @Created by Zhao.J
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override protected void configure(HttpSecurity http) throws Exception {

        //匹配的页面，符合限制才可访问
        http
                .authorizeRequests()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/doc.html","/webjars/**","/img.icons/**","/swagger-resources/**","/v2/api-docs").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable(); }

    @Autowired
    public  void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //添加两个账号用来做测试
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("admin"))
                .roles("ADMIN","USER")
                .and()
                .withUser("user")
                .password(new BCryptPasswordEncoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("dev")
                .password(new BCryptPasswordEncoder().encode("dev"))
                .roles("DEV","USER");
    }

}
