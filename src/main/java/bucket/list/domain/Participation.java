package bucket.list.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "participation")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participationidx")
    private Integer participationidx;
    private String participation_option;
    private String participation_tag;
    private String participation_place;
    private int participation_cost;
    private int participation_party;
    private String participation_text;
    private String participation_file;
    private LocalDate participation_date;
    private String participation_writer;
    private String participation_subject;


    @Column(name = "participationcount", columnDefinition = "integer default 0")
    private Integer count;

    @PrePersist
    public void localAboutDate(){
        this.participation_date = LocalDate.now();
    }
}
