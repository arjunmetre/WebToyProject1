package WebToyProject1.webService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HomeController {

    @ResponseBody
    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }
}
