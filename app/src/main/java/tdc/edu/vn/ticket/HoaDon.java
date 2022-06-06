package tdc.edu.vn.ticket;

public class HoaDon {
    private String nameMV, time, seet, date;

    public HoaDon() {
    }

    public HoaDon(String nameMV, String time, String seet, String date) {
        this.nameMV = nameMV;
        this.time = time;
        this.seet = seet;
        this.date = date;
    }

    public String getNameMV() {
        return nameMV;
    }

    public void setNameMV(String nameMV) {
        this.nameMV = nameMV;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeet() {
        return seet;
    }

    public void setSeet(String seet) {
        this.seet = seet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
