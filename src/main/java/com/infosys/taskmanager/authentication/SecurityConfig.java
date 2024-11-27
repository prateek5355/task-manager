package com.infosys.taskmanager.authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    private  AuthenticationProvider authenticationProvider;
    private JwtAuthenticationEntryPoint authenticationEntryPoint;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> {
//                    authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                    authorize.requestMatchers("/api/register/**").permitAll();
                    authorize.requestMatchers("/api/login/**").permitAll();
                    authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        http.exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }




//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/loans/admin-dashboard").hasAnyAuthority("ADMIN")
//                .antMatchers("/api/register/**").permitAll()
//                .antMatchers("/api/login/**").permitAll()
//                .antMatchers("/public/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/home-loans/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/v3/api-docs/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/home-loans/**").permitAll()
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .cors()
//                .and()
//                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
   // }


}


