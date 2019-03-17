package com.rcodingschool.carrepair.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN_URI = "/admin";
    public static final String USER_URI = "/user";

    public static final String ADMIN_ROLE = "Admin";
    public static final String USER_ROLE = "User";

    public static final String INDEX_URI = "/";
    public static final String LOGIN_URI = "/login";
    private static final String LOGOUT_URI = "/logout";

    private final LoginAuthenticationProvider loginAuthenticationProvider;
    private final LoginSuccessHandler loginSuccessHandler;

    @Autowired
    public SecurityConfig(LoginAuthenticationProvider loginAuthenticationProvider, LoginSuccessHandler loginSuccessHandler) {
        this.loginAuthenticationProvider = loginAuthenticationProvider;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .formLogin()
                .loginPage(LOGIN_URI)
                .successHandler(loginSuccessHandler)
                .and()
                .logout()
                .logoutUrl(LOGOUT_URI)
                .logoutSuccessUrl(LOGIN_URI)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(LOGIN_URI).permitAll()
                .antMatchers(USER_URI + "/**").hasAnyAuthority(USER_ROLE)
                .antMatchers(ADMIN_URI + "/**").hasAnyAuthority(ADMIN_ROLE)
                .and()
                .authenticationProvider(loginAuthenticationProvider);
    }
}
