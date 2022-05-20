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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JoinRepositoryImpl implements JoinRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JoinRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //저장하는 메서드
    @Override
    public Join save(Join join) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);       // sql 구문 생성 INSERT INTO
        jdbcInsert.withTableName("join_table").usingGeneratedKeyColumns("join_idx");      // ps <- SQL, id 값 자동 생성 요청

        Map<String, Object> parameters = new HashMap<>();


        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nowTime = now.format(formatter);

        join.setJoin_date(nowTime);

        parameters.put("join_option", join.getJoin_option());
        parameters.put("join_tag", join.getJoin_tag());
        parameters.put("join_place", join.getJoin_place());
        parameters.put("join_cost", join.getJoin_cost());
        parameters.put("join_party", join.getJoin_party());
        parameters.put("join_text", join.getJoin_text());
        parameters.put("join_file", join.getJoin_file());
        parameters.put("join_date", join.getJoin_date());
        parameters.put("join_writer", join.getJoin_writer());
        parameters.put("join_subject", join.getJoin_subject());


        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));

        join.setJoin_idx(key.intValue());

        return join;

    }

    @Override
    //모든 게시물을 가져오는 메서드
    public List<Join> AllContentList() {
        return jdbcTemplate.query("select * from join_table order by join_idx desc", joinRowMapper());
    }

    @Override
    //하나의 게시물을 가져오는 메서드
    public Join oneContentList(int join_idx) {
         return jdbcTemplate.query("select * from join_table where join_idx =?", joinRowMapper(), join_idx).get(0);
    }

    @Override
    //게시글 수정
    public void updateContentInfo(Join join) {

        jdbcTemplate.update("update join_table set join_option = ?, join_tag = ?, join_place = ?, join_cost = ?, join_party = ?, " +
                "join_text = ?, join_file = ?, join_subject = ? " +
                " where join_idx = ?",join.getJoin_option(), join.getJoin_tag(), join.getJoin_place(), join.getJoin_cost(), join.getJoin_party(),
                join.getJoin_text(), join.getJoin_file(), join.getJoin_subject());
    }


    private RowMapper<Join> joinRowMapper (){
        return new RowMapper<Join>() {
            @Override
            public Join mapRow(ResultSet rs, int rowNum) throws SQLException {
                Join join = new Join();

                join.setJoin_idx(rs.getInt("join_idx"));
                join.setJoin_option(rs.getString("join_option"));
                join.setJoin_tag(rs.getString("join_tag"));
                join.setJoin_place(rs.getString("join_place"));
                join.setJoin_cost(rs.getInt("join_cost"));
                join.setJoin_party(rs.getInt("join_party"));
                join.setJoin_text(rs.getString("join_text"));
                join.setJoin_file(rs.getString("join_file"));
                join.setJoin_date(rs.getString("join_date"));
                join.setJoin_writer(rs.getString("join_writer"));
                join.setJoin_subject(rs.getString("join_subject"));


                return join;
            }
        };
    }
}
