package tdc.edu.vn.ticket;

public class User {
    public String taiKhoan, matKhau, email, sdt;

    public User(String taiKhoan, String matKhau, String email, String sdt) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.email = email;
        this.sdt = sdt;
    }

    public User() {
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
