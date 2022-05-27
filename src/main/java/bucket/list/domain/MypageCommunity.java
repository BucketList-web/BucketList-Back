package bucket.list.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MypageCommunity {

    private String mypagecommunitysubject;
    private String mypagecommunitytag;        // 태그
    private String mypagecommunitykind;       // 종류
    private String mypagecommunitylocation;   // 위치,장소
    private String mypagecommunitycontent;     // 내용
    private String mypagecommunityprice;      // 비용
    private Date mypagecommunitycreatedate = new Date(System.currentTimeMillis());
    private String mypagecommunityfilename;
    private String mypagecommunityfilepath;
    private String mypagecommunitylink;
    private String mypagecommunitywriter;
}
