package spring.basic.demo.domain;

public class Store {

    private int storeid;
    private String storename;
    private String storelocation;
    private String storemenu;
    private int storeprice;
    private String storetag;

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

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
