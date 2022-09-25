package tech.hiddenproject.compajcloud.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import tech.hiddenproject.compajcloud.userservice.client.ProcessorClient;
import tech.hiddenproject.compajcloud.userservice.dto.ContainerEvent;

/**
 * @author Danila Rassokhin
 */
@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {

  private final ProcessorClient processorClient;

  @Autowired
  public RestController(ProcessorClient processorClient) {
    this.processorClient = processorClient;
  }

  @GetMapping(value = "/watch/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<ContainerEvent>> watch(JwtAuthenticationToken jwtAuthenticationToken) {
    return processorClient.watchFile(jwtAuthenticationToken.getName());
  }

}
