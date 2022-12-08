package lam.fpoly.adminmanager.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.Dao.TbDonHangDao;
import lam.fpoly.adminmanager.Model.TbKhuyenMai;
import lam.fpoly.adminmanager.R;

public class ChiTietDonHang extends AppCompatActivity {
    TextView hdct_hoten, hdct_sdt, hdct_diachi, hdct_tensp_item, hdct_gia_item, hdct_soluong_item, hdct_tt_tensp, hdct_tt_tienhang, hdct_tt_ship, hdct_tt_tongtien;
    ImageView hdct_anh_item, img_edit;
    Spinner hdct_trangthai;
    Button btnLuu;
    int phi_ship = 34;
    String loaii;
    Context context;
    int indexSpn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        hdct_hoten = findViewById(R.id.hdct_hoten);
        context = this;
        hdct_sdt = findViewById(R.id.hdct_sdt);
        hdct_diachi = findViewById(R.id.hdct_diachi);
        hdct_trangthai = findViewById(R.id.hdct_trangthai);
        hdct_tensp_item = findViewById(R.id.hdct_tensp_item);
        hdct_gia_item = findViewById(R.id.hdct_gia_item);
        hdct_soluong_item = findViewById(R.id.hdct_soluong_item);
        hdct_tt_tensp = findViewById(R.id.hdct_tt_tensp);
        hdct_tt_tienhang = findViewById(R.id.hdct_tt_tienhang);
        hdct_tt_ship = findViewById(R.id.hdct_tt_ship);
        hdct_tt_tongtien = findViewById(R.id.hdct_tt_tongtien);
        hdct_anh_item = findViewById(R.id.hdct_anh_item);
        img_edit = findViewById(R.id.img_edit);
        btnLuu = findViewById(R.id.btnSave_hdct);


        Intent intent = getIntent();
        String ten = intent.getStringExtra("ten_kh");
        String sdt = intent.getStringExtra("sdt_kh");
        String diaChi = intent.getStringExtra("diachi_kh");
        String trangThai = intent.getStringExtra("trangThai");
        String tenSP = intent.getStringExtra("ten_sp");
        String anh = intent.getStringExtra("anh");
        int soLuong = intent.getIntExtra("soLuong", 0);
        int giaBan = intent.getIntExtra("giaBan",0);
        int id_donHang = intent.getIntExtra("id_donHang", 0);

        hdct_hoten.setText(ten);
        hdct_sdt.setText(sdt);
        hdct_diachi.setText(diaChi);
        hdct_tensp_item.setText(tenSP);
        hdct_gia_item.setText(giaBan+".000đ");
        hdct_soluong_item.setText(""+ soLuong);
        hdct_tt_tensp.setText(tenSP + " x " + soLuong);
        hdct_tt_tienhang.setText(giaBan * soLuong + ".000đ");
        hdct_tt_ship.setText(phi_ship + ".000đ");

        //int donGia = Integer.parseInt(giaBan)

        hdct_tt_tongtien.setText((giaBan * soLuong) + phi_ship +".000đ");
        Picasso.get().load(anh).fit().into(hdct_anh_item);

        List<String> listSpn = new ArrayList<>();

        listSpn.add("Chờ xác nhận");
        listSpn.add("Đang chuẩn bị hàng");
        listSpn.add("Đang giao hàng");
        listSpn.add("Giao hàng thành công");



        for(int i = 0; i < listSpn.size(); i++){
            if(trangThai.equals(listSpn.get(i))){
                indexSpn = i;
            }
        }

        ArrayAdapter spnAdapter = new ArrayAdapter(ChiTietDonHang.this, android.R.layout.simple_spinner_item, listSpn);
       spnAdapter.setDropDownViewResource(R.layout.space_text);
        hdct_trangthai.setAdapter(spnAdapter);
        hdct_trangthai.setSelection(indexSpn);
        hdct_trangthai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaii = listSpn.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TbDonHangDao tbDonHangDao = new TbDonHangDao();
                tbDonHangDao.upDateTT(id_donHang,loaii);
                Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}