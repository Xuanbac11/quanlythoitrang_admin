package lam.fpoly.adminmanager.Model;

public class TbDonHang {
    int id_donHang;
    int id_khachHang;
    String trangThai;
    String ngayMua;
    int tongtien;

    public TbDonHang() {
    }

    public TbDonHang(int id_donHang, int id_khachHang, String trangThai, String ngayMua, int tongtien) {
        this.id_donHang = id_donHang;
        this.id_khachHang = id_khachHang;
        this.trangThai = trangThai;
        this.ngayMua = ngayMua;
        this.tongtien = tongtien;
    }

    public int getId_donHang() {
        return id_donHang;
    }

    public void setId_donHang(int id_donHang) {
        this.id_donHang = id_donHang;
    }

    public int getId_khachHang() {
        return id_khachHang;
    }

    public void setId_khachHang(int id_khachHang) {
        this.id_khachHang = id_khachHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
