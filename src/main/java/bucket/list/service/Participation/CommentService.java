package bucket.list.service.Participation;

import bucket.list.domain.Comment;
import bucket.list.repository.Participation.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Transactional
    public void save(Comment comment){
        commentRepository.save(comment);
    }
    public List<Comment> allContentList(int comment_number) {
        List<Comment> commentidx = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "commentidx"));
        return commentidx;

    }
}
