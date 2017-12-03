package pl.mg.cfm.api.security;

import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AuthorizationServerEndpointsConfiguration.class, CfmAuthorizationServerSecurityConfiguration.class})
public @interface CfmEnableAuthorizationServer {

}
