package bucket.list.controller;

import bucket.list.service.Login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CheckIdController {

    private final UserService userService;


    @Autowired
    public CheckIdController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/checkUserIdExist/{user_id}")
    public String checkUserIdExist(@PathVariable String user_id, Model model) {

        boolean chk = userService.checkUserIdExist(user_id);


        if(chk==true){
            model.addAttribute("checkUserIdExist", chk);
            return "user/existId";
        }
        else {
            model.addAttribute("checkUserIdExist", chk);
            return "user/noExistId";
        }
    }
}
