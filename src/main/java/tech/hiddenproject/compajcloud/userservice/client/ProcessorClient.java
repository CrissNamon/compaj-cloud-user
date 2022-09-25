package tech.hiddenproject.compajcloud.userservice.client;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.hiddenproject.compajcloud.userservice.dto.ContainerEvent;

/**
 * @author Danila Rassokhin
 */
@ReactiveFeignClient(name = "processor-service")
public interface ProcessorClient {

  @GetMapping("/watch")
  Flux<ContainerEvent> watch();

  @GetMapping("/info")
  Mono<String> info();

  @GetMapping("/file/{id}")
  Flux<ServerSentEvent<ContainerEvent>> watchFile(@PathVariable String id);

  @GetMapping("/exec/{id}")
  Mono<String> exec(@PathVariable String id, @RequestBody String cmd);

}
