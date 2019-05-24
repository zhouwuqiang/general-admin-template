package com.java.general.config.security;

import com.java.general.config.security.handler.AjaxAuthFailHandler;
import com.java.general.config.security.handler.AjaxAuthSuccessHandler;
import com.java.general.config.security.service.CustomPasswordEncoder;
import com.java.general.config.security.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/6 13:02
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserService customUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//配置权限
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()//配置登录表
                .formLogin()
                .loginPage("/view/login?error=0").permitAll()
                .successHandler(new AjaxAuthSuccessHandler())
                .failureHandler(new AjaxAuthFailHandler())
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()//配置注销
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/view/login?error=1").permitAll()
                .deleteCookies("myCookie")
                .and()
                .csrf().disable() //禁用csrf
                .headers().frameOptions().sameOrigin();

        //只允许登录1个用户
//        http.sessionManagement().invalidSessionUrl("/view/login").maximumSessions(1).maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry);
    }

    /**
     * 忽略静态资源拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }


//    /**
//     * 内存数据认证
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication().passwordEncoder(new CustomPasswordEncoder())
//                .withUser("admin").password("admin").roles("USER");
//    }

    /**
     * 配置认证方式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(new CustomPasswordEncoder());
    }


    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * session 保存
     * @return
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
