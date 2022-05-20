package bucket.list.controller;

import bucket.list.domain.Comment;
import bucket.list.domain.Join;
import bucket.list.service.JoinService;
import bucket.list.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/board")
public class CommentController {

    private final JoinService joinService;
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService, JoinService joinService) {
        this.commentService = commentService;
        this.joinService = joinService;
    }

//    @GetMapping("comment/{number}")
//    //댓글 폼으로
//    public String commentForm(@PathVariable int number, Model model){ //해당게시글의 댓글을 연동하기위해 게시글의 번호를 전달한다
//
//        Join join = joinService.oneContentList(number);
//        model.addAttribute("join",join);
//
//        return "comment";
//    }

        Comment comment = new Comment();
        comment.setComment_idx(number);
        comment.setComment_text(comment_text);
        commentService.save(comment);

        return "redirect:/board/{number}";
    }
//    @GetMapping("/commentList/{number}")
//    public String commentList(@PathVariable("number") int number, Model model){
//        List<Comment> comments = commentService.oneContentList(number);
//
//        model.addAttribute("comments", comments);
//
//
//        return "comment_list";
//
//    }


}
