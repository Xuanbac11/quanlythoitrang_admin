package lam.fpoly.adminmanager.Model;

public class TbSanPham {
    int id_sanPham;
    String ten_sanPham;
    String anh;
    int giaNhap;
    int giaBan;
    int tonKho;
    int id_danhMuc;

    public TbSanPham(String ten_sanPham, String anh, int giaNhap, int giaBan, int tonKho, int id_danhMuc) {
        this.ten_sanPham = ten_sanPham;
        this.anh = anh;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.tonKho = tonKho;
        this.id_danhMuc = id_danhMuc;
    }

    public int getId_sanPham() {
        return id_sanPham;
    }

    public void setId_sanPham(int id_sanPham) {
        this.id_sanPham = id_sanPham;
    }

    public String getTen_sanPham() {
        return ten_sanPham;
    }

    public void setTen_sanPham(String ten_sanPham) {
        this.ten_sanPham = ten_sanPham;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public int getId_danhMuc() {
        return id_danhMuc;
    }

    public void setId_danhMuc(int id_danhMuc) {
        this.id_danhMuc = id_danhMuc;
    }
}

