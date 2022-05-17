package spring.basic.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.basic.demo.domain.CreateLogin;
import spring.basic.demo.service.CreateLoginService;


@Controller      //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class CreateLoginController {

    CreateLoginService service;
    @Autowired
    public CreateLoginController(CreateLoginService service){         // controller와 service 연결하는 느낌

        this.service = service;
    }
    @GetMapping("login/loginmember")
    public String MemberLogin(){        // 회원가입 작성 폼

        return "Login/LoginForm";
    }

    @GetMapping("login/createmember")
    public String MemberCreate(){        // 회원가입 작성 폼

        return "Login/CreateLoginForm";
    }

    // URL 이 변경되지 않은 상태에서 실행
    @PostMapping("login/createmember")       // 게시물 작성
    public String MemberCreateData(CreateLoginForm createLoginForm){


        CreateLogin m = new CreateLogin();
        m.setUser_id(createLoginForm.getUser_id());
        m.setUser_pw(createLoginForm.getUser_pw());
        m.setUser_pwcorrect(createLoginForm.getUser_pwcorrect());
        m.setUser_email(createLoginForm.getUser_email());
        m.setUser_name(createLoginForm.getUser_name());
        m.setUser_phone(createLoginForm.getUser_phone());

//        if(createLoginForm.getUser_id() == null && createLoginForm.getUser_pw() == null && createLoginForm.getUser_pwcorrect() ==null &&
//        createLoginForm.getUser_email() ==null&& createLoginForm.getUser_name() ==null&& createLoginForm.getUser_phone()==null){
//            return "Login/CreateLoginForm";
//        }
//        else {
//            //DB에 입력한 값을 넣어야 해요.
//            service.CreateLoginCreate(m);
//            return "redirect:/";    // 제일 첫 페이지로 돌아감
//        }
            service.CreateLoginCreate(m);
            return "redirect:/";
    }



}