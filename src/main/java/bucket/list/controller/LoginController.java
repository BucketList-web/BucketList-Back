package bucket.list.controller;

import bucket.list.domain.CreateLogin;
import bucket.list.domain.Login;
import bucket.list.service.CreateLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;

@Controller
@RequestMapping(value="/user")
public class LoginController {

    @GetMapping("/login")
    public String Loginmain(){        // 로그인 메인
        return "login/logintest";
    }

    @PostMapping("/login")      // 로그인 값 입력
    public String Login(Login m, HttpServletRequest request, HttpServletResponse response){
        Login logindata =new Login();
        logindata.setId(m.getId());
        logindata.setPw(m.getPw());

        String logintest[] = {"aaa","bbb","ccc","ddd"};         // DB연결하기 전이라 일단 아이디는 배열에 저장

        int result = 0;                 // 입력받은 값과 배열의 값을 비교하기 위한 결과값

        for(int i = 0; i<logintest.length; i++){
            if(m.getId().equals(logintest[i])){
                result = 1;
            }
        }

        if(result == 1){
            System.out.println(logindata.getId() + "님이 로그인 하셨습니다.");
        /*
            로그인 객체를 세션에 담음
         */
            HttpSession session = request.getSession();

            session.setAttribute("loginMember", logindata);
            System.out.println(((Login)session.getAttribute("loginMember")).getId() + "님의 로그인 세션이 생성되었습니다.");

        /*
            Interceptor preHandle에서 저장해둔 가던 길이 있으면,
            꺼내서, 다시 가던 길로 보내줌
         */
            String destURI = (String) session.getAttribute("destURI");

            if(destURI != null) {  // 마이 페이지 눌렀는데, 로그인 안된 상태라 interceptor가 이리로 보낸거면
                System.out.println("마이페이지를 가려다 로그인으로 빠지셨네요. 다시 마이페이지로 돌아갑니다.");
                session.setAttribute("destURI", null);
                return "redirect:" + destURI;   // 가던길로 돌려줌
            }
            else {  // 로그인 링크를 눌러서 왔으면,
                System.out.println("다시 홈으로 돌아갑니다.");
                return "/main/main"; // 다시 홈으로
            }
        }

        else{
            return "login/create";
        }
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
            System.out.println(((Login)session.getAttribute("loginMember")).getId() + "님의 로그인 세션이 종료되었습니다.");
            session.invalidate();// session 만료시킴.
        } else {
            System.out.println("로그인 세션이 없습니다.");
        }

        return "/main/main"; // 홈(home.html) 화면으로
    }


    // ------------- 회원 가입 부분 --------
    CreateLoginService service;
    @Autowired
    public LoginController(CreateLoginService service){         // controller와 service 연결하는 느낌

        this.service = service;
    }

    @GetMapping("/create")
    public String MemberCreate(){        // 회원가입 작성 폼

        return "login/createlogintest";
    }

    // URL 이 변경되지 않은 상태에서 실행
    @PostMapping("/create")       // 회원가입 데이터 저장
    public String MemberCreateData(CreateLoginForm createLoginForm){


        CreateLogin m = new CreateLogin();
        m.setUser_id(createLoginForm.getUser_id());
        m.setUser_pw(createLoginForm.getUser_pw());
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
        return "/login/logintest";
    }
}