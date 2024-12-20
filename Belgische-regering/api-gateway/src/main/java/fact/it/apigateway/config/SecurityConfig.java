package fact.it.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.
                authorizeExchange(exchange ->
                        exchange.pathMatchers(HttpMethod.GET, "/alle-partijen").permitAll()
                                .pathMatchers(HttpMethod.GET, "/partij/{naam}").permitAll()
                                .pathMatchers(HttpMethod.GET, "/alle-regeringen").permitAll()
                                .pathMatchers(HttpMethod.GET, "/regering/{naam}").permitAll()
                                .pathMatchers(HttpMethod.GET, "/partijlid/{segment}").permitAll()
                                .pathMatchers(HttpMethod.GET, "/minister/{segment}").permitAll()
                                .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));
        return serverHttpSecurity.build();
    }
}
