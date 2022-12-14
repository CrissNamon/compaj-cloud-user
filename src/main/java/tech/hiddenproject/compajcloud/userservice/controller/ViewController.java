package tech.hiddenproject.compajcloud.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Danila Rassokhin
 */
@Slf4j
@Controller
public class ViewController {

  @GetMapping(value = "/processor")
  public String processorPage(Model model) {
    return "processor";
  }
}
