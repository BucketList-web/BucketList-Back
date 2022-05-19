//package bucket.list.controller;
//
//import bucket.list.domain.CreateLogin;
//import bucket.list.service.CreateLoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping(value = "/user")
//public class CreateMemberController {
//    CreateLoginService service;
//    @Autowired
//    public CreateMemberController(CreateLoginService service){         // controller와 service 연결하는 느낌
//
//        this.service = service;
//    }
//
//    @GetMapping("/create")
//    public String MemberCreate(){        // 회원가입 작성 폼
//
//        return "login/createlogintest";
//    }
//
//    // URL 이 변경되지 않은 상태에서 실행
//    @PostMapping("/create")       // 회원가입 데이터 저장
//    public String MemberCreateData(CreateLoginForm createLoginForm){
//
//
//        CreateLogin m = new CreateLogin();
//        m.setUser_id(createLoginForm.getUser_id());
//        m.setUser_pw(createLoginForm.getUser_pw());
//        m.setUser_email(createLoginForm.getUser_email());
//        m.setUser_name(createLoginForm.getUser_name());
//        m.setUser_phone(createLoginForm.getUser_phone());
//
////        if(createLoginForm.getUser_id() == null && createLoginForm.getUser_pw() == null && createLoginForm.getUser_pwcorrect() ==null &&
////        createLoginForm.getUser_email() ==null&& createLoginForm.getUser_name() ==null&& createLoginForm.getUser_phone()==null){
////            return "Login/CreateLoginForm";
////        }
////        else {
////            //DB에 입력한 값을 넣어야 해요.
////            service.CreateLoginCreate(m);
////            return "redirect:/";    // 제일 첫 페이지로 돌아감
////        }
//        service.CreateLoginCreate(m);
//        return "/login/logintest";
//    }
//
//}
//
