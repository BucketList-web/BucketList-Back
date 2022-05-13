package spring.basic.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import spring.basic.demo.domain.Lodging;
import spring.basic.demo.domain.Place;
import spring.basic.demo.domain.Store;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// h2 DB연결 부분
@Repository     //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class JdbcTemplateRepository implements BoardRepositoryInterface {

    public int index = 0;
    public int index2 = 0;
    public int index3 = 0;
    // ------------ 맛집 부분 -----------

    // JdbcTemplate 사용하여 Repository 작성
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcTemplateRepository(DataSource dataSource){     // spring bean으로 가지고 있던 datasource를
        jdbcTemplate = new JdbcTemplate(dataSource);                //
    }

    @Override
    public void savestore(Store m) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);       // sql 구문 생성 INSERT INTO
        jdbcInsert.withTableName("store").usingGeneratedKeyColumns("id");      // ps <- SQL, id 값 자동 생성 요청

        Map<String, Object> parameters = new HashMap<>();
        // string => 열의 이름
        // object => 그 자리에 들어갈 값 (INTEGER,STRING을 사용하지 않은 이유는 OBJECT사용시 모든 형식사용 가능하기 때문)
        parameters.put("name", m.getStorename());
        parameters.put("location", m.getStorelocation());
        parameters.put("menu", m.getStoremenu());
        parameters.put("price", m.getStoreprice());
        parameters.put("tag", m.getStoretag());



        jdbcInsert.execute(parameters);
        //jdbcInsert.executeAndReturnKey(new MapSqlParameterSource((parameters)));   // ID(기본키)를 자동으로 생성하기 위한 구문
    }

    @Override
    public Store findstoreById(int id) {
        //스트림을 사용하지 않을 경우
        return jdbcTemplate.query("Select *from store WHERE id=?",
                storeRowMapper(),id).get(0);



        /* 스트림을 사용할 경우
        List<Member> result = jdbcTemplate.query("Select *from member WHERE id=?",
                memberRowMapper(),id);

        if(result.stream().findFirst().isPresent())     // ispresent() => 데이터가 있는지 유뮤 확인(결과 스트림의 첫번째에 데이터가 있다면)
            return result.stream().findFirst().get();
        */
    }

    @Override
    public List<Store> findstoreAll() {
        return jdbcTemplate.query("SELECT *FROM store",storeRowMapper());
    }

    // select 했을때 사용할 결과 행들
    private RowMapper<Store> storeRowMapper(){
        // RowMapper를 반환해주는 메소드를 작성
        return  new RowMapper<Store>() {       // new RowMapper<Member>() {} => {}안에 메소드 호출하여 바로 사용하면 굳이 따로
                                               // class를 통해 implement를 해서 작업하지 않아도 됨
            @Override
            public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
                //ResultSet(Select 결과물)을 Member 객체에 저장
                // 여러 행이 반환되도, JdbcTemplate이 rowNum 만큼 돌려서 쓴다.
                // 그래서 우리는 한 행만 넣는 척 해도 된다.

                Store m = new Store();
                //1행을 m에 담아서
                m.setStoreid(rs.getInt("id"));   // m객체의 id값 저장
                m.setStorename(rs.getString("name"));    // m객체의 name값 저장
                m.setStorelocation(rs.getString("location"));
                m.setStoremenu(rs.getString("menu"));
                m.setStoreprice(rs.getInt("price"));
                m.setStoretag(rs.getString("tag"));

                return m;
            }
        };
    }

    // ------------ 숙소 부분 -----------

