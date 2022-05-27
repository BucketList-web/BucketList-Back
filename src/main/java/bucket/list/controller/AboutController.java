package bucket.list.controller;


import bucket.list.domain.About;
import bucket.list.domain.Login;
import bucket.list.service.about.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/about")
public class AboutController {


    private final AboutService aboutService;

    @Autowired
    public AboutController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    //공지사항 메인페이지 메서드
    @GetMapping()
    //페이징구현하기, Pageable 사용하기 page = 기본페이지, size 한페이지 게시글수,sort 정렬 기준 잡을 변수, direction 오름차순인지 내림차순인지
    public String about(Model model,
                        @PageableDefault(page = 0, size = 10, sort = "aboutnumber",direction = Sort.Direction.DESC) Pageable pageable,
                        HttpServletRequest request) {

        HttpSession session = request.getSession(false);        // 세션의 유무 설정
    //    String adminLoginId = ((Login) session.getAttribute("loginMember")).getId();
        String adminId = "must";
        String result = "true";

        Page<About> about_items = aboutService.allContentList(pageable);

        if (session == null) {
            //현재 페이지 변수 Pageable 0페이지부터 시작하기 +1을해줘서 1페이지부터 반영한다
            int nowPage = about_items.getPageable().getPageNumber() + 1;
            //블럭에서 보여줄 시작페이지(Math.max 한이유는 시작페이지가 마이너스 값일 수는 업으니깐 Math.max를 사용)
            int startPage = Math.max(nowPage - 4, 1);
            //블럭에서 보여줄때 마지막페이지(Math.min 한이유는 총페이지가 10페이지인데, 현재페이지가 9페이지이면 14페이지가되므로 오류,
            //그렇기에 getTotalpage를  min으로설정)
            int endPage = Math.min(nowPage + 5, about_items.getTotalPages());

            model.addAttribute("about_items", about_items);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);

            return "about/about";
        }


        if (session != null) {
            String sessionwriter = ((Login) session.getAttribute("loginMember")).getId();
            if (sessionwriter.equals(adminId)) {
                session.setAttribute("adminid", result);
                int nowPage = about_items.getPageable().getPageNumber() + 1;
                //블럭에서 보여줄 시작페이지(Math.max 한이유는 시작페이지가 마이너스 값일 수는 업으니깐 Math.max를 사용)
                int startPage = Math.max(nowPage - 4, 1);
                //블럭에서 보여줄때 마지막페이지(Math.min 한이유는 총페이지가 10페이지인데, 현재페이지가 9페이지이면 14페이지가되므로 오류,
                //그렇기에 getTotalpage를  min으로설정)
                int endPage = Math.min(nowPage + 5, about_items.getTotalPages());

                model.addAttribute("about_items", about_items);
                model.addAttribute("nowPage", nowPage);
                model.addAttribute("startPage", startPage);
                model.addAttribute("endPage", endPage);

                return "about/about";
            } else {
                //현재 페이지 변수 Pageable 0페이지부터 시작하기 +1을해줘서 1페이지부터 반영한다
                int nowPage = about_items.getPageable().getPageNumber() + 1;
                //블럭에서 보여줄 시작페이지(Math.max 한이유는 시작페이지가 마이너스 값일 수는 업으니깐 Math.max를 사용)
                int startPage = Math.max(nowPage - 4, 1);
                //블럭에서 보여줄때 마지막페이지(Math.min 한이유는 총페이지가 10페이지인데, 현재페이지가 9페이지이면 14페이지가되므로 오류,
                //그렇기에 getTotalpage를  min으로설정)
                int endPage = Math.min(nowPage + 5, about_items.getTotalPages());

                model.addAttribute("about_items", about_items);
                model.addAttribute("nowPage", nowPage);
                model.addAttribute("startPage", startPage);
                model.addAttribute("endPage", endPage);

                return "about/about";
            }
        } else {
            return null;
        }
    }


    //글쓰기페이지
    @GetMapping("/write")
    public String writeForm( ){

        return "about/write";
    }

    @PostMapping("/write")
    public String write(HttpServletRequest request, @ModelAttribute("about")About about, MultipartFile file) throws IOException {

        HttpSession session = request.getSession();
        if(session != null){
            about.setAbout_writer(((Login)session.getAttribute("loginMember")).getId());
            aboutService.save(about,file);
        }

        return "redirect:/about";

    }
    @GetMapping("/{aboutnumber}/read")
    //글읽는 페이지 메서드
    public String read(@PathVariable Integer aboutnumber, Model model,HttpServletRequest request ){

        HttpSession session = request.getSession(false);
        String adminid ="must";
        String result = "true";

        About about = aboutService.oneContentList(aboutnumber);

        if(session == null){
            model.addAttribute("about",about);
            return "about/read";
        }

        if(session != null){
            String sessionwriter = ((Login)session.getAttribute("loginMember")).getId();
            if(sessionwriter.equals(adminid)){
                session.setAttribute("adminid", result);
                model.addAttribute("about",about);
                return "about/read";
            }else{
                model.addAttribute("about",about);
                return "about/read";
            }
        }else{
            return null;
        }
    }


    @GetMapping("/edit/{aboutnumber}")
    //게시글 수정 view 보여주고 전달
    public String editForm(@PathVariable int aboutnumber, Model model){
        About about = aboutService.oneContentList(aboutnumber);
        model.addAttribute("about", about);
        model.addAttribute("number", aboutnumber);
        return "about/edit";
    }

    @PostMapping("/edit/{aboutnumber}")
    //실제 게시글수정, 파일이미지 업로드
    public String edit(@ModelAttribute("aboutnumber") About about,@PathVariable int aboutnumber,MultipartFile file) throws IOException {
        aboutService.save(about,file);
        return "redirect:/about/{aboutnumber}/read";
    }

    @GetMapping("/delete/{aboutnumber}")
    //게시글 삭제
    public String delete(@PathVariable int aboutnumber){

        aboutService.deleteContent(aboutnumber);

        return "redirect:/about";
    }
}
