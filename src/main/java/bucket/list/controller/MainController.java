package bucket.list.controller;

import bucket.list.domain.Community;
import bucket.list.domain.Participation;
import bucket.list.service.Community.CommunityService;
import bucket.list.service.Main.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/main")
public class MainController {
    private final MainService mainService;
    CommunityService service;
    @Autowired
    public MainController(CommunityService service, MainService mainService){         // controller와 service 연결하는 느낌
        this.service = service;
        this.mainService = mainService;
    }

//    @GetMapping("")
//    public String main(){        // 로그인 메인
//        return "/main/main";
//    }

    @GetMapping("")
    public String maincommunity(Model model, @PageableDefault(page = 0, size=4, sort="communityid", direction = Sort.Direction.DESC)
            Pageable pageable){        // 커뮤니티 메인
        Page<Community> data =service.showCommunityAll(pageable);
        List<Participation> bestContents = mainService.bestContent();

        model.addAttribute("bestContents", bestContents);

        model.addAttribute("data",data);

        return "/main/main";
    }
}
