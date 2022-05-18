package bucket.list.repository;

import bucket.list.domain.BoardInfo;
import bucket.list.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TopMenuRepositoryImple implements TopMenuRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TopMenuRepositoryImple(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<BoardInfo> allInfoName() {

        return jdbcTemplate.query("select board_info_idx, board_info_name from board_info_table order by board_info_idx", boardRowMapper());

    }

    private RowMapper<BoardInfo> boardRowMapper() {
        return new RowMapper<BoardInfo>() {
            @Override
            public BoardInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                BoardInfo boardInfo = new BoardInfo();


                boardInfo.setBoard_info_idx(rs.getInt("board_info_idx"));
                boardInfo.setBoard_info_name(rs.getString("board_info_name"));

                return boardInfo;
            }
        };
    }
}
