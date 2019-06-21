package com.java.general.config.security;

import com.java.general.config.security.handler.AjaxAuthFailHandler;
import com.java.general.config.security.handler.AjaxAuthSuccessHandler;
import com.java.general.config.security.handler.AjaxSessionInformationExpiredStrategy;
import com.java.general.config.security.service.CustomPasswordEncoder;
import com.java.general.config.security.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;

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

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private AjaxAuthFailHandler ajaxauthfailhandler;

    @Autowired
    private AjaxAuthSuccessHandler ajaxAuthSuccessHandler;

    @Autowired
    private LogoutHandler logoutHandler;

    @Autowired
    private AjaxSessionInformationExpiredStrategy ajaxSessionInformationExpiredStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//配置权限
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()//配置登录表
                .formLogin()
                .loginPage("/view/login?error=0").permitAll()
                .successHandler(ajaxAuthSuccessHandler)
                .failureHandler(ajaxauthfailhandler)
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()//配置注销
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/view/login?error=1").permitAll()
                .deleteCookies("myCookie")
                .addLogoutHandler(logoutHandler)
                .and()
                .csrf().disable() //禁用csrf
                .headers().frameOptions().sameOrigin();//允许跨域访问

        //只允许登录1个用户
        http.sessionManagement()
                .invalidSessionUrl("/view/login?error=2")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .sessionRegistry(sessionRegistry)
                .expiredUrl("/view/login?error=3");
//                .expiredSessionStrategy(ajaxSessionInformationExpiredStrategy)
    }

    /**
     * 忽略静态资源拦截
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/static/**");
    }


    /**
     * 配置认证方式
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(new CustomPasswordEncoder());
    }

}
