package bucket.list.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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