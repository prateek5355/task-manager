//package com.infosys.taskmanager.configuration;
//
//import com.infosys.taskmanager.security.JwtTokenFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final JwtTokenFilter jwtTokenFilter;
//    private final AuthenticationProvider authenticationProvider;
//
//    public SecurityConfig(JwtTokenFilter jwtTokenFilter, AuthenticationProvider authenticationProvider) {
//        this.jwtTokenFilter = jwtTokenFilter;
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Disable CSRF protection if you don't need it (common for stateless APIs)
//                // Configure session management to use stateless (no sessions)
//                // Enable CORS (cross-origin requests)
//                .req
//                // Define your endpoint access control using 'authorizeHttpRequests'
//                .requestMatchers(HttpMethod.GET, "/api/loans/admin-dashboard").hasAnyAuthority("ADMIN")
//                .requestMatchers("/api/register/**").permitAll()
//                .requestMatchers("/api/login/**").permitAll()
//                .requestMatchers("/public/**").permitAll()
//                .requestMatchers(HttpMethod.POST, "/api/home-loans/**").permitAll()
//                .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
//                .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/home-loans/**").permitAll()
//                .anyRequest().authenticated()  // Ensure other requests require authentication
//                .and()
//                // Add your custom JWT filter before UsernamePasswordAuthenticationFilter
//                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationConfiguration authenticationConfiguration = new AuthenticationConfiguration();
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    // Bean to provide PasswordEncoder (bcrypt in this case)
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
