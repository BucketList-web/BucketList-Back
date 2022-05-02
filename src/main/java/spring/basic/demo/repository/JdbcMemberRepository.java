package spring.basic.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.basic.demo.domain.Member;

import javax.sql.DataSource;
import java.sql.*;

// h2 DB연결 부분
// @Repository     spring bean 사용하여 따로 설정했으므로 삭제해야함
public class JdbcMemberRepository implements MemberRepositoryInterface{

    private DataSource dataSource;
    public int index = 0;

    @Autowired
    public JdbcMemberRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void saveMember(Member m) {
        // 객체에 id값 주고
        // DB에 저장

        //String sql = "INSERT INTO member(id, name) values(?,?)";      // id,name열에 값을 넣겠다 ,values(?,?) = 값을 넣겠다
        String sql = "INSERT INTO member(name) VALUES(?)";   // identity /id값은 sql에서 알아서 해줌

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            //pstmt = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);   // identity  id값을 sql에서 자동으로 해주기 위함

            // m.setId(index++);               // setId 설정

            //pstmt.setInt(1,m.getId());      // 첫번째  칸에 id 저장
            //pstmt.setString(2,m.getName());     // 두번째 칸에 이름 저장
            pstmt.setString(1,m.getName());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        close(conn);
    }

    @Override
    public Member findById(int id) {

        String sql = "SELECT * FROM member WHERE id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;        // 디비에서 가져올 값을 저장할 객체

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,id);      // 첫번째  칸에 id 저장

            rs = pstmt.executeQuery();  // select 문 사용시에는 executeQuery 사용

            if(rs.next()){          // rs객체에 값이 없다면
                Member m = new Member();            // member클래스 불러옴
                m.setId(rs.getInt("id"));   // m객체의 id값 저장
                m.setName(rs.getString("name"));    // m객체의 name값 저장

                return m;
            }

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
