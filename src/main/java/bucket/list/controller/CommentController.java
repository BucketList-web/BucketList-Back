package bucket.list.controller;

import bucket.list.domain.Comment;
import bucket.list.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("comment/{number}")
    //댓글 폼으로
    public String commentForm(@PathVariable int number, Model model){ //해당게시글의 댓글을 연동하기위해 게시글의 번호를 전달한다
        model.addAttribute("number",number);


        return "comment";
    }

    @PostMapping("/comment/{number}/")
    //댓글 저장
    public String comment(@RequestParam("number") int number,
                          @RequestParam("comment_text") String comment_text){

        Comment comment = new Comment();
        comment.setComment_idx(number);
        comment.setComment_text(comment_text);
        commentService.save(comment);

        return "redirect:/board/{number}";
    }
}