//    @Override
//    public void savelodging(Lodging m) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);       // sql 구문 생성 INSERT INTO
//        jdbcInsert.withTableName("lodging").usingGeneratedKeyColumns("id");      // ps <- SQL, id 값 자동 생성 요청
//
//        Map<String, Object> parameters = new HashMap<>();
//        // string => 열의 이름
//        // object => 그 자리에 들어갈 값 (INTEGER,STRING을 사용하지 않은 이유는 OBJECT사용시 모든 형식사용 가능하기 때문)
//        parameters.put("name", m.getLodgingname());
//        parameters.put("location", m.getLodginglocation());
//        parameters.put("content", m.getLodgingcontent());
//        parameters.put("price", m.getLodgingprice());
//        parameters.put("tag", m.getLodgingtag());
//
//        jdbcInsert.execute(parameters);
//
//        //jdbcInsert.executeAndReturnKey(new MapSqlParameterSource((parameters)));   // ID(기본키)를 자동으로 생성하기 위한 구문
//    }
//
//    @Override
//    public Lodging findlodgingById(int id) {
//        //스트림을 사용하지 않을 경우
//        return jdbcTemplate.query("Select *from lodging WHERE id=?",
//                lodgingRowMapper(),id).get(0);
//
//        /* 스트림을 사용할 경우
//        List<Member> result = jdbcTemplate.query("Select *from member WHERE id=?",
//                memberRowMapper(),id);
//
//        if(result.stream().findFirst().isPresent())     // ispresent() => 데이터가 있는지 유뮤 확인(결과 스트림의 첫번째에 데이터가 있다면)
//            return result.stream().findFirst().get();
//        */
//    }
//
//    @Override
//    public List<Lodging> findlodgingAll() {
//        return jdbcTemplate.query("SELECT *FROM lodging",lodgingRowMapper());
//    }
//
//    // select 했을때 사용할 결과 행들
//    private RowMapper<Lodging> lodgingRowMapper(){
//        // RowMapper를 반환해주는 메소드를 작성
//        return  new RowMapper<Lodging>() {       // new RowMapper<Member>() {} => {}안에 메소드 호출하여 바로 사용하면 굳이 따로
//            // class를 통해 implement를 해서 작업하지 않아도 됨
//            @Override
//            public Lodging mapRow(ResultSet rs, int rowNum) throws SQLException {
//                //ResultSet(Select 결과물)을 Member 객체에 저장
//                // 여러 행이 반환되도, JdbcTemplate이 rowNum 만큼 돌려서 쓴다.
//                // 그래서 우리는 한 행만 넣는 척 해도 된다.
//
//                Lodging m = new Lodging();
//                //1행을 m에 담아서
//                m.setLodgingid(rs.getInt("id"));   // m객체의 id값 저장
//                m.setLodgingname(rs.getString("name"));    // m객체의 name값 저장
//                m.setLodginglocation(rs.getString("location"));
//                m.setLodgingcontent(rs.getString("content"));
//                m.setLodgingprice(rs.getInt("price"));
//                m.setLodgingtag(rs.getString("tag"));
//
//                return m;
//            }
//        };
//    }
//
//
//
//    // ------------ 장소 부분 -----------
//
//    @Override
//    public void saveplace(Place m) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);       // sql 구문 생성 INSERT INTO
//        jdbcInsert.withTableName("place").usingGeneratedKeyColumns("id");      // ps <- SQL, id 값 자동 생성 요청
//
//        Map<String, Object> parameters = new HashMap<>();
//        // string => 열의 이름
//        // object => 그 자리에 들어갈 값 (INTEGER,STRING을 사용하지 않은 이유는 OBJECT사용시 모든 형식사용 가능하기 때문)
//        parameters.put("name", m.getPlacename());
//        parameters.put("location", m.getPlacelocation());
//        parameters.put("content", m.getPlacecontent());
//        parameters.put("tag", m.getPlacetag());
//
//        jdbcInsert.execute(parameters);
//        //jdbcInsert.executeAndReturnKey(new MapSqlParameterSource((parameters)));   // ID(기본키)를 자동으로 생성하기 위한 구문
//    }
//
//    @Override
//    public Place findplaceById(int id) {
//        //스트림을 사용하지 않을 경우
//        return jdbcTemplate.query("Select *from place WHERE id=?",
//                placeRowMapper(),id).get(0);
//
//        /* 스트림을 사용할 경우
//        List<Member> result = jdbcTemplate.query("Select *from member WHERE id=?",
//                memberRowMapper(),id);
//
//        if(result.stream().findFirst().isPresent())     // ispresent() => 데이터가 있는지 유뮤 확인(결과 스트림의 첫번째에 데이터가 있다면)
//            return result.stream().findFirst().get();
//        */
//    }
//
//    @Override
//    public List<Place> findplaceAll() {
//        return jdbcTemplate.query("SELECT *FROM place",placeRowMapper());
//    }
//
//    // select 했을때 사용할 결과 행들
//    private RowMapper<Place> placeRowMapper(){
//        // RowMapper를 반환해주는 메소드를 작성
//        return  new RowMapper<Place>() {       // new RowMapper<Member>() {} => {}안에 메소드 호출하여 바로 사용하면 굳이 따로
//            // class를 통해 implement를 해서 작업하지 않아도 됨
//            @Override
//            public Place mapRow(ResultSet rs, int rowNum) throws SQLException {
//                //ResultSet(Select 결과물)을 Member 객체에 저장
//                // 여러 행이 반환되도, JdbcTemplate이 rowNum 만큼 돌려서 쓴다.
//                // 그래서 우리는 한 행만 넣는 척 해도 된다.
//
//                Place m = new Place();
//                //1행을 m에 담아서
//                m.setPlaceid(rs.getInt("id"));   // m객체의 id값 저장
//                m.setPlacename(rs.getString("name"));    // m객체의 name값 저장
//                m.setPlacelocation(rs.getString("location"));
//                m.setPlacecontent(rs.getString("content"));
//                m.setPlacetag(rs.getString("tag"));
//
//                return m;
//            }
//        };
//    }


//    private void close(Connection conn){            // conn.close try-catch문 함수화
//        try {
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
