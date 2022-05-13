package spring.basic.demo.controller;

public class BoardForm {
    private String storename;
    private String storelocation;
    private String storemenu;
    private int storeprice;
    private String storetag;

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getStorelocation() {
        return storelocation;
    }

    public void setStorelocation(String storelocation) {
        this.storelocation = storelocation;
    }

    public String getStoremenu() {
        return storemenu;
    }

    public void setStoremenu(String storemenu) {
        this.storemenu = storemenu;
    }

    public int getStoreprice() {
        return storeprice;
    }

    public void setStoreprice(int storeprice) {
        this.storeprice = storeprice;
    }

    public String getStoretag() {
        return storetag;
    }

    public void setStoretag(String storetag) {
        this.storetag = storetag;
    }
}
