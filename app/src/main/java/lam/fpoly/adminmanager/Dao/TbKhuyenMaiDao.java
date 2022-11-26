package lam.fpoly.adminmanager.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.DbSqlServer;
import lam.fpoly.adminmanager.Model.TbKhuyenMai;
import lam.fpoly.adminmanager.Model.TbSanPham;

public class TbKhuyenMaiDao {
    Connection objConn;

    public TbKhuyenMaiDao() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }

    public List<TbKhuyenMai> getAll() {
        List<TbKhuyenMai> listCat = new ArrayList<TbKhuyenMai>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM khuyenMai ";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbKhuyenMai obj = new TbKhuyenMai();
                    obj.setId(resultSet.getInt("id_km"));
                    obj.setLoai(resultSet.getString("loaiKm"));
                    obj.setSale(resultSet.getInt("sale"));
                    obj.setHsd(resultSet.getString("hsd"));
                    obj.setMaxSale(resultSet.getInt("maxSale"));
                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return listCat;
    }
}
