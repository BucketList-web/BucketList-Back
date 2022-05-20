package bucket.list.repository;

import bucket.list.domain.User;
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
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);       // sql 구문 생성 INSERT INTO
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("user_idx");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("user_id", user.getUser_id());
        parameters.put("user_name", user.getUser_name());
        parameters.put("user_pw", user.getUser_pw());
        parameters.put("user_pw2", user.getUser_pw2());
        parameters.put("user_email", user.getUser_email());
        parameters.put("user_phone", user.getUser_phone());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));

        user.setUser_idx(key.intValue());

        return user;
    }

    @Override
    public User exist(String user_id) {

        return jdbcTemplate.query("select user_phone from user where user_id = ?" , userRowMapper(), user_id).get(0);
    }




    private RowMapper<User> userRowMapper(){
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();

                user.setUser_idx(rs.getInt("user_idx"));
                user.setUser_id(rs.getString("user_id"));
                user.setUser_name(rs.getString("user_name"));
                user.setUser_pw(rs.getString("user_pw"));
                user.setUser_pw2(rs.getString("user_pw2"));
                user.setUser_email(rs.getString("user_email"));
                user.setUser_phone(rs.getString("user_phone"));

                return user;
            }
        };
    }

}
