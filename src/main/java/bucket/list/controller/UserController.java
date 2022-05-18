package bucket.list.controller;

import bucket.list.domain.User;
import bucket.list.service.UserService;
import bucket.list.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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



    @GetMapping("/login")
    public String Login(){
        return "user/login";
    }

    @GetMapping("/create")
    public String createForm(Model model){

        model.addAttribute("user", new User());

        return "user/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "/user/create";
        }

        userService.save(user);

        return "/user/create_success";
    }

    //비밀번호 일치여부 initbinder
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        UserValidator userValidator = new UserValidator();

        webDataBinder.addValidators(userValidator);
    }
}
