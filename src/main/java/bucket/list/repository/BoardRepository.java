package bucket.list.repository;

import bucket.list.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BoardRepository {

    Board save(Board board);

    List<Board> AllContentList();

    Board oneContentList(int number);

   void updateContentInfo(Board board);
}
