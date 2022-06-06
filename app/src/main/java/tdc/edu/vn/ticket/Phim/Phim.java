package tdc.edu.vn.ticket.Phim;

public class Phim {
    public int id;
    public String str_img;
    public String tenPhim;
    public ThongTinPhim thongTinPhim;

    public Phim() {
    }

    public Phim(int id, String str_img, String tenPhim, String moTaNgan, String theLoai, String thoiLuong, String ngonNgu) {
        this.id = id;
        this.str_img = str_img;
        this.tenPhim = tenPhim;
        this.thongTinPhim = new ThongTinPhim(moTaNgan, theLoai, thoiLuong, ngonNgu);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStr_img() {
        return str_img;
    }

    public void setStr_img(String str_img) {
        this.str_img = str_img;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public ThongTinPhim getThongTinPhim() {
        return thongTinPhim;
    }

    public void setThongTinPhim(ThongTinPhim thongTinPhim) {
        this.thongTinPhim = thongTinPhim;
    }
}
