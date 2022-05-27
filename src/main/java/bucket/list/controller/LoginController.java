package bucket.list.controller;

import bucket.list.domain.Community;
import bucket.list.domain.Login;
import bucket.list.domain.User;
import bucket.list.service.Community.CommunityService;
import bucket.list.service.Login.LoginService;
import bucket.list.service.Login.UserService;
import bucket.list.service.Participation.ParticipationService;
import bucket.list.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class LoginController {

    private final LoginService service;
    private final CommunityService communityService;
    private final UserService userService;
    private final ParticipationService participationService;

    @Autowired
    public LoginController(LoginService service,CommunityService communityService,UserService userService,ParticipationService participationService){         // controller와 service 연결하는 느낌
        this.communityService = communityService;
        this.service = service;
        this.userService = userService;
        this.participationService = participationService;
    }

    @GetMapping("/mypage")
    public String mypage(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String name = ((Login)session.getAttribute("loginMember")).getId();
        String user = ((Login)session.getAttribute("loginMember")).getId();
        if(session != null){
            model.addAttribute("participation",participationService.selectAllSQL(name));
            model.addAttribute("data",communityService.selectAllSQL(name));
            model.addAttribute("user",userService.finduser(user));
            return "user/mypage";
        }else{
            return null;
        }
    }

    @GetMapping("/login")
    public String Loginmain(Model model){        // 로그인 메인
        model.addAttribute("login", new Login());
        return "user/logintest";
    }

    @PostMapping("/login")      // 로그인 값 입력
    public String Login(Login m, HttpServletRequest request, HttpServletResponse response){
        Login logindata =new Login();
        logindata.setId(m.getId());     // html에서 입력받은 아이디 값을 Login도메인 안에 저장함.
        logindata.setPw(m.getPw());

        User userdata = service.showUserById(m.getId());
        String dbpw = userdata.getUser_pw();



        if(m.getPw().equals(dbpw)) {

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

                return "redirect:/main"; // 다시 홈으로
            }
        }else{
            return "/main/main";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null) {   // 안에 값이 있으면,

            session.invalidate();// session 만료시킴.
        } else {

        }
        return "redirect:/main"; // 홈(home.html) 화면으로
    }

}