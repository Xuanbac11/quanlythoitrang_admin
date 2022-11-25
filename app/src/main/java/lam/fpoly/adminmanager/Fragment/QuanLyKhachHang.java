package lam.fpoly.adminmanager.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.Adapter.KhachHangAdapter;
import lam.fpoly.adminmanager.Dao.TbKhachHangDao;
import lam.fpoly.adminmanager.Model.TbKhachHang;
import lam.fpoly.adminmanager.R;

public class QuanLyKhachHang extends Fragment {
    RecyclerView rcvKhachHang;
    TbKhachHangDao tbkhachhangdao;
    List<TbKhachHang> list;
    KhachHangAdapter adapter;


    public QuanLyKhachHang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_khach_hang, container, false);
        rcvKhachHang = view.findViewById(R.id.rcv_khachhang);
        adapter = new KhachHangAdapter();
        list = new ArrayList<>();
        tbkhachhangdao = new TbKhachHangDao();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcvKhachHang.setLayoutManager(manager);
        list = tbkhachhangdao.getAll();
        adapter.setData(list);
        rcvKhachHang.setAdapter(adapter);
        return view;
    }
}