package bucket.list.service;

import bucket.list.domain.Comment;
import bucket.list.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment) {
        Comment save = commentRepository.save(comment);
        return save;
    }
    public List<Comment> oneContentList(int comment_number) {
        List<Comment> comments = commentRepository.oneCommentList(comment_number);
        return comments;
    }
}
