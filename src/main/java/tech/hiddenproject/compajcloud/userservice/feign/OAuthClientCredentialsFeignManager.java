package tech.hiddenproject.compajcloud.userservice.feign;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

/**
 * @author Danila Rassokhin
 */
@Slf4j
public class OAuthClientCredentialsFeignManager {

  private final OAuth2AuthorizedClientManager manager;

  private final Authentication principal;

  private final ClientRegistration clientRegistration;

  public OAuthClientCredentialsFeignManager(OAuth2AuthorizedClientManager manager,
                                            ClientRegistration clientRegistration) {
    this.manager = manager;
    this.clientRegistration = clientRegistration;
    this.principal = createPrincipal();
  }

  public String getAccessToken() {
    try {
      ClientRegistration registration = clientRegistration;
      OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
          .withClientRegistrationId(registration.getRegistrationId())
          .principal(principal)
          .build();
      OAuth2AuthorizedClient client = Optional.ofNullable(manager.authorize(oAuth2AuthorizeRequest))
          .orElseThrow(() -> new IllegalStateException(
              "client credentials flow on " + registration.getRegistrationId() + " failed, client is null"
          ));
      return client.getAccessToken().getTokenValue();
    } catch (Exception exp) {
      log.error("client credentials error " + exp.getMessage());
    }
    return null;
  }

  private Authentication createPrincipal() {
    return new AnonymousAuthenticationToken(
        "key", "anonymous", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
  }
}
