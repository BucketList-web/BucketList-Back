package bucket.list.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

//    @NotBlank(message = "태그를 작성해주세요")
    private String participation_tag;

//    @NotBlank(message = "장소를 작성해주세요")
    private String participation_place;


    @Column(columnDefinition = "integer default 0")
//    @NotNull(message = "비용을 입력해주세요")
    private Integer participation_cost;

    @Column(columnDefinition = "integer default 0")
//    @NotNull(message = "인원수를 입력해주세요")
    private Integer participation_party;

//    @NotBlank(message = "게시글의 내용을 적어주세요")
    private String participation_text;

    private String participation_file;
    private LocalDate participation_date;
    private String participation_writer;
    private String participation_subject;
    private String participation_filepath;


    @Column(name = "participationcount", columnDefinition = "integer default 0")
    private Integer count;

    @PrePersist
    public void localAboutDate(){
        this.participation_date = LocalDate.now();
    }
}
