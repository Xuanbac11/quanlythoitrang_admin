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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.Adapter.KhuyenMaiAdapter;
import lam.fpoly.adminmanager.Dao.TbKhuyenMaiDao;
import lam.fpoly.adminmanager.Model.TbKhuyenMai;
import lam.fpoly.adminmanager.R;


public class QuanLyKhuyenMai extends Fragment {


    public QuanLyKhuyenMai() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static QuanLyKhuyenMai newInstance() {
        QuanLyKhuyenMai fragment = new QuanLyKhuyenMai();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_khuyen_mai, container, false);
    }
    TbKhuyenMaiDao dao;
    List<TbKhuyenMai> list;
    KhuyenMaiAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rsv_km);
        fab = view.findViewById(R.id.fab_km);

        dao = new TbKhuyenMaiDao();
        list = new ArrayList<>();
        adapter = new KhuyenMaiAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        list = dao.getAll();
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
    }
}