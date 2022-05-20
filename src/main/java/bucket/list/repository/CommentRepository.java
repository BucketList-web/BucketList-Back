package bucket.list.repository;

import bucket.list.domain.Comment;

import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);
    List<Comment> oneCommentList(int comment_number);
}
