package bucket.list.repository;

import bucket.list.domain.About;
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
//공지사항 리포지토리
public class AboutRepositoryImpl implements AboutRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AboutRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    //공지사항 글 저장
    public About Save(About about) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);       // sql 구문 생성 INSERT INTO
        jdbcInsert.withTableName("about").usingGeneratedKeyColumns("about_number");

        Map<String, Object> parameters = new HashMap<>();

        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nowTime = now.format(formatter);

        about.setAbout_date(nowTime);


        parameters.put("about_subject", about.getAbout_subject());
        parameters.put("about_text", about.getAbout_text());
        parameters.put("about_writer", about.getAbout_writer());
        parameters.put("about_date", about.getAbout_date());
        parameters.put("about_file", about.getAbout_file());


        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));

        about.setAbout_number(key.intValue());

        return about;
    }

    @Override
    public List<About> allContentList() {
        return jdbcTemplate.query("select *  from about order by about_number desc", aboutRowMapper());
    }

    @Override
    public About oneContentList(int about_number) {
        return jdbcTemplate.query("select * from about where about_number =?", aboutRowMapper(), about_number).get(0);
    }


    private RowMapper<About> aboutRowMapper(){
        return new RowMapper<About>() {
            @Override
            public About mapRow(ResultSet rs, int rowNum) throws SQLException {
               About about = new About();

               about.setAbout_number(rs.getInt("about_number"));
               about.setAbout_subject(rs.getString("about_subject"));
               about.setAbout_text(rs.getString("about_text"));
               about.setAbout_writer(rs.getString("about_writer"));
               about.setAbout_date(rs.getString("about_date"));
               about.setAbout_file(rs.getString("about_file"));

               return about;
            }
        };
    }
}
