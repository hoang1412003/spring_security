package com.example.spring_security.configs;

import com.example.spring_security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig2 {
    private final UserRepository userRepository;
    //    @Bean
//    public UserDetailsManager userDetailsManager() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(
//                User.withUsername("hoangadmin").password(passwordEncoder().encode("123456789")).roles("ADMIN").build()
//        );
//        manager.createUser(
//                User.withUsername("hoanguser").password(passwordEncoder().encode("123456789")).roles("USER").build()
//        );
//        return manager;
//
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username);
    }

    @Bean
    // ánh xạ username và password cho spring security
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
