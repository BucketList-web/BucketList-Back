package bucket.list.repository.Login;

import bucket.list.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// h2 DB연결 부분
@Repository     //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class LoginTemplateRepository implements LoginRepositoryInterface {



    // JdbcTemplate 사용하여 Repository 작성
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public LoginTemplateRepository(DataSource dataSource){     // spring bean으로 가지고 있던 datasource를
        jdbcTemplate = new JdbcTemplate(dataSource);                //
    }

    @Override
    public User findUserById(String id) {                   //  마이페이지를 위한 아이디를 입력하여 나머지 정보 출력
        //스트림을 사용하지 않을 경우
        return jdbcTemplate.query("Select *from userdata WHERE user_id=?",
                LoginRowMapper(),id).get(0);
    }

    @Override
    public List<User> findIdAll() {
        return jdbcTemplate.query("SELECT user_id FROM userdata",LoginRowMapper());
    }

    // select 했을때 사용할 결과 행들
    private RowMapper<User> LoginRowMapper(){
        // RowMapper를 반환해주는 메소드를 작성
        return  new RowMapper<User>() {       // new RowMapper<Member>() {} => {}안에 메소드 호출하여 바로 사용하면 굳이 따로
            // class를 통해 implement를 해서 작업하지 않아도 됨
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                //ResultSet(Select 결과물)을 Member 객체에 저장
                // 여러 행이 반환되도, JdbcTemplate이 rowNum 만큼 돌려서 쓴다.
                // 그래서 우리는 한 행만 넣는 척 해도 된다.

                User m = new User();
                //1행을 m에 담아서
                m.setUser_pw(rs.getString("user_pw"));


                return m;
            }
        };
    }

//    private RowMapper<Communityjdbc> communityRowMapper() {
//        // RowMapper를 반환해주는 메소드를 작성
//        return new RowMapper<Communityjdbc>() {       // new RowMapper<Member>() {} => {}안에 메소드 호출하여 바로 사용하면 굳이 따로
//            // class를 통해 implement를 해서 작업하지 않아도 됨
//            @Override
//            public Communityjdbc mapRow(ResultSet rs, int rowNum) throws SQLException {
//                //ResultSet(Select 결과물)을 Member 객체에 저장
//                // 여러 행이 반환되도, JdbcTemplate이 rowNum 만큼 돌려서 쓴다.
//                // 그래서 우리는 한 행만 넣는 척 해도 된다.
//
//                Communityjdbc m = new Communityjdbc();
//                //1행을 m에 담아서
//                m.setCommunityid(rs.getInt("id"));   // m객체의 id값 저장
//                m.setCommunitytag(rs.getString("tag"));
//                m.setCommunitykind(rs.getString("kind"));
//                m.setCommunitylocation(rs.getString("location"));
//                m.setCommunitycontent(rs.getString("content"));
//                m.setCommunityprice(rs.getString("price"));
//                m.setCommunitysubject(rs.getString("subject"));
//                m.setCommunitylink(rs.getString("link"));
//
//                return m;
//            }
//        };
//    }
}
