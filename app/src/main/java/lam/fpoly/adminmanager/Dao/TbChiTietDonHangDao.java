package lam.fpoly.adminmanager.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.DbSqlServer;
import lam.fpoly.adminmanager.Model.TbChiTietDonHang;
import lam.fpoly.adminmanager.Model.TbDonHang;

public class TbChiTietDonHangDao {
    Connection objConn;

    public TbChiTietDonHangDao() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }

    public List<TbChiTietDonHang> getAll() {
        List<TbChiTietDonHang> list = new ArrayList<>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM chiTietDonHang";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbChiTietDonHang obj = new TbChiTietDonHang();
                    obj.setId_donHang(resultSet.getInt("id_donHang"));
                    obj.setId_sanPham(resultSet.getInt("id_sanPham"));
                    obj.setSoLuong(resultSet.getInt("soLuong"));
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return list;
    }
    public TbChiTietDonHang getHdctID(int id) {
        List<TbChiTietDonHang> list = new ArrayList<>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM chiTietDonHang where id_donHang = "+id+"";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbChiTietDonHang obj = new TbChiTietDonHang();
                    obj.setId_donHang(resultSet.getInt("id_donHang"));
                    obj.setId_sanPham(resultSet.getInt("id_sanPham"));
                    obj.setSoLuong(resultSet.getInt("soLuong"));
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "lỗi: "+e);
        }

        return list.get(0);
    }

}
