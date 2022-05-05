package spring.basic.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import spring.basic.demo.domain.Store;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// h2 DB연결 부분
// @Repository     spring bean 사용하여 따로 설정했으므로 삭제해야함
public class JdbcStoreRepository implements StoreRepositoryInterface {

    private DataSource dataSource;
    public int index = 0;

    @Autowired
    public JdbcStoreRepository(DataSource dataSource){     // spring에서 자체적으로 DB에 저장한 값 연결하는 부분
        this.dataSource = dataSource;
    }

    @Override
    public void savedata(Store m) {
        // 객체에 id값 주고
        // DB에 저장

        String sql = "INSERT INTO store(id, name,location,menu,price,tag) values(?,?,?,?,?,?)";      // id,name열에 값을 넣겠다 ,values(?,?) = 값을 넣겠다


        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);


             m.setId(index++);               // setId 설정       현재 게시글 순서를 맞추기 위해 기본키 값을 설정하는 곳으로 로그인 완성되면
                                            //                    아이디를 키값으로 받아올거기 때문에 아이디로 변경해주면 됨.

            pstmt.setInt(1,m.getId());      // 첫번째  칸에 id 저장
            pstmt.setString(2,m.getName());     // 두번째 칸에 이름 저장
            pstmt.setString(3,m.getLocation());
            pstmt.setString(4,m.getMenu());
            pstmt.setInt(5,m.getPrice());
            pstmt.setString(6,m.getTag());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        close(conn);
    }

    @Override
    public Store findById(int id) {

        String sql = "SELECT * FROM store WHERE id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;        // 디비에서 가져올 값을 저장할 객체

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,id);      // 첫번째  칸에 id 저장

            rs = pstmt.executeQuery();  // select 문 사용시에는 executeQuery 사용

            if(rs.next()){          // rs객체에 값이 없다면
                Store m = new Store();            // member클래스 불러옴
                m.setId(rs.getInt("id"));   // m객체의 id값 저장
                m.setName(rs.getString("name"));    // m객체의 name값 저장
                m.setLocation(rs.getString("location"));
                m.setMenu(rs.getString("menu"));
                m.setPrice(rs.getInt("price"));
                m.setTag(rs.getString("tag"));

                return m;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);            // finally는 try-catch문을 다 실행하고 떠나기 전에 무조건 실행하는  장소
        }

        return null;
    }

    @Override
    public List<Store> findAll() {
        List<Store> dataList = new ArrayList<>();    // select *from store 결과 담을 리스트

        String sql = "SELECT * FROM store";

        Connection conn = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;        // 디비에서 가져올 값을 저장할 객체

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();  // select 문 사용시에는 executeQuery 사용

            while(rs.next()){          // rs객체에 결과값이 있다면(모든 데이터를 출력하기 위해 while 사용)
                Store m = new Store();            // member클래스 불러옴
                m.setId(rs.getInt("id"));   // m객체의 id값 저장
                m.setName(rs.getString("name"));    // m객체의 name값 저장
                m.setLocation(rs.getString("location"));
                m.setMenu(rs.getString("menu"));
                m.setPrice(rs.getInt("price"));
                m.setTag(rs.getString("tag"));

                dataList.add(m);           // dataList에 추가
            }

            return dataList;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);            // finally는 try-catch문을 다 실행하고 떠나기 전에 무조건 실행하는  장소
        }

        return null;
    }


    private void close(Connection conn){            // conn.close try-catch문 함수화
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
