package bucket.list.controller;

import bucket.list.domain.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/menu/3")
public class CommunityController {

    @GetMapping("")
    public String Communitymain(HttpServletRequest request){        // 커뮤니티 메인

        HttpSession session = request.getSession();
        System.out.println(((Login)session.getAttribute("loginMember")).getId() + "님이 마이페이지에 들어왔습니다.");

        return "community/communitymain";
    }

    @GetMapping("/create")
    public String createCommunity(){        // 커뮤니티 게시물 작성 폼

        return "community/communityadd";
    }
}
