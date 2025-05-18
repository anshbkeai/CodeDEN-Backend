package com.leetcode.leetcode.Core.Configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.leetcode.leetcode.Core.Filter.JwtFilter;

//  enlr othe  cors Origin  here  
@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityConfiguration {

    private  final JwtFilter jwtFilter;
    public  SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter   =  jwtFilter;
    }
   
    @Bean
    public  SecurityFilterChain securityFilterChain(HttpSecurity  httpSecurity) throws Exception {
        httpSecurity. 
                    csrf(csrf ->  csrf.disable())
                    .cors(cors -> cors.configurationSource(corsConfiguration()))
                    .sessionManagement(session ->  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(authorizeHttpRequests  ->  {
                        authorizeHttpRequests.requestMatchers("/api/**").permitAll();
                        authorizeHttpRequests.requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll();

                        authorizeHttpRequests.anyRequest().authenticated();
                    })
                    .addFilterBefore(jwtFilter  ,  UsernamePasswordAuthenticationFilter.class);
        return  httpSecurity.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfiguration() {
        CorsConfiguration  corsConfiguration   =  new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://192.168.97.36:5173", "https://dn11g40h-5173.inc1.devtunnels.ms/"));   //  in th  Prod  we  need  to  se  the   React app  hosted  Url  
        corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource  urlBasedCorsConfigurationSource   =  new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return  urlBasedCorsConfigurationSource;
    }  
}
