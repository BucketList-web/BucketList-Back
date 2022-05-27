package bucket.list.controller;

import bucket.list.domain.Community;
import bucket.list.domain.Participation;
import bucket.list.domain.Search;
import bucket.list.service.Community.CommunityService;
import bucket.list.service.Main.MainService;
import bucket.list.service.Participation.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = {"/main","/user"})
public class MainController {
    private final CommunityService service;
    private final ParticipationService participationService;
    private final MainService mainService;

    @Autowired
    public MainController(CommunityService service,ParticipationService participationService,MainService mainService){         // controller와 service 연결하는 느낌
        this.service = service;
        this.participationService = participationService;
        this.mainService = mainService;
    }

    @GetMapping("")
    public String main(Model model,@PageableDefault(page=0, size=4) Pageable pageable){
        Page<Community> data = maincommunity(pageable);
        Page<Participation> data2 = mainparticipation(pageable);


            model.addAttribute("data",data);
            model.addAttribute("data2", data2);
            return "/main/main";

    }

    @PostMapping("")
    public String main(Model model,Search search){
            System.out.println("들어옴");
            List<Community> list = mainService.searchList(search);
            model.addAttribute("search1",list);
            System.out.println(list);
            return "search/searchmain";
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
