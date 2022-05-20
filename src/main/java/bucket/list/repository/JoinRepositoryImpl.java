package bucket.list.repository;

import bucket.list.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BoardRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //저장하는 메서드
    @Override
    public Board save(Board board) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);       // sql 구문 생성 INSERT INTO
        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("number");      // ps <- SQL, id 값 자동 생성 요청

        Map<String, Object> parameters = new HashMap<>();


        parameters.put("text", board.getText());
        parameters.put("cost", board.getCost());
        parameters.put("party", board.getParty());


        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));

        board.setNumber(key.intValue());

        return board;

    }

    @Override
    //모든 게시물을 가져오는 메서드
    public List<Board> AllContentList() {
        return jdbcTemplate.query("select * from board order by number desc", boardRowMapper());
    }

    @Override
    //하나의 게시물을 가져오는 메서드
    public Board oneContentList(int number) {
         return jdbcTemplate.query("select * from board where number =?", boardRowMapper(), number).get(0);
    }

    @Override
    //게시글 수정
    public void updateContentInfo(Board board) {

        jdbcTemplate.update("update board set text = ?, cost = ?, party = ? where number = ?",board.getText(), board.getCost(),board.getParty(),board.getNumber());
    }


    private RowMapper<Board> boardRowMapper (){
        return new RowMapper<Board>() {
            @Override
            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                Board board = new Board();
                board.setNumber(rs.getInt("number"));
                board.setText(rs.getString("text"));
                board.setCost(rs.getInt("cost"));
                board.setParty(rs.getInt("party"));


                return board;
            }
        };
    }
}
