package tech.hiddenproject.compajcloud.userservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ClientCredentialsOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.reactive.function.client.WebClient;
import tech.hiddenproject.compajcloud.userservice.feign.OAuthClientCredentialsFeignManager;

/**
 * @author Danila Rassokhin
 */
@Slf4j
@Configuration
public class FeignOAuthConfig {

  public static final String CLIENT_REGISTRATION_ID = "keycloak";

  private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

  private final ClientRegistrationRepository clientRegistrationRepository;

  @Autowired
  public FeignOAuthConfig(OAuth2AuthorizedClientService oAuth2AuthorizedClientService,
                          ClientRegistrationRepository clientRegistrationRepository) {
    this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    this.clientRegistrationRepository = clientRegistrationRepository;
  }

  @Bean
  public OAuthClientCredentialsFeignManager getOAuthClientCredentialsFeignManager() {
    ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(CLIENT_REGISTRATION_ID);
    return new OAuthClientCredentialsFeignManager(authorizedClientManager(), clientRegistration);
  }

  @Bean
  @LoadBalanced
  public WebClient webClient() {
    return WebClient.create();
  }

  private OAuth2AuthorizedClientManager authorizedClientManager() {
    OAuth2AuthorizedClientProvider authorizedClientProvider = new ClientCredentialsOAuth2AuthorizedClientProvider();
    AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
        new AuthorizedClientServiceOAuth2AuthorizedClientManager(
            clientRegistrationRepository, oAuth2AuthorizedClientService);
    authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
    return authorizedClientManager;
  }
}
