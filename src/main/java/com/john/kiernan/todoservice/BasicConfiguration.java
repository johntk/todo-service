package com.john.kiernan.todoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder.encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder.encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("adminPass")).roles("ADMIN");
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/webjars/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/list-todos.html", true)
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        ;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {

        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
