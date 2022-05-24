package bucket.list.controller;

import bucket.list.domain.About;
import bucket.list.domain.Participation;
import bucket.list.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String bestContent(Model model){

        List<Participation> bestContents = mainService.bestContent();
        List<About> newContents = mainService.newContent();

        model.addAttribute("newContents", newContents);
        model.addAttribute("bestContents", bestContents);

        return "main/main";
    }
}
