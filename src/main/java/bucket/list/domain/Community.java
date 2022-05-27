package bucket.list.domain;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int communityid;            // 게시물 번호 (기본키값)

    private String communitysubject;
    @Column(name = "communitytag")
    private String communitytag;        // 태그
    private String communitykind;       // 종류
    private String communitylocation;   // 위치,장소
    private String communitycontent;     // 내용
    private String communityprice;      // 비용
    private LocalDate communitycreatedate;
    private String communityfile;
    private String communitylink;
    private String communitywriter;

    @PrePersist
    public void localAboutDate(){
        this.communitycreatedate = LocalDate.now();
    }


}
