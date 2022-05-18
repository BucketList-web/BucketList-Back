package bucket.list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {

    @GetMapping("/login")
    public String Login(){
        return "user/login";
    }

    @GetMapping("/create")
    public String create(){

        return "user/create";
    }
}
