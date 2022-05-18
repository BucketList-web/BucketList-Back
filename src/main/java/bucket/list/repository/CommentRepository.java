package bucket.list.repository;

import bucket.list.domain.Board;
import bucket.list.domain.Comment;

import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);
    List<Comment> oneContentList(int number);
}
