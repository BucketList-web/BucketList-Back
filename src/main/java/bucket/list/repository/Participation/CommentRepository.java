package bucket.list.repository.Participation;

import bucket.list.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

//    Comment save(Comment comment);
//    List<Comment> oneCommentList(int comment_number);
}
