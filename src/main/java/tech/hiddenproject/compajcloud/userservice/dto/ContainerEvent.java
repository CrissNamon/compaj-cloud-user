package tech.hiddenproject.compajcloud.userservice.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author Danila Rassokhin
 */
@Data
public class ContainerEvent {

  private String status;

  private String id;

  private LocalDateTime time;

  private String action;

  @Override
  public String toString() {
    return "ContainerEvent{" +
        "status='" + status + '\'' +
        ", id='" + id + '\'' +
        ", time=" + time +
        ", action='" + action + '\'' +
        '}';
  }
}
