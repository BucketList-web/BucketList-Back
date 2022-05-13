package spring.basic.demo.domain;

public class Place {
    private int placeid;
    private String placelocation;
    private String placename;
    private String placecontent;
    private String placetag;

    public int getPlaceid() {
        return placeid;
    }

    public void setPlaceid(int placeid) {
        this.placeid = placeid;
    }

    public String getPlacelocation() {
        return placelocation;
    }

    public void setPlacelocation(String placelocation) {
        this.placelocation = placelocation;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public String getPlacecontent() {
        return placecontent;
    }

    public void setPlacecontent(String placecontent) {
        this.placecontent = placecontent;
    }

    public String getPlacetag() {
        return placetag;
    }

    public void setPlacetag(String placetag) {
        this.placetag = placetag;
    }
}
