package lam.fpoly.adminmanager.Model;

public class TbKhuyenMai {
    int id;
    String loai;
    int sale;
    String hsd;
    int maxSale;

    public TbKhuyenMai() {
    }

    public TbKhuyenMai(String loai, int sale, String hsd, int maxSale) {
        this.loai = loai;
        this.sale = sale;
        this.hsd = hsd;
        this.maxSale = maxSale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getHsd() {
        return hsd;
    }

    public void setHsd(String hsd) {
        this.hsd = hsd;
    }

    public int getMaxSale() {
        return maxSale;
    }

    public void setMaxSale(int maxSale) {
        this.maxSale = maxSale;
    }
}
