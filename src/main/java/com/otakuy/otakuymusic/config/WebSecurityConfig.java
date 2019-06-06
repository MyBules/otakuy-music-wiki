package com.otakuy.otakuymusic.config;

import com.otakuy.otakuymusic.model.security.Role;
import com.otakuy.otakuymusic.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setSecurityContextRepository(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(HttpMethod.POST, "/login", "/admin/login", "/register", "/test").permitAll()
                .pathMatchers(HttpMethod.PUT, "/users/password").permitAll()
                .pathMatchers(HttpMethod.GET, "/albums", "/check/emails", "/check/usernames", "/verificationCode", "/albums/recommendAlbum", "/forgetPassword", "/test").permitAll()
                .anyExchange().authenticated()
                .and().build();
    }

    @Component
    class AuthenticationManager implements ReactiveAuthenticationManager {

        private final JWTUtil jwtUtil;

        @Autowired
        public AuthenticationManager(JWTUtil jwtUtil) {
            this.jwtUtil = jwtUtil;
        }

        @Override
        public Mono<Authentication> authenticate(Authentication authentication) {
            String authToken = authentication.getCredentials().toString();
            String username;
            try {
                username = jwtUtil.getUsernameFromToken(authToken);
            } catch (Exception e) {
                username = null;
            }
            if (username != null && jwtUtil.validateToken(authToken)) {
                Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
                List<String> rolesMap = claims.get("role", List.class);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        rolesMap.stream().map(role -> new SimpleGrantedAuthority(Role.valueOf(role).name())).collect(Collectors.toList())
                );
                return Mono.just(auth);
            } else {
                return Mono.empty();
            }
        }
    }

    @Component
    class SecurityContextRepository implements ServerSecurityContextRepository {

        private final AuthenticationManager authenticationManager;

        @Autowired
        public SecurityContextRepository(AuthenticationManager authenticationManager) {
            super();
            this.authenticationManager = authenticationManager;
        }

        @Override
        public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Mono<SecurityContext> load(ServerWebExchange swe) {
            ServerHttpRequest request = swe.getRequest();
            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String authToken = authHeader.substring(7);
                Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
                return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
            } else {
                return Mono.empty();
            }
        }

    }

    @Bean
    CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://otakuy.com");
        config.addAllowedOrigin("https://m.otakuy.com");
        config.addAllowedOrigin("https://admin.otakuy.com");
        config.addAllowedOrigin("http://localhost:63342");
        config.addAllowedHeader("*");
        config.addExposedHeader("Authorization");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}
