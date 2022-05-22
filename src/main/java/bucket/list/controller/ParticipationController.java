package bucket.list.controller;

import bucket.list.domain.Comment;
import bucket.list.domain.Participation;
import bucket.list.service.ParticipationService;
import bucket.list.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
    public String items(Model model,@PageableDefault(page = 0, size = 6, sort = "participationidx",direction = Sort.Direction.DESC) Pageable pageable){
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
    public String addForm(){
        return "participation/write";
    }

    @PostMapping("/write")
    public String add(@ModelAttribute("participation")Participation participation, MultipartFile file) throws IOException {
//    public String add() {



        participationService.save(participation,file);


        return "redirect:/participation";
    }
    @GetMapping("{participationidx}")
    //전체게시글에서 하나의 게시글 클릭시 하나의게시글보여주는 메서드
    public String item(@PathVariable int participationidx, Model model){

        Participation participation = participationService.oneContentList(participationidx);
        int participation_count = participationService.updateCount(participationidx);
        List<Comment> comments = commentService.allContentList(participationidx);

        model.addAttribute("comments", comments);

        model.addAttribute("participation", participation);

        model.addAttribute("participationidx",participationidx);

        model.addAttribute("participation_count", participation_count);

        return "participation/read";
    }
    @PostMapping("/comment/{participationidx}")
    //댓글 저장
    public String comment(@PathVariable int participationidx,
                          @RequestParam("comment_text") String comment_text){

        
        Comment comment = new Comment();
        comment.setComment_number(participationidx);
        comment.setComment_text(comment_text);

        commentService.save(comment);

        return "redirect:/participation/{participationidx}";
    }

    @GetMapping("/edit/{participationidx}")
    //게시글 수정 view 보여주고 전달
    public String editForm(@PathVariable int participationidx, Model model){
         Participation participation = participationService.oneContentList(participationidx);
        model.addAttribute("participation", participation);
        return "participation/edit";
    }
    
    @PostMapping("/edit/{participationidx}")
    //실제 게시글수정, 
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

}
