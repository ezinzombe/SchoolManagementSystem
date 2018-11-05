package zw.co.soskode.SchoolManagementSystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import zw.co.soskode.SchoolManagementSystem.model.Role;
import zw.co.soskode.SchoolManagementSystem.service.UserService;


import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomSuccessHandler customSuccessHandler;
    @Autowired
    private UserService userService;
    private String[] AUTH_WHITELIST = {"/resources/**",
            "/registration**",
            "/js/**",
            "/css/**",
            "/images/**",
            "/webjars/**"};

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder().encode("password");
        Role cutAdminRole = new Role("CUTADMIN");
        auth.inMemoryAuthentication().withUser("admin@cut.co.zw")
                .password(password)
                .credentialsExpired(true)
                .accountExpired(true)
                .accountLocked(true)
                .authorities("CUTADMIN")
                .roles("CUTADMIN");
        System.out.println("USER+++++++++++++++++++++++++" + cutAdminRole);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(customSuccessHandler)
                .failureUrl("/login?error")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout");

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }


}