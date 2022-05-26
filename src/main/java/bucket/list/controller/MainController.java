package bucket.list.controller;

import bucket.list.domain.Community;
import bucket.list.domain.Participation;
import bucket.list.service.Community.CommunityService;
import bucket.list.service.Participation.ParticipationService;
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
@RequestMapping(value = {"/main","/user"})
public class MainController {
    private final CommunityService service;
    private final ParticipationService participationService;

    @Autowired
    public MainController(CommunityService service,ParticipationService participationService){         // controller와 service 연결하는 느낌
        this.service = service;
        this.participationService = participationService;
    }
    @GetMapping("")
    public String main(Model model,@PageableDefault(page=0, size=4) Pageable pageable){
        Page<Community> data = maincommunity(pageable);
        Page<Participation> data2 = mainparticipation(pageable);

        model.addAttribute("data",data);
        model.addAttribute("data2", data2);

        return "/main/main";
    }

    public Page<Community> maincommunity(@PageableDefault(page = 0, size=4, sort="communityid", direction = Sort.Direction.DESC)
            Pageable pageable){        // 커뮤니티 메인
        Page<Community> data =service.showCommunityAll(pageable);

        return data;
    }

    public Page<Participation> mainparticipation(@PageableDefault(page = 0, size=4, sort="participationidx", direction = Sort.Direction.DESC)
            Pageable pageable){        // 커뮤니티 메인
        Page<Participation> data2 = participationService.AllContentList(pageable);
        return data2;
    }

}
