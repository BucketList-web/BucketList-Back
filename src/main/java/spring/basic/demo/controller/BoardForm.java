package spring.basic.demo.controller;


public class BoardForm {

    private String communitytag;        // 태그
    private String communitykind;       // 종류
    private String communitylocation;   // 위치,장소
    private String communitycontent;     // 내용
    private String communityprice;      // 비용

    public String getCommunitytag() {
        return communitytag;
    }

    public void setCommunitytag(String communitytag) {
        this.communitytag = communitytag;
    }

    public String getCommunitykind() {
        return communitykind;
    }

    public void setCommunitykind(String communitykind) {
        this.communitykind = communitykind;
    }

    public String getCommunitylocation() {
        return communitylocation;
    }

    public void setCommunitylocation(String communitylocation) {
        this.communitylocation = communitylocation;
    }

    public String getCommunitycontent() {
        return communitycontent;
    }

    public void setCommunitycontent(String communitycontent) {
        this.communitycontent = communitycontent;
    }

    public String getCommunityprice() {
        return communityprice;
    }

    public void setCommunityprice(String communityprice) {
        this.communityprice = communityprice;
    }
}
