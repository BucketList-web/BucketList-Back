package bucket.list.repository.Community;

import bucket.list.domain.Communityjdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// h2 DB연결 부분
@Repository     //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class CommunityRepository implements CommunityRepositoryInterface {



    // JdbcTemplate 사용하여 Repository 작성
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public CommunityRepository(DataSource dataSource){     // spring bean으로 가지고 있던 datasource를
        jdbcTemplate = new JdbcTemplate(dataSource);                //
    }

    @Override
    public void writecommunity(Communityjdbc m) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);       // sql 구문 생성 INSERT INTO
        jdbcInsert.withTableName("community").usingGeneratedKeyColumns("id");      // ps <- SQL, id 값 자동 생성 요청

        Map<String, Object> parameters = new HashMap<>();
        // string => 열의 이름
        // object => 그 자리에 들어갈 값 (INTEGER,STRING을 사용하지 않은 이유는 OBJECT사용시 모든 형식사용 가능하기 때문)
        parameters.put("tag", m.getCommunitytag());
        parameters.put("kind", m.getCommunitykind());
        parameters.put("location", m.getCommunitylocation());
        parameters.put("content", m.getCommunitycontent());
        parameters.put("price", m.getCommunityprice());
        parameters.put("createdate", m.getCommunitydate());
        parameters.put("subject",m.getCommunitysubject());
        parameters.put("link",m.getCommunitylink());


        jdbcInsert.execute(parameters);
        //jdbcInsert.executeAndReturnKey(new MapSqlParameterSource((parameters)));   // ID(기본키)를 자동으로 생성하기 위한 구문
    }

    @Override
    public Communityjdbc findcommunityById(int id) {
        //스트림을 사용하지 않을 경우
        return jdbcTemplate.query("Select *from community WHERE id=?",
                communityRowMapper(),id).get(0);
    }

    @Override
    public List<Communityjdbc> findcommunityAll() {
        return jdbcTemplate.query("SELECT *FROM community",communityRowMapper());
    }


    public void communitymodify(Communityjdbc community) {
        jdbcTemplate.update("update community set tag = ?, kind = ?, location = ?, content = ?, price =? where id = ?",community.getCommunitytag(),community.getCommunitykind(),community.getCommunitylocation(), community.getCommunitycontent(), community.getCommunityprice(),community.getCommunityid());
    }

    public void communitydelete(Communityjdbc community) {
        jdbcTemplate.update("delete from community where id = ?",community.getCommunityid());
    }

    // select 했을때 사용할 결과 행들
    private RowMapper<Communityjdbc> communityRowMapper(){
        // RowMapper를 반환해주는 메소드를 작성
        return  new RowMapper<Communityjdbc>() {       // new RowMapper<Member>() {} => {}안에 메소드 호출하여 바로 사용하면 굳이 따로
            // class를 통해 implement를 해서 작업하지 않아도 됨
            @Override
            public Communityjdbc mapRow(ResultSet rs, int rowNum) throws SQLException {
                //ResultSet(Select 결과물)을 Member 객체에 저장
                // 여러 행이 반환되도, JdbcTemplate이 rowNum 만큼 돌려서 쓴다.
                // 그래서 우리는 한 행만 넣는 척 해도 된다.

                Communityjdbc m = new Communityjdbc();
                //1행을 m에 담아서
                m.setCommunityid(rs.getInt("id"));   // m객체의 id값 저장
                m.setCommunitytag(rs.getString("tag"));
                m.setCommunitykind(rs.getString("kind"));
                m.setCommunitylocation(rs.getString("location"));
                m.setCommunitycontent(rs.getString("content"));
                m.setCommunityprice(rs.getString("price"));
                m.setCommunitysubject(rs.getString("subject"));
                m.setCommunitylink(rs.getString("link"));

                return m;
            }
        };
    }
}
