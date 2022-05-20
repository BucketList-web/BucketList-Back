package bucket.list.controller;

import bucket.list.domain.Comment;
import bucket.list.domain.Join;
import bucket.list.service.JoinService;
import bucket.list.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/menu/{board_info_idx}")
@RequestMapping("/join")
public class JoinController {

    private final JoinService joinService;
    private final CommentService commentService;



    @Autowired
    public JoinController(JoinService joinService, CommentService commentService) {
        this.joinService = joinService;
        this.commentService = commentService;

    }

    @GetMapping
    //전체 글보여주는 페이지
    public String items(Model model){
        List<Join> items = joinService.AllContentList();
        model.addAttribute("items", items);

        return "join/main";
    }

    @GetMapping("/write")
    //글 추가하는 view보여주는 메서드
    public String addForm(){
        return "join/write";
    }

    @PostMapping("/write")
    //add.html view에서 작성한 add메서드
    public String add(@ModelAttribute("join") Join join){
        joinService.save(join);


        return "redirect:/join";
    }
    @GetMapping("{join_idx}")
    //전체게시글에서 하나의 게시글 클릭시 하나의게시글보여주는 메서드
    public String item(@PathVariable int join_idx, Model model){

        Join join = joinService.oneContentList(join_idx);
        List<Comment> comments = commentService.oneContentList(join_idx);

        model.addAttribute("comments", comments);

        model.addAttribute("join", join);

        model.addAttribute("join_idx",join_idx);

        return "join/read";
    }
    @PostMapping("/comment/{join_idx}")
    //댓글 저장
    public String comment(@PathVariable int join_idx,
                          @RequestParam("comment_text") String comment_text){

        
        Comment comment = new Comment();
        comment.setComment_number(join_idx);
        comment.setComment_text(comment_text);

        commentService.save(comment);

        return "redirect:/join/{join_idx}";
    }

    @GetMapping("/edit/{join_idx}")
    //게시글 수정 view 보여주고 전달
    public String editForm(@PathVariable int join_idx, Model model){
        Join join = joinService.oneContentList(join_idx);
        model.addAttribute("join", join);
        return "edit";
    }
    @PostMapping("/edit/{join_idx}")
    //실제 게시글수정
    public String edit(@ModelAttribute("join") Join join,@PathVariable int join_idx){
        joinService.updateContentInfo(join);
        return "redirect:/join/{join_idx}";
    }


}
