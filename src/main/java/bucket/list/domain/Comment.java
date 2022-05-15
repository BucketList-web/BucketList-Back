package bucket.list.domain;

import lombok.Getter;
import lombok.Setter;

//댓글 domain
@Getter
@Setter
public class Comment {

    private int comment_number;
    private String comment_text;
    private int comment_idx;
}
