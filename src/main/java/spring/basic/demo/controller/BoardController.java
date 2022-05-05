package spring.basic.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.basic.demo.domain.Store;
import spring.basic.demo.service.BoardService;

import java.util.List;


@Controller      //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class BoardController {

    BoardService service;
    @Autowired
    public BoardController(BoardService service){         // controller와 service 연결하는 느낌

        this.service = service;
    }

    @GetMapping("store/new")
    public String createMember(){

        return "store/StorecreateForm";
    }

    // URL 이 변경되지 않은 상태에서 실행
    @PostMapping("store/new")
    public String create(@RequestParam("name") String name,@RequestParam("location") String location,@RequestParam("menu") String menu,@RequestParam("price") int price,@RequestParam("tag") String tag ){

        Store m = new Store();
        m.setName(name);
        m.setLocation(location);
        m.setMenu(menu);
        m.setPrice(price);
        m.setTag(tag);
        //DB에 입력한 값을 넣어야 해요.
        service.join(m);

        return "redirect:/";    // 제일 첫 페이지로 돌아감
    }

    @GetMapping("store/find")
    public String findMember(){

        return "store/StorefindForm";
    }

    @PostMapping("store/find")
    public String find(@RequestParam("id")int id, Model model){
        // Service를 통해서 id로 member를 찾아서
        Store m = service.findMemberBuId(id);

        // 찾은 객체를 통재로 다음 페이지로 넘김
        model.addAttribute("member",m);  // member라는 상자에 m객체를 넣어서 보내줌

        // 다음 페이지로 이동
        return "store/StorefindMember";
    }


    @GetMapping("store/findall")
    public String datalist(Model model){
        List<Store> data = service.findAll();
        model.addAttribute("data",data);

        return "store/Storefindall";
    }

}
