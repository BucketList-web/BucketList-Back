package spring.basic.demo.controller;

import lombok.Data;

import java.util.Date;

@Data
public class BoardForm {

    private String communitytag;        // 태그
    private String communitykind;       // 종류
    private String communitylocation;   // 위치,장소
    private String communitycontent;     // 내용
    private String communityprice;      // 비용
    private Date communitydate = new Date(System.currentTimeMillis());

}
