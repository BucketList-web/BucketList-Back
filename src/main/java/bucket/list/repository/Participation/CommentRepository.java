package bucket.list.repository.Participation;

import bucket.list.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {


    //댓글리스트
    @Query(value = "select * from comment where comment_number=? order by commentidx desc", nativeQuery = true)
    List<Comment> allContentList(int comment_number);

    //댓글수정
    @Query(value = "update comment set comment_text=? where commentidx=?", nativeQuery = true)
    String commentEdit(String comment_text, int commentidx);


    //댓글작성자
    @Query(value = "select comment_writer from comment where commentidx=?", nativeQuery = true)
    String selectIdSQL(int commentidx);

}
