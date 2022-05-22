//package bucket.list.repository;
//
//import bucket.list.domain.Comment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//public class CommentRepositoryImpl implements CommentRepository{
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public CommentRepositoryImpl(DataSource dataSource) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//
//    @Override
//    public Comment save(Comment comment) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);       // sql 구문 생성 INSERT INTO
//        jdbcInsert.withTableName("comment").usingGeneratedKeyColumns("comment_idx");      // ps <- SQL, id 값 자동 생성 요청
//
//        Map<String, Object> parameters = new HashMap<>();
//
//
//        parameters.put("comment_text", comment.getComment_text());
//        parameters.put("comment_writer", comment.getComment_writer());
//        parameters.put("comment_number", comment.getComment_number());
//
//
//        Number key = jdbcInsert.executeAndReturnKey(new
//                MapSqlParameterSource(parameters));
//
//        comment.setComment_idx(key.intValue());
//
//        return comment;
//    }
//
//    @Override
//    public List<Comment> oneCommentList(int comment_number) {
//        return  jdbcTemplate.query("select * from comment where comment_number =? order by comment_idx desc", boardRowMapper(), comment_number);
//    }
//
//    private RowMapper<Comment> boardRowMapper (){
//        return new RowMapper<Comment>() {
//            @Override
//            public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Comment comment = new Comment();
//
//                comment.setComment_idx(rs.getInt("comment_idx"));
//                comment.setComment_text(rs.getString("comment_text"));
//                comment.setComment_writer(rs.getString("comment_writer"));
//                comment.setComment_number(rs.getInt("comment_number"));
//
//                return comment;
//            }
//        };
//    }
//}
