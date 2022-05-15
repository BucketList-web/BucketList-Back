package bucket.list.repository;

import bucket.list.domain.Board;
import bucket.list.domain.Comment;

public interface CommentRepository {

    Comment save(Comment comment);
    Comment oneContentList(int number);
}
