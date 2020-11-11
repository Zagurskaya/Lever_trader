package com.gmail.zagurskaya.web.security;

import com.gmail.zagurskaya.web.handler.AppUrlAuthenticationSuccessHandler;
import com.gmail.zagurskaya.web.handler.LoginAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static com.gmail.zagurskaya.web.constant.RolesConstant.ADMIN;
import static com.gmail.zagurskaya.web.constant.RolesConstant.GUEST;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_403;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMIN;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_CONFIRM;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_CONTROLLER;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_SIGN_UP;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_SIGN_IN;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_TEST;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public WebSecurityConfigurer(UserDetailsService userDetailsService,
                                 PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AppUrlAuthenticationSuccessHandler();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new LoginAccessDeniedHandler();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers(URL_ADMIN)
                .antMatchers(URL_TEST)
                .hasAuthority(ADMIN)
//                .antMatchers("/api/auth/**","/api/traders/**")
                .antMatchers("/api/auth/**", "/api/traders/**", "/api/admin/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage(URL_SIGN_IN)
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .csrf()
                .disable();
    }
}
