package lam.fpoly.adminmanager.Model;

public class TbChiTietDonHang {
    int id_donHang;
    int id_sanPham;
    int soLuong;

    public TbChiTietDonHang() {
    }

    public TbChiTietDonHang(int id_donHang, int id_khachHang, int id_sanPham, int soLuong) {
        this.id_donHang = id_donHang;
        this.id_sanPham = id_sanPham;
        this.soLuong = soLuong;
    }

    public int getId_donHang() {
        return id_donHang;
    }

    public void setId_donHang(int id_donHang) {
        this.id_donHang = id_donHang;
    }


    public int getId_sanPham() {
        return id_sanPham;
    }

    public void setId_sanPham(int id_sanPham) {
        this.id_sanPham = id_sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
