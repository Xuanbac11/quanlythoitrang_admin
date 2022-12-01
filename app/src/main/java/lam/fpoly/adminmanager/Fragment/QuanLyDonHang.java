package lam.fpoly.adminmanager.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.Adapter.DonHangAdapter;
import lam.fpoly.adminmanager.Adapter.KhuyenMaiAdapter;
import lam.fpoly.adminmanager.Dao.TbDonHangDao;
import lam.fpoly.adminmanager.Dao.TbKhuyenMaiDao;
import lam.fpoly.adminmanager.Model.TbDonHang;
import lam.fpoly.adminmanager.R;

public class QuanLyDonHang extends Fragment {

    public QuanLyDonHang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_quan_ly_don_hang, container, false);

    }
    TbDonHangDao tbDonHangDao;
    List<TbDonHang> list;
    DonHangAdapter donHangAdapter;
    RecyclerView recyclerView;
    TbDonHang tbDonHang;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rsv_qldh);
        tbDonHangDao = new TbDonHangDao();
        list = new ArrayList<>();
        donHangAdapter = new DonHangAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        list = tbDonHangDao.getAll();
        donHangAdapter.setData(list);
        recyclerView.setAdapter(donHangAdapter);
    }
}