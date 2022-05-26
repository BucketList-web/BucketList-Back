package bucket.list.controller;


import bucket.list.domain.Comment;
import bucket.list.domain.Login;
import bucket.list.domain.Participation;
import bucket.list.service.Participation.CommentService;
import bucket.list.service.Participation.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Controller

@RequestMapping("/participation")
public class ParticipationController {


    private final ParticipationService participationService;
    private final CommentService commentService;



    @Autowired
    public ParticipationController(ParticipationService participationService, CommentService commentService) {
        this.participationService = participationService;
        this.commentService = commentService;

    }

    @GetMapping
    //전체 글보여주는 페이지
    //participationidx을 기준으로 정렬
    public String items(Model model,@PageableDefault(page = 0, size = 8, sort = "participationidx",direction = Sort.Direction.DESC) Pageable pageable){
        Page<Participation> items = participationService.AllContentList(pageable);

        //현재 페이지 변수 Pageable 0페이지부터 시작하기 +1을해줘서 1페이지부터 반영한다
        int nowPage = items.getPageable().getPageNumber() + 1;
        //블럭에서 보여줄 시작페이지(Math.max 한이유는 시작페이지가 마이너스 값일 수는 업으니깐 Math.max를 사용)
        int startPage =Math.max(nowPage-4,1) ;
        //블럭에서 보여줄때 마지막페이지(Math.min 한이유는 총페이지가 10페이지인데, 현재페이지가 9페이지이면 14페이지가되므로 오류,
        //그렇기에 getTotalpage를  min으로설정)
        int endPage = Math.min(nowPage + 5, items.getTotalPages());

        model.addAttribute("items", items);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "participation/main";
    }

    @GetMapping("/write")
    //글 추가하는 view보여주는 메서드
    public String addForm(Model model){

        model.addAttribute("participation", new Participation());

        return "participation/write";
    }

    @PostMapping("/write")
    public String add(HttpServletRequest request, @ModelAttribute("participation")Participation participation, MultipartFile file) throws IOException {

        HttpSession session = request.getSession();
        if(session != null){
            participation.setParticipation_writer(((Login)session.getAttribute("loginMember")).getId());
            participationService.save(participation,file);
        }

        return "redirect:/participation";
    }
    @GetMapping("{participationidx}")
    //전체게시글에서 하나의 게시글 클릭시 하나의게시글보여주는 메서드
    public String item(@PathVariable int participationidx, Model model,HttpServletRequest request){

        HttpSession session = request.getSession(false);// sessionwriter에 현재 세션의 user이름을 넣어줌
        String dbwriter2 = participationService.selectIdSQL(participationidx);       // 해당 게시물의 writer 정보를 dbwriter에 저장함

        Participation participation = participationService.oneContentList(participationidx);
        List<Comment> comments = commentService.allContentList(participationidx);
        participationService.updateCount(participationidx);
        String result2 = "true";

        if(session == null){                // 세션이 없을떄
            model.addAttribute("comments", comments);
            model.addAttribute("participation", participation);
            model.addAttribute("participationidx",participationidx);
            return "participation/read";
        }

        if(session != null){                // 세션이 있을때
            String sessionwriter2 = ((Login)session.getAttribute("loginMember")).getId();
            if(sessionwriter2.equals(dbwriter2)){     // 현재 세션의 아이디와 게시물 작성자 아이디가 같다면
                session.setAttribute("result2", result2);
                model.addAttribute("comments", comments);
                model.addAttribute("participation", participation);
                model.addAttribute("participationidx",participationidx);
                return "participation/read";
            }else{
                model.addAttribute("comments", comments);
                model.addAttribute("participation", participation);
                model.addAttribute("participationidx",participationidx);
                return "participation/read";
            }
        }else{
            return null;
        }
    }

    @PostMapping("/comment/{participationidx}")
    //댓글 저장
    public String comment(HttpServletRequest request,@PathVariable int participationidx,
                          @ModelAttribute("comment") Comment comment,Model model){
//        @RequestParam("comment_text") String comment_text

//        Comment comment = new Comment();
        comment.setComment_number(participationidx);
//        comment.setComment_text(comment_text);

        HttpSession session = request.getSession();



        if(session != null){
            comment.setComment_writer(((Login)session.getAttribute("loginMember")).getId());
            commentService.save(comment);
        }
        String sessionwriter = ((Login)session.getAttribute("loginMember")).getId();   // sessionwriter에 현재 세션의 user이름을 넣어줌
        String dbwriter = commentService.selectIdSQL(comment.getCommentidx());

        if(session.equals(dbwriter)){
            model.addAttribute("dbwriter", dbwriter);
        }

        return "redirect:/participation/{participationidx}";


    }


    @GetMapping("/edit/{participationidx}")
    //게시글 수정 view 보여주고 전달
    public String editForm(@PathVariable int participationidx, Model model){
        Participation participation = participationService.oneContentList(participationidx);
        model.addAttribute("participation", participation);
        model.addAttribute("number", participationidx);
        return "participation/edit";
    }

    @PostMapping("/edit/{participationidx}")
    //실제 게시글수정, 파일이미지 업로드
    public String edit(@ModelAttribute("participation") Participation participation,@PathVariable int participationidx,MultipartFile file) throws IOException {
        participationService.save(participation,file);
        return "redirect:/participation/{participationidx}";
    }

    @GetMapping("/delete/{participationidx}")
    //게시글 삭제
    public String delete(@PathVariable int participationidx){

        participationService.deleteContent(participationidx);

        return "redirect:/participation";
    }
    @GetMapping("/commentEdit/{commentidx}/{participationidx}")
    public String commentEdit(@PathVariable int participationidx,
                              @PathVariable int commentidx,
                              Model model){



        return "redirect:/participation/{participationidx}";

    }
    @GetMapping("/commentDelete/{commentidx}/{participationidx}")
    public String commentEdit(@PathVariable int participationidx,
                              @PathVariable int commentidx){

        commentService.deleteComment(commentidx);

        return "redirect:/participation/{participationidx}";
    }
}
