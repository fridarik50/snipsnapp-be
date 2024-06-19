package hackeru.fridarik.snipsnapp.config;

import hackeru.fridarik.snipsnapp.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( req -> req.requestMatchers("/api/v1/login").permitAll())
                .authorizeHttpRequests( req -> req.requestMatchers(HttpMethod.POST, "/api/v1/barbers").permitAll())
                .authorizeHttpRequests( req -> req.requestMatchers(HttpMethod.GET,"/api/v1/barbers/**").authenticated())
                .authorizeHttpRequests( req -> req.requestMatchers(HttpMethod.PUT,"/api/v1/barbers/**").authenticated())
                .authorizeHttpRequests( req -> req.requestMatchers(HttpMethod.DELETE,"/api/v1/barbers/**").authenticated())
                .authorizeHttpRequests( req -> req.requestMatchers(HttpMethod.POST, "/api/v1/customers/**").permitAll())
                .authorizeHttpRequests( req -> req.requestMatchers(HttpMethod.GET,"/api/v1/customers/**").authenticated())
                .authorizeHttpRequests( req -> req.requestMatchers(HttpMethod.PUT,"/api/v1/customers/**").authenticated())
                .authorizeHttpRequests( req -> req.requestMatchers(HttpMethod.DELETE,"/api/v1/customers/**").authenticated())
                .authorizeHttpRequests( req -> req.requestMatchers("/api/v1/appointments/**").authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }
}
