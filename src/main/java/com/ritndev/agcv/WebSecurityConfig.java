package com.ritndev.agcv;

// Aide : https://blog.ouidou.fr/spring-security-pour-les-nuls-1930fce60089

import javax.sql.DataSource;

import com.ritndev.agcv.services.customUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private customUserDetailsService customUserDetails;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(customUserDetails).passwordEncoder(passwordEncoder());
    }
    
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/index").permitAll()
            .antMatchers("/css/**", "/js/**", "/images/**").permitAll()     // Autorisations d'accés au ressources
            .antMatchers("/admin", "/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_SUPADMIN")
            .antMatchers("/superAdmin", "/newData").hasAnyAuthority("ROLE_SUPADMIN")
            .anyRequest().permitAll()
            .and()
        .exceptionHandling().accessDeniedPage("/403");

        // Config for Login Form
        http
            .authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginPage("/login")//
                .defaultSuccessUrl("/admin")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/index");
        
        // Config Remember Me.
        http
            .authorizeRequests().and() //
            .rememberMe().tokenRepository(this.persistentTokenRepository()) //
            .tokenValiditySeconds(1 * 2 * 60 * 60); // 2h
        
	}

    
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
        
}
