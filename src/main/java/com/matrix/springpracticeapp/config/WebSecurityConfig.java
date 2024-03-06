package com.matrix.springpracticeapp.config;

import com.matrix.springpracticeapp.filter.JwtAuthorizationFilter;
import com.matrix.springpracticeapp.service.LogoutService;
import com.matrix.springpracticeapp.service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final MyUserDetailsService userDetailsService;
    private final LogoutService logoutHandler;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder passwordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(permitSwagger).permitAll()
                                .requestMatchers(permitAllUrls).permitAll()
                                .requestMatchers(readerUrls).hasAnyAuthority(Role.ROLE_READER.name())
                                .requestMatchers(authorUrls).hasAnyAuthority(Role.ROLE_AUTHOR.name())
                                .requestMatchers(adminUrls).hasAnyAuthority(Role.ROLE_ADMIN.name())
//                                .requestMatchers(anyAuthReqUrls).authenticated()
                                .anyRequest().authenticated()
                ).exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) ->
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
                        )
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN)
                        )
                )
                .httpBasic(Customizer.withDefaults()).logout(lg -> lg
                        .logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()
                        )
                );

        return http.build();

    }


    public static String[] permitSwagger = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/swagger-ui/**",
            "/swagger-ui.html",
    };

    public static String[] authorUrls = {
            "/books/**",
            "authors/create",
            "authors/update"
    };

    public static String[] readerUrls = {
            "/books/readAll",
            "/books/readById",
            "/books/readByPrice"
    };

    public static String[] permitAllUrls = {
            "users/**"
    };

    public static String[] adminUrls = {
            "/authors/**"
    };

    public static String[] anyAuthReqUrls = {
            "authors/readByID",
            "authors/readAllAuthors"
    };
}
