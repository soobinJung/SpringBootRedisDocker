package com.soo.bin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests( authorize ->
                authorize
                .requestMatchers(
                    "/"
                    , "/public/**"
                    , "/login"
                    , "/login?error"
                    , "/login?logout"
                    , "/access-denied"
                ).permitAll()
                .anyRequest().authenticated()
            )

            .formLogin( formLogin ->
                formLogin.loginPage("/login")
                    .defaultSuccessUrl("/public", true)
                    .failureUrl("/access-denied")
                    .permitAll()

            )

            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .permitAll()
                    .logoutSuccessUrl("/login?logout")
            )

            .exceptionHandling(exceptionHandling -> exceptionHandling
                    .accessDeniedPage("/access-denied")
            )

            .rememberMe(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
