package lam.fpoly.adminmanager.Model;

public class TbChiTietKhachHang {
    String id;
    String tendonhang;
    String ngaymua;
    String trangthai;

    public TbChiTietKhachHang() {
    }

    public TbChiTietKhachHang(String id, String tendonhang, String ngaymua, String trangthai) {
        this.id = id;
        this.tendonhang = tendonhang;
        this.ngaymua = ngaymua;
        this.trangthai = trangthai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTendonhang() {
        return tendonhang;
    }

    public void setTendonhang(String tendonhang) {
        this.tendonhang = tendonhang;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
