package tech.hiddenproject.compajcloud.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;

/**
 * @author Danila Rassokhin
 */
@SpringBootApplication
@EnableEurekaClient
@EnableReactiveFeignClients
public class UserServiceApplication {

  public static void main(String... args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

}
