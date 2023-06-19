package com.example.cuoponProject.Configuration;


//
//@Configuration
//public class CORSConfig {
//    @Bean
//    public CorsFilter corsFilter() {
//        //create new url configuration for browsers
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        //create new cors configuration....
//        CorsConfiguration config = new CorsConfiguration();
//        //allow to get credentials in cors
//        config.setAllowCredentials(true);
//        //allow to get from any ip/domain
//        config.addAllowedOrigin("*");
//        //allow to get any header
//        config.addAllowedHeader("*");
//        //alow to get methods
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("DELETE");
//        //allow to get any route -> localhost:8080/api/lecturer -> /api/lecture is route
//        source.registerCorsConfiguration("/**", config);
//        ///return new configuration
//        return new CorsFilter(source);
//    }
//}
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSConfig {
    @Bean
    public CorsFilter corsFilter() {
        // create new url configuration for browsers
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // create new cors configuration
        CorsConfiguration config = new CorsConfiguration();
        // allow to get credentials in cors
        config.setAllowCredentials(true);
        // allow to get from trusted domains only
        config.addAllowedOrigin("https://www.example1.com");
        config.addAllowedOrigin("https://example1.com");
        // allow to get only specified headers
        config.addAllowedHeader("Authorization");
        config.addAllowedHeader("Content-Type");
        // allow to get only specified methods
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        // allow to get only specified routes
        source.registerCorsConfiguration("/**", config);
        // return new configuration
        return new CorsFilter(source);
    }
}*/