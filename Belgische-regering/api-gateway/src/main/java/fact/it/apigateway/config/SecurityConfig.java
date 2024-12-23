package fact.it.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .cors(cors -> cors.configurationSource(request -> {
                        var config = new CorsConfiguration();
                        config.addAllowedOrigin("*");
                        config.addAllowedMethod("*");
                        config.addAllowedHeader("*");
                        return config;
                    }))
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(HttpMethod.GET, "/partijen", "/partij/naam/{naam}", "/regeringen", "/regering/naam/{naam}", "/partijleden","/partijlid/{id}", "/partijlid/naam/{naam}", "/ministers", "/minister/{id}", "/minister/naam/{naam}").permitAll()
                                .anyExchange().authenticated())

                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));
        return serverHttpSecurity.build();
    }
}
