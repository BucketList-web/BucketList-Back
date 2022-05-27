package bucket.list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/menu/4")
public class servicecenterController {

    @GetMapping("")
    public String servicecentermain(HttpServletRequest request){


        return "community/communitymain";
    }
}
