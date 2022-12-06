package lam.fpoly.adminmanager.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.DbSqlServer;
import lam.fpoly.adminmanager.Model.TbChiTietKhachHang;
import lam.fpoly.adminmanager.Model.TbKhachHang;

public class CTKhachHangDao {
    Connection objConn;
    public CTKhachHangDao(){
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }
    public List<TbChiTietKhachHang> getALlCT_idkh(int id){
        List<TbChiTietKhachHang> listCat = new ArrayList<TbChiTietKhachHang>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "select dh.id_donHang as \"id\" ,ten_sanPham as \"donhangdamua\",ngayMua as\"ngaymua\",trangThai as \"trangthai\" , giaMua as\"mua\"\n" +
                        "from khachHang kh,sanPham sp,donHang dh,chiTietDonHang ct \n" +
                        "where kh.id_khachHang = dh.id_khachHang and ct.id_donHang=dh.id_donHang and sp.id_sanPham=ct.id_sanPham and kh.id_khachHang = "+id+"";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbChiTietKhachHang obj=new TbChiTietKhachHang();
                    obj.setId(resultSet.getString("id"));
                    obj.setTendonhang(resultSet.getString("donhangdamua"));
                    obj.setNgaymua(resultSet.getString("ngaymua"));
                    obj.setTrangthai(resultSet.getString("trangthai"));
                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }
        return listCat;
    }
}
