package bucket.list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {

    @GetMapping("/menu/3")
    public String createCommunity(){        // 게시물 작성 폼

        return "community";
    }
}
