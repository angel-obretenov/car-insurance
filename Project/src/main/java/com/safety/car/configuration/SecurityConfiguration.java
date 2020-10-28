package com.safety.car.configuration;

import com.safety.car.utils.filters.LoginRegisterPageFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private static final String[] PUBLIC_MATCHERS = {"/style.css", "/fonts/**", "/css/**", "/js/**",
            "/uploads/**", "/images/**", "/", "/login", "/register/**", "/confirm-account/**",
            "/images/**", "/register/confirm-account/**"};
    private static final String[] USER_MATCHERS = {"/service"};
    private static final String[] ADMIN_MATCHERS = {"/policy-approval/**"};

    @Autowired
    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        var jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    // auth manager builder sets the logic for users, passwords and roles
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // http configuration which sets the application security behaviour
    //TODO still yet to configure the restricted access
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new LoginRegisterPageFilter(), DefaultLoginPageGeneratingFilter.class);

        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(USER_MATCHERS).hasRole("USER")
                .antMatchers(ADMIN_MATCHERS).hasRole("ADMIN")
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                //.and()
                //.httpBasic() -> (postman, insomnia configuration)
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate").permitAll()
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .csrf().disable();
    }
}