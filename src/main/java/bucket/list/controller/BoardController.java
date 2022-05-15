package bucket.list.controller;

import bucket.list.domain.Board;
import bucket.list.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    //전체 글보여주는 페이지
    public String items(Model model){
        List<Board> items = boardService.AllContentList();
        model.addAttribute("items", items);

        return "items";
    }

    @GetMapping("/add")
    //글 추가하는 view보여주는 메서드
    public String addForm(){
        return "add";
    }

    @PostMapping("/add")
    //add.html view에서 작성한 add메서드
    public String add(@ModelAttribute("board") Board board){
        boardService.save(board);

        return "redirect:/board/items";
    }
    @GetMapping("{number}")
    //전체게시글에서 하나의 게시글 클릭시 하나의게시글보여주는 메서드
    public String item(@PathVariable int number, Model model){

        Board board = boardService.oneContentList(number);

        model.addAttribute("board", board);

        return "item";
    }
    @GetMapping("/edit/{number}")
    //게시글 수정 view 보여주고 전달
    public String editForm(@PathVariable int number, Model model){
        Board board = boardService.oneContentList(number);
        model.addAttribute("board", board);
        return "edit";
    }
    @PostMapping("/edit/{number}")
    //실제 게시글수정
    public String edit(@ModelAttribute("board") Board board,@PathVariable int number){
        boardService.updateContentInfo(board);
        return "redirect:/board/{number}";
    }
}
