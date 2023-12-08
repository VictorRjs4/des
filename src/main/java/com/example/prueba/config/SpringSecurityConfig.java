package com.example.prueba.config;

import com.example.prueba.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JpaUserDetailsService userDetailsService;

    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private LoginSucessHandler sucessHandler;

    protected void configure(HttpSecurity http) throws Exception {
        try {
            http.authorizeRequests()

                        .antMatchers("/users").permitAll()
                        .antMatchers("/home").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                        .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                        .and()
                         .formLogin().successHandler(sucessHandler).loginPage("/login").loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home").permitAll()
                        .and().logout().logoutSuccessUrl("/login").permitAll()
                        .and().exceptionHandling().accessDeniedPage("/error403");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}