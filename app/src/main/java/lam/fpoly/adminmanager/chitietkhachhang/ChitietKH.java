package lam.fpoly.adminmanager.chitietkhachhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.Adapter.khachHangChitietAdapter;
import lam.fpoly.adminmanager.Dao.CTKhachHangDao;
import lam.fpoly.adminmanager.Model.TbChiTietKhachHang;
import lam.fpoly.adminmanager.R;

public class ChitietKH extends AppCompatActivity {
 List<TbChiTietKhachHang> list;
 khachHangChitietAdapter adapter;
 RecyclerView rcvctkh;
 CTKhachHangDao ctKhachHangDao;
 TextView tongdonhang,tbname,tbsdt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_kh);
        tongdonhang=findViewById(R.id.tongdonhang);
       tbname=findViewById(R.id.tenkh);
       tbsdt=findViewById(R.id.txtsdt);
        rcvctkh=findViewById(R.id.rcvchitiet);
        list=new ArrayList<>();
        adapter = new khachHangChitietAdapter();
        Intent i=getIntent();
        int id=i.getIntExtra("id",0);
        String name=i.getStringExtra("name");
        String sdt=i.getStringExtra("sdt");
        ctKhachHangDao=new CTKhachHangDao();
        list =ctKhachHangDao.getALlCT_idkh(id);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvctkh.setLayoutManager(linearLayoutManager);

        adapter.setData(list);
        rcvctkh.setAdapter(adapter);
        tbname.setText(name);
        tbsdt.setText(sdt+"");
        tongdonhang.setText(list.size()+"");


    }
}