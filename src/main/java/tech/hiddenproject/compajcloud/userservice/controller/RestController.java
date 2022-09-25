package tech.hiddenproject.compajcloud.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import tech.hiddenproject.compajcloud.userservice.client.TestClient;
import tech.hiddenproject.compajcloud.userservice.dto.ContainerEvent;

/**
 * @author Danila Rassokhin
 */
@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {

  private final TestClient testClient;

  @Autowired
  public RestController(TestClient testClient) {
    this.testClient = testClient;
  }

  @GetMapping(value = "/watch/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<ContainerEvent>> watch(JwtAuthenticationToken jwtAuthenticationToken) {
    return testClient.watchFile(jwtAuthenticationToken.getName());
  }

//  @PostMapping("/beacon/{id}")
//  public void closeConnection(@PathVariable String id) {
//    //beaconService.get(id).ifPresent(handler -> handler.);
//  }

//  public Mono<ServerResponse> watch(ServerRequest serverRequest) {
//    return ServerResponse.ok()
//        .contentType(MediaType.TEXT_EVENT_STREAM)
//        .body(testClient.watch(), ServerSentEvent.class);
//  }
//
//  public Mono<ServerResponse> info(ServerRequest serverRequest) {
//    return ServerResponse.ok()
//        .body(testClient.info(), String.class);
//  }
//
//
//  public String watchFile(Model model) {
//    return "Hello";
//  }
//
//  public Mono<ServerResponse> exec(ServerRequest serverRequest) {
//    return ReactiveSecurityContextHolder.getContext()
//        .map(SecurityContext::getAuthentication)
//        .map(Principal::getName)
//        .flatMap(name -> ServerResponse.ok()
//            .contentType(MediaType.TEXT_EVENT_STREAM)
//            .body(testClient.exec(name, "println \"Hello world!\""), String.class)
//        );
//  }

}
