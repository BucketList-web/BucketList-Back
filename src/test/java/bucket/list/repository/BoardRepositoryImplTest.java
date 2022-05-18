package bucket.list.repository;

import bucket.list.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BoardRepositoryImplTest {

    @Autowired
    BoardRepository repo;

    @Test
    void updateContentInfo() {
        Board board = new Board();
        board.setText("조아");
        board.setCost(3000);
        board.setParty(1);
        repo.updateContentInfo(board);
        Assertions.assertThat(board.getCost()).isEqualTo(3000);
    }
}