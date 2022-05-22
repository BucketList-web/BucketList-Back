package bucket.list.controller;


import bucket.list.domain.Login;
import bucket.list.domain.User;

import bucket.list.service.LoginService;
import bucket.list.service.UserService;
import bucket.list.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.Member;

@Controller
@RequestMapping(value="/user")
public class LoginController {

    private final LoginService service;

    @Autowired
    public LoginController(LoginService service){         // controller와 service 연결하는 느낌

        this.service = service;
    }


    @GetMapping("/login")
    public String Loginmain(){        // 로그인 메인
        return "user/logintest";
    }

    @PostMapping("/login")      // 로그인 값 입력
    public String Login(Login m, HttpServletRequest request, HttpServletResponse response){
        Login logindata =new Login();
        logindata.setId(m.getId());
        logindata.setPw(m.getPw());

        User userdata = service.showUserById(m.getId());
        String dbpw = userdata.getUser_pw();

        int result = 0;                 // 입력받은 값과 배열의 값을 비교하기 위한 결과값


        if(dbpw.equals(m.getPw())) {
            result = 1;
        }

        if(result == 1){

        /*
            로그인 객체를 세션에 담음
         */
            HttpSession session = request.getSession();

            session.setAttribute("loginMember", logindata);


        /*
            Interceptor preHandle에서 저장해둔 가던 길이 있으면,
            꺼내서, 다시 가던 길로 보내줌
         */
            String destURI = (String) session.getAttribute("destURI");

            if(destURI != null) {  // 마이 페이지 눌렀는데, 로그인 안된 상태라 interceptor가 이리로 보낸거면
                session.setAttribute("destURI", null);
                return "redirect:" + destURI;   // 가던길로 돌려줌
            }
            else {  // 로그인 링크를 눌러서 왔으면,
                return "/main/main"; // 다시 홈으로
            }
        }
        return "/main/main";
    }

//    @GetMapping("/create")
//    public String createCommunity(){        // 회원가입 작성 폼
//
//        return "login/create";
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null) {   // 안에 값이 있으면,
            session.invalidate();// session 만료시킴.
        } else {
        }

        return "/index"; // 홈(home.html) 화면으로
    }

}