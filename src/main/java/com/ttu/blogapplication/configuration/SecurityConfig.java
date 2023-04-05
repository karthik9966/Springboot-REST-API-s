package com.ttu.blogapplication.configuration;
import com.ttu.blogapplication.filters.JwtFilter;
import com.ttu.blogapplication.security.JwtAuthenticationEntryPoint;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SecurityScheme(
        name = "Bear Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat ="JWT"
)
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    private JwtFilter jwtFilter;

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,
                   JwtFilter jwtFilter,
                   JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint)
    {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                {
                    authorize.requestMatchers(HttpMethod.GET, "/api/**")
                            .permitAll()
                            .requestMatchers("/api/auth/**")
                            .permitAll()
                            .requestMatchers("/swagger-ui/**")
                            .permitAll()
                            .requestMatchers("/v3/api-docs/**")
                            .permitAll()
                            .anyRequest().authenticated();
                })
                .exceptionHandling(exception ->exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        ).sessionManagement( session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
