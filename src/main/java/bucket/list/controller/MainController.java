package bucket.list.controller;

import bucket.list.domain.Community;
import bucket.list.service.Community.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/main")
public class MainController {

    CommunityService service;
    @Autowired
    public MainController(CommunityService service){         // controller와 service 연결하는 느낌
        this.service = service;
    }

//    @GetMapping("")
//    public String main(){        // 로그인 메인
//        return "/main/main";
//    }

    @GetMapping("")
    public String maincommunity(Model model, @PageableDefault(page = 0, size=4, sort="communityid", direction = Sort.Direction.DESC)
            Pageable pageable){        // 커뮤니티 메인
        Page<Community> data =service.showCommunityAll(pageable);

        model.addAttribute("data",data);

        return "/main/main";
    }
}
