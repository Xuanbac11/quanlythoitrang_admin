package lam.fpoly.adminmanager.Dao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.DbSqlServer;
import lam.fpoly.adminmanager.Model.TbDonHang;
import lam.fpoly.adminmanager.Model.TbKhachHang;
import lam.fpoly.adminmanager.Model.TbSanPham;


public class TbSanPhamDao {
    Connection objConn;

    public TbSanPhamDao() {
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
    }

    public List<TbSanPham> getAll() {
        List<TbSanPham> listCat = new ArrayList<TbSanPham>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM SanPham ";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbSanPham obj = new TbSanPham();
                    obj.setId_sanPham(resultSet.getInt("id_sanpham"));
                    obj.setTen_sanPham(resultSet.getString("ten_sanpham"));
                    obj.setSrcAnh(resultSet.getString("anh"));
                    obj.setGiaBan(resultSet.getInt("giaban"));
                    obj.setGiaNhap(resultSet.getInt("gianhap"));
                    obj.setTonKho(resultSet.getInt("tonkho"));
                    obj.setId_danhmuc(resultSet.getInt("id_danhmuc"));
                    obj.setIn4(resultSet.getString("info"));
                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi");
        }

        return listCat;
    }

    public TbSanPham getSanPham(int id) {
        List<TbSanPham> listCat = new ArrayList<TbSanPham>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM SanPham where id_sanpham = '"+id+"'";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbSanPham obj = new TbSanPham();
                    obj.setId_sanPham(resultSet.getInt("id_sanpham"));
                    obj.setTen_sanPham(resultSet.getString("ten_sanpham"));
                    obj.setSrcAnh(resultSet.getString("anh"));
                    obj.setGiaBan(resultSet.getInt("giaban"));
                    obj.setGiaNhap(resultSet.getInt("gianhap"));
                    obj.setTonKho(resultSet.getInt("tonkho"));
                    obj.setId_danhmuc(resultSet.getInt("id_danhmuc"));
                    obj.setIn4(resultSet.getString("info"));
                    listCat.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getSanPham: lỗi");
        }

        return listCat.get(0);
    }

    public void insertRow (TbSanPham objCat){
        try {
            if (this.objConn != null) {
                String insertSQL = "INSERT INTO SanPham VALUES (N'" + objCat.getTen_sanPham() + "'," +
                        "'"+ objCat.getSrcAnh()+"'," +
                        "'"+objCat.getGiaNhap()+"'," +
                        "'"+objCat.getGiaBan()+"'," +
                        "'"+objCat.getTonKho()+"'," +
                        "'"+objCat.getId_danhmuc()+"'," +
                        "N'"+ objCat.getIn4()+"') ";
                String generatedColumns[] = { "ID" };
                Log.d("TAG", "insertRow: "+insertSQL);
                PreparedStatement stmtInsert = this.objConn.prepareStatement(insertSQL, generatedColumns);
                stmtInsert.execute();
                // lấy ra ID cột tự động tăng
                ResultSet rs = stmtInsert.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                }
            }
        } catch (Exception e) {
            Log.d("TAG", "insertRow: lỗi" + e);
        }
    }

    public List<TbSanPham> getSpDanhMuc(int idDanhMuc) {
        List<TbSanPham> list = new ArrayList<TbSanPham>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM SanPham WHERE id_danhmuc = '"+idDanhMuc+"'";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbSanPham obj = new TbSanPham();
                    obj.setId_sanPham(resultSet.getInt("id_sanpham"));
                    obj.setTen_sanPham(resultSet.getString("ten_sanpham"));
                    obj.setSrcAnh(resultSet.getString("anh"));
                    obj.setGiaBan(resultSet.getInt("giaban"));
                    obj.setGiaNhap(resultSet.getInt("gianhap"));
                    obj.setTonKho(resultSet.getInt("tonkho"));
                    obj.setId_danhmuc(resultSet.getInt("id_danhmuc"));
                    obj.setIn4(resultSet.getString("info"));
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getSpDanhMuc: lỗi");
        }

        return list;
    }

    public TbSanPham getSanPhamId(int id_SanPham) {
        List<TbSanPham> list = new ArrayList<TbSanPham>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT * FROM SanPham WHERE id_sanPham = "+id_SanPham+"";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    TbSanPham obj = new TbSanPham();
                    obj.setId_sanPham(resultSet.getInt("id_sanpham"));
                    obj.setTen_sanPham(resultSet.getString("ten_sanpham"));
                    obj.setSrcAnh(resultSet.getString("anh"));
                    obj.setGiaBan(resultSet.getInt("giaban"));
                    obj.setGiaNhap(resultSet.getInt("gianhap"));
                    obj.setTonKho(resultSet.getInt("tonkho"));
                    obj.setId_danhmuc(resultSet.getInt("id_danhmuc"));
                    obj.setIn4(resultSet.getString("info"));
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            Log.i("TAG", "getSpDanhMuc: lỗi");
        }

        return list.get(0);
    }

    public void upDateSp(TbSanPham obj) {
        List<TbDonHang> list = new ArrayList<>();
        try {
            if (this.objConn != null) {
                String sqlQuery = "UPDATE sanPham SET ten_sanPham = N'"+obj.getTen_sanPham()+"'," +
                                    " anh = '"+obj.getSrcAnh()+"'," +
                                    " giaNhap = "+obj.getGiaNhap()+"," +
                                    " giaBan = "+obj.getGiaBan()+"," +
                                    " tonKho = "+obj.getTonKho()+"," +
                                    " id_danhMuc = "+obj.getId_danhmuc()+"," +
                                    " info = N'"+obj.getIn4()+"' WHERE ten_sanPham = N'"+obj.getTen_sanPham()+"'";
                Log.d("TAG", "upDateSp: "+sqlQuery);
                PreparedStatement stmt = this.objConn.prepareStatement(sqlQuery);
                stmt.execute(); // thực thi câu lệnh SQL
            }
        } catch (Exception e) {
            Log.i("TAG", "getAll: lỗi +" +e);
        }

    }


    public int count() {
        int sl = 0;
        try {
            if (this.objConn != null) {
                String sqlQuery = "SELECT count(id_sanpham) as 'so luong' FROM SanPham ";
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                while (resultSet.next()) {
                    sl = resultSet.getInt("so luong");
                }
            }
        } catch (Exception e) {
        }
        return sl;
    }


}
