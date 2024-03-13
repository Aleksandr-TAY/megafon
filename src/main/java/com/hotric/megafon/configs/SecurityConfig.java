package com.hotric.megafon.configs;

import com.hotric.megafon.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public SecurityConfig(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
    
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/").permitAll();
                    request.requestMatchers("/hello").hasAnyRole("ADMIN", "FRONTEND", "BACKEND");
                    request.requestMatchers("/fe").hasAnyRole("ADMIN", "FRONTEND");
                    request.requestMatchers("/be").hasAnyRole("ADMIN", "BACKEND");
                    request.requestMatchers("/admin").hasRole("ADMIN");
                    request.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .permitAll()
                        .defaultSuccessUrl("/hello", true))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
                .authenticationProvider(daoAuthenticationProvider()).build();
    }
}
