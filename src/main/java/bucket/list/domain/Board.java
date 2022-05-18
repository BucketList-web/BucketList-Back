package bucket.list.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Board {

    private int number;
    private String text;
    private int cost;
    private int party;

}
