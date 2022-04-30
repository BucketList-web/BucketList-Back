package spring.basic.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.basic.demo.domain.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// h2 DB연결 부분
@Repository
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

        String sql = "INSERT INTO member(id, name) values(?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);

            m.setId(index++);               // setId 설정


            pstmt.setInt(1,m.getId());      // 첫번째  칸에 id 저장
            pstmt.setString(2,m.getName());     // 두번째 칸에 이름 저장

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        close(conn);
    }

    @Override
    public Member findById(int id) {
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
