package tech.hiddenproject.compajcloud.userservice.feign;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactivefeign.client.ReactiveHttpRequest;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactor.core.publisher.Mono;

/**
 * @author Danila Rassokhin
 */
@Slf4j
@Component
public class OAuthFeignInterceptor implements ReactiveHttpRequestInterceptor {

  private final OAuthClientCredentialsFeignManager oAuthClientCredentialsFeignManager;

  @Autowired
  public OAuthFeignInterceptor(OAuthClientCredentialsFeignManager oAuthClientCredentialsFeignManager) {
    this.oAuthClientCredentialsFeignManager = oAuthClientCredentialsFeignManager;
  }

  @Override
  public Mono<ReactiveHttpRequest> apply(ReactiveHttpRequest reactiveHttpRequest) {
    String token = oAuthClientCredentialsFeignManager.getAccessToken();
    reactiveHttpRequest.headers()
        .put("Authorization", List.of("Bearer " + token));
    return Mono.just(reactiveHttpRequest);
  }
}
