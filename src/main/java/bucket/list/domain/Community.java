package bucket.list.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int communityid;            // 게시물 번호 (기본키값)

    private String communitysubject;
    private String communitytag;        // 태그
    private String communitykind;       // 종류
    private String communitylocation;   // 위치,장소
    private String communitycontent;     // 내용
    private String communityprice;      // 비용
    private Date communitycreatedate = new Date(System.currentTimeMillis());
    private String communityfilename;
    private String communityfilepath;
    private String communitylink;
    private String communitywriter;

}
