package bucket.list.controller;

import bucket.list.domain.User;
import bucket.list.service.Login.UserService;
import bucket.list.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createForm(Model model){

        model.addAttribute("user", new User());

        return "user/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "user/create";
        }

        userService.save(user);

        return "/user/create_success";
    }

    @GetMapping("/exist/{user_id}")
    //아이디중복확인 메서드
    public String exist(@Valid @RequestParam("user_id") String user_id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/create";
        }

        return "user/create";
    }

    //비밀번호 일치여부 initbinder
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        UserValidator userValidator = new UserValidator();

        webDataBinder.addValidators(userValidator);
    }
}
