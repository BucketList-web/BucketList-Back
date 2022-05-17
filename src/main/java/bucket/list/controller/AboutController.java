package bucket.list.controller;


import bucket.list.domain.About;
import bucket.list.domain.Board_info_idx;
import bucket.list.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String about(@ModelAttribute("board_info_idx") Board_info_idx board_info_idx, Model model){

        List<About> about_items = aboutService.allContentList();

        model.addAttribute("about_items", about_items);
        model.addAttribute("board_info_idx", board_info_idx);

        return "about/about";
    }
    //글쓰기페이지
    @GetMapping("/write")
    // board_info_idx 의 변수값을 받기위해 board_info_idx 도메인을 생성해서 커맨드객체로 값은 받은다음 해당값을 넘겨줌
    public String writeForm(@ModelAttribute("board_info_idx") Board_info_idx board_info_idx, Model model){

        model.addAttribute("board_info_idx", board_info_idx);

        return "about/write";
    }

    @PostMapping("/write")
    //
    public String write(@ModelAttribute("about")About about){

        About save = aboutService.Save(about);


        return "redirect:/menu/{board_info_idx}";

    }



}
