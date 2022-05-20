package bucket.list.service;

import bucket.list.domain.Board;
import bucket.list.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board save(Board board) {
        //게시글 저장
        Board save = boardRepository.save(board);
        return save;
    }
    public List<Board> AllContentList() {
        //전체게시글
        List<Board> boards = boardRepository.AllContentList();
        return boards;
    }

    public Board oneContentList(int number) {
        //하나의 게시글
        Board board = boardRepository.oneContentList(number);
        return board;
    }
    public void updateContentInfo(Board board) {
        boardRepository.updateContentInfo(board);

    }
}
