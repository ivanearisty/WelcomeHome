package PDS.Project3.Configuration;

import PDS.Project3.Configuration.Filters.JwtAuthenticationFilter;
import PDS.Project3.Domain.Enum.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static PDS.Project3.Domain.Enum.Roles.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private static final String[] PUBLIC_URLS = {"/user/login/**", "/user/register/**", "/inserttest/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> {
                    requests.requestMatchers(PUBLIC_URLS).permitAll()
                            //Core requests
                            .requestMatchers("/test").hasAuthority("CREATE:ITEM")
                            .requestMatchers("/donation").hasAuthority("CREATE:ITEM")
                            .requestMatchers("/register/**").hasAuthority("CREATE:PERSON")
                            .requestMatchers("/item/piece/**").hasAnyAuthority("READ:ITEM", "UPDATE:ITEM", "DELETE:ITEM", "CREATE:ITEM")
                            .requestMatchers("/item").hasAnyAuthority("READ:ITEM")
                            .requestMatchers("/order/get/**").hasAnyAuthority("READ:ORDER")
                            .requestMatchers("/order/create").hasAnyAuthority("CREATE:ORDER")
                            .requestMatchers("/donate").hasAuthority("READ:ITEM")
                            .requestMatchers("/category/**").hasAuthority("READ:ITEM")
                            .requestMatchers("/order/addItem").hasAuthority("UPDATE:ORDER")
                            //Block unauthenticated requests
                            .anyRequest().authenticated();
                });
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

}
