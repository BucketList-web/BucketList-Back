package bucket.list.controller;

import bucket.list.domain.Community;
import bucket.list.domain.Login;
import bucket.list.service.Community.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="/community")
public class CommunityController {


    CommunityService service;
    @Autowired
    public CommunityController(CommunityService service){         // controller와 service 연결하는 느낌

        this.service = service;
    }


//    @GetMapping("")
//    public String Communitymain(Model model){        // 커뮤니티 메인
//
//        return "community/communitymain";
//    }

    @GetMapping("")
    public String Communitymain(Model model, @PageableDefault(page = 0, size=8, sort="communityid", direction = Sort.Direction.DESC)
            Pageable pageable){        // 커뮤니티 메인
        Page<Community> data =service.showCommunityAll(pageable);

        //현재 페이지 변수 Pageable 0페이지부터 시작하기 +1을해줘서 1페이지부터 반영한다
        int nowPage = data.getPageable().getPageNumber() + 1;
        //블럭에서 보여줄 시작페이지(Math.max 한이유는 시작페이지가 마이너스 값일 수는 업으니깐 Math.max를 사용)
        int startPage =Math.max(nowPage-4,1) ;
        //블럭에서 보여줄때 마지막페이지(Math.min 한이유는 총페이지가 10페이지인데, 현재페이지가 9페이지이면 14페이지가되므로 오류,
        //그렇기에 getTotalpage를  min으로설정)
        int endPage = Math.min(nowPage + 5, data.getTotalPages());

        model.addAttribute("data",data);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        return "community/communitymain2";
    }


    @GetMapping("/create")
    public String createStore(HttpServletRequest request){        // 게시물 작성 폼
        HttpSession session = request.getSession();
        System.out.println(((Login)session.getAttribute("loginMember")).getId() + "님이 마이페이지에 들어왔습니다.");
        return "community/communityadd";
    }

    @PostMapping("/create")
    public String createCommunity(HttpServletRequest request,Community community, MultipartFile file) throws IOException {        // 커뮤니티 게시물 작성 폼

        HttpSession session = request.getSession();
        if(session != null){
            community.setCommunitywriter(((Login)session.getAttribute("loginMember")).getId());
            service.createCommunity(community,file);                    //DB에 입력한 값을 넣어야 해요.
        }else{
            service.createCommunity(community,file);
        }

        return "redirect:/community";
    }

    @GetMapping("/finddetail/{id}")       // 해당 게시물 출력
    public String findcommunitydetail(@PathVariable("id")int id, Model model,HttpServletRequest request){

        HttpSession session = request.getSession(false);        // 세션의 유무 설정
           // sessionwriter에 현재 세션의 user이름을 넣어줌


        String dbwriter = service.selectIdSQL(id);       // 해당 게시물의 writer 정보를 dbwriter에 저장함
        String result = "true";
        String temp = (String) session.getAttribute("userid");

        // 현재 수정, 삭제 보여주는 세션이 한번 생성되면 계속 유지가 됨 즉, 상세 페이지에 한해서만 동작되도록 바꿔야함

        if(session == null){
            model.addAttribute("data",service.showCommunityById(id));
            System.out.println("다릅니다.");
            return "community/communitydetail";
        }

        if(session != null){
            String sessionwriter = ((Login)session.getAttribute("loginMember")).getId();
            if(sessionwriter.equals(dbwriter)){
                session.setAttribute("userid", result);
                model.addAttribute("data",service.showCommunityById(id));
                System.out.println("같습니다.");
                return "community/communitydetail";
            }else{
                model.addAttribute("data",service.showCommunityById(id));
                System.out.println("다릅니다.");
                return "community/communitydetail";
            }
        }else{
            return null;
        }

    }

//    @GetMapping("/findall")        // 전체 출력
//    public String findStoreAll(Model model){
//        List<Community> data = service.showCommunityAll();
//        model.addAttribute("data",data);
//
//        return "community/Communitymain";
//    }



    @GetMapping("/edit/{id}")      // 게시글 수정을 위한 form 페이지(이전 값 불러옴)
    public String Communitymodify(@PathVariable("id") int id, Model model){
        model.addAttribute("data", service.showCommunityById(id));
        return "community/communitymodify";
    }

    @PostMapping("/modify/{id}")       // 게시글 수정
    public String commumitymodify(Community community, @PathVariable("id") int id){
        Community communitytemp = service.showCommunityById(id);

        communitytemp.setCommunitytag(community.getCommunitytag());       // 기존의 내용중 이름을 새로운 값으로 덮어씌움
        communitytemp.setCommunitylocation(community.getCommunitylocation());
        communitytemp.setCommunityprice(community.getCommunityprice());
        communitytemp.setCommunitykind(community.getCommunitykind());
        communitytemp.setCommunitycontent(community.getCommunitycontent());
        communitytemp.setCommunitylink(community.getCommunitylink());

        service.createeditCommunity(communitytemp);
        return "redirect:/community";
    }

    @GetMapping("/delete")         // 게시물 삭제
    public String communitydelete(int id){
        service.communitydelete(id);
        return "redirect:/community";
    }

}
