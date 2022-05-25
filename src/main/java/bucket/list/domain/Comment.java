package bucket.list.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//댓글 domain
@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentidx")
    private Integer commentidx;

    private String comment_text;

    private String comment_writer;

    private int comment_number;
}
