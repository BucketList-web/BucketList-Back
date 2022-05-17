package spring.basic.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.basic.demo.domain.Community;
import spring.basic.demo.service.BoardService;

import java.awt.print.Pageable;
import java.util.List;


@Controller      //spring bean 사용하여 따로 설정했으므로 삭제해야함
@RequestMapping(value="/community")
public class BoardController {

    BoardService service;
    @Autowired
    public BoardController(BoardService service){         // controller와 service 연결하는 느낌

        this.service = service;
    }

    @GetMapping("/new")
    public String createStore(){        // 게시물 작성 폼

        return "community/CommunitycreateForm";
    }

    // URL 이 변경되지 않은 상태에서 실행
    @PostMapping("/new")       // 게시물 작성
    public String createStoreData(BoardForm boardForm){

        Community m = new Community();
        m.setCommunitytag(boardForm.getCommunitytag());
        m.setCommunitykind(boardForm.getCommunitykind());
        m.setCommunitylocation(boardForm.getCommunitylocation());
        m.setCommunitycontent(boardForm.getCommunitycontent());
        m.setCommunityprice(boardForm.getCommunityprice());
        //DB에 입력한 값을 넣어야 해요.
        service.createCommunity(m);

        System.out.println(boardForm.getCommunitydate());
        return "redirect:/";    // 제일 첫 페이지로 돌아감
    }

    @GetMapping("/finddetail")       // 해당 게시물 출력
    public String findcommunitydetail(int id, Model model){
        Community community = service.showCommunityById(id);
        model.addAttribute("member", community);
        return "community/Communityfinddetail";
    }

    @GetMapping("/findall")        // 전체 출력
    public String findStoreAll(Model model){
        List<Community> data = service.showCommunityAll();
        model.addAttribute("data",data);

        return "community/Communityfindall";
    }

    @GetMapping("/edit/{id}")      // 게시글 수정을 위한 form 페이지(이전 값 불러옴)
    public String Communitymodify(@PathVariable("id") int id, Model model){
        model.addAttribute("data", service.showCommunityById(id));
        return "community/Communitymodify";
    }

    @PostMapping("/modify/{id}")       // 게시글 수정
    public String commumitymodify(Community community,@PathVariable int id){
        Community communitytemp = service.showCommunityById(id);

        communitytemp.setCommunitytag(community.getCommunitytag());       // 기존의 내용중 이름을 새로운 값으로 덮어씌움
        communitytemp.setCommunitylocation(community.getCommunitylocation());
        communitytemp.setCommunityprice(community.getCommunityprice());
        communitytemp.setCommunitykind(community.getCommunitykind());
        communitytemp.setCommunitycontent(community.getCommunitycontent());

        service.commumitymodify(communitytemp);
        return "redirect:/community/findall";
    }

    @GetMapping("/delete/{id}")         // 게시물 삭제
    public String communitydelete(@PathVariable int id){
        Community communitytemp = service.showCommunityById(id);
        service.communitydelete(communitytemp);
        return "redirect:/community/findall";
    }


}