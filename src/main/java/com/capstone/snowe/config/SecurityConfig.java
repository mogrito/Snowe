package com.capstone.snowe.config;

import com.capstone.snowe.jwt.JwtAccessDeniedHandler;
import com.capstone.snowe.jwt.JwtAuthenticationEntryPoint;
import com.capstone.snowe.jwt.JwtSecurityConfig;
import com.capstone.snowe.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // PasswordEncoder는 BCryptPasswordEncoder를 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:19006")); //  origin
            config.setAllowCredentials(true);

            return config;
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // srf를 disable
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)


                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/board/add").hasAuthority("USER")
                        .requestMatchers("/lesson/**").hasAuthority("TEACHER")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/member/**").permitAll()
                        .requestMatchers("/reservation").hasAuthority("USER")
                        .requestMatchers("/board/**").hasAuthority("USER")
                        .requestMatchers("/comment/**").hasAuthority("USER")
                        .anyRequest().authenticated()
                )
                .apply(new JwtSecurityConfig(tokenProvider)); //JwtSecurityConfig class 적용

        return httpSecurity.build();
    }

}
