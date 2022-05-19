package bucket.list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String Communitymain(){        // 로그인 메인
        return "/main/main";
    }
}
