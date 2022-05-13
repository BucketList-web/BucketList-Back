package spring.basic.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.basic.demo.domain.Community;
import spring.basic.demo.service.BoardService;

import java.util.List;


@Controller      //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class BoardController {

    BoardService service;
    @Autowired
    public BoardController(BoardService service){         // controller와 service 연결하는 느낌

        this.service = service;
    }

    // ------------- 맛집 부분 --------------
    @GetMapping("community/new")
    public String createStore(){

        return "community/CommunitycreateForm";
    }

    // URL 이 변경되지 않은 상태에서 실행
    @PostMapping("community/new")
    public String createStoreData(BoardForm boardForm){

        Community m = new Community();
        m.setCommunitytag(boardForm.getCommunitytag());
        m.setCommunitykind(boardForm.getCommunitykind());
        m.setCommunitylocation(boardForm.getCommunitylocation());
        m.setCommunitycontent(boardForm.getCommunitycontent());
        m.setCommunityprice(boardForm.getCommunityprice());



        //DB에 입력한 값을 넣어야 해요.
        service.createCommunity(m);

        return "redirect:/";    // 제일 첫 페이지로 돌아감
    }

    @GetMapping("community/find")
    public String findStore(){

        return "community/CommunityfindForm";
    }

    @PostMapping("community/find")
    public String findStoreData(@RequestParam("id")int id, Model model){
        // Service를 통해서 id로 member를 찾아서
        Community m = service.showCommunityById(id);

        // 찾은 객체를 통재로 다음 페이지로 넘김
        model.addAttribute("member",m);  // member라는 상자에 m객체를 넣어서 보내줌

        // 다음 페이지로 이동
        return "community/CommunityfindMember";
    }


    @GetMapping("community/findall")
    public String findStoreAll(Model model){
        List<Community> data = service.showCommunityAll();
        model.addAttribute("data",data);

        return "community/Communityfindall";
    }
}
