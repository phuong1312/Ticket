package tdc.edu.vn.ticket.Phim;

public class ThongTinPhim {
    public String moTaNgan;
    public String theLoai;
    public String thoiLuong;
    public String ngonNgu;

    public ThongTinPhim() {
    }

    public ThongTinPhim(String moTaNgan, String theLoai, String thoiLuong, String ngonNgu) {
        this.moTaNgan = moTaNgan;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.ngonNgu = ngonNgu;
    }

    public String getMoTaNgan() {
        return moTaNgan;
    }

    public void setMoTaNgan(String moTaNgan) {
        this.moTaNgan = moTaNgan;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }
}
