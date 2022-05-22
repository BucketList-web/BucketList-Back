package bucket.list.repository;

import bucket.list.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

//    Comment save(Comment comment);
//    List<Comment> oneCommentList(int comment_number);
}
