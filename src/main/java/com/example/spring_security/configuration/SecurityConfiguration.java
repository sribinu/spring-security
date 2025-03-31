package com.example.spring_security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 1. Disable CSRF
            // Two alternatives:
                // Same site strict - Block sharing of cookies when going to third party websites
                // Convert Session into stateless
        // 2. Functional Interface and lambda expression

        //http.csrf(customizer -> customizer.disable());
        //http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
            //http.formLogin(Customizer.withDefaults()); // For default login page of Spring Security
        //http.httpBasic(Customizer.withDefaults()); // For Postman

        //stateless - create new session ID for every request
        //http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        //return http.build(); //provide us the SecurityFilterChain object

        return http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build(); //Builder pattern


        // How csrf disable works?
//        Customizer<CsrfConfigurer<HttpSecurity>> custcsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> customizer) {
//                customizer.disable();
//            }
//        }; //since it is a functional interface, we can use lambda expression
//        http.csrf(custcsrf);
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        List<UserDetails> users = new ArrayList<>();
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("sribinu")
//                .password("123")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("subin")
//                .password("123")
//                .roles("USER")
//                .build();
//
//        UserDetails user3 = User
//                .withDefaultPasswordEncoder()
//                .username("adam")
//                .password("123")
//                .roles("USER")
//                .build();
//
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//        return new InMemoryUserDetailsManager(users);
//
//        //return new InMemoryUserDetailsManager(user1,user2,user3); //varargs
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // No password encoding
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
