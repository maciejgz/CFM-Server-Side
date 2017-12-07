package pl.mg.cfm.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpointHandlerMapping;

/**
 * Custom CFM authorization server security configuration due to lack of permissions on OPTIONS method for pre-flight methods.
 *
 * @author Maciej Gzik
 */
public class CfmAuthorizationServerSecurityConfiguration extends AuthorizationServerSecurityConfiguration {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerEndpointsConfiguration endpoints;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthorizationServerSecurityConfigurer configurer = new AuthorizationServerSecurityConfigurer();
        FrameworkEndpointHandlerMapping handlerMapping = endpoints.oauth2EndpointHandlerMapping();
        http.setSharedObject(FrameworkEndpointHandlerMapping.class, handlerMapping);
        configure(configurer);
        http.apply(configurer);
        String tokenEndpointPath = handlerMapping.getServletPath("/oauth/token");
        String tokenKeyPath = handlerMapping.getServletPath("/oauth/token_key");
        String checkTokenPath = handlerMapping.getServletPath("/oauth/check_token");
        if (!endpoints.getEndpointsConfigurer().isUserDetailsServiceOverride()) {
            UserDetailsService userDetailsService = http.getSharedObject(UserDetailsService.class);
            endpoints.getEndpointsConfigurer().userDetailsService(userDetailsService);
        }
        // @formatter:off
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers(tokenEndpointPath).fullyAuthenticated()
                .antMatchers(tokenKeyPath).access(configurer.getTokenKeyAccess()).antMatchers(checkTokenPath).access(configurer.getCheckTokenAccess())
                .and().requestMatchers().antMatchers(tokenEndpointPath, tokenKeyPath, checkTokenPath).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER);
        // @formatter:on
        http.setSharedObject(ClientDetailsService.class, clientDetailsService);
    }

}
