package bucket.list.controller;


import bucket.list.domain.About;
import bucket.list.domain.BoardInfo;
import bucket.list.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu/{board_info_idx}")
public class AboutController {

    private final AboutService aboutService;

    @Autowired
    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    //공지사항 메인페이지 메서드
    @GetMapping()
    public String about(@ModelAttribute("board_info_idx") BoardInfo board_info_idx, Model model){

        List<About> about_items = aboutService.allContentList();

        model.addAttribute("about_items", about_items);
        model.addAttribute("board_info_idx", board_info_idx);

        return "about/about";
    }
    //글쓰기페이지
    @GetMapping("/write")
    // board_info_idx 의 변수값을 받기위해 board_info_idx 도메인을 생성해서 커맨드객체로 값은 받은다음 해당값을 넘겨줌
    public String writeForm(@ModelAttribute("board_info_idx") BoardInfo board_info_idx, Model model){

        model.addAttribute("board_info_idx", board_info_idx);

        return "about/write";
    }

    @PostMapping("/write")
    //
    public String write(@ModelAttribute("about")About about){

        About save = aboutService.Save(about);


        return "redirect:/menu/{board_info_idx}";

    }
    @GetMapping("/{about_number}/read")
    //글읽는 페이지 메서드
    public String read(@PathVariable int about_number, Model model ){

        About about = aboutService.oneContentList(about_number);

        model.addAttribute("about", about);

        return "about/read";
    }
    



}
