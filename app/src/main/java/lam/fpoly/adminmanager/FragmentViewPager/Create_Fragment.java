package lam.fpoly.adminmanager.FragmentViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.Adapter.MyAdapter_GirdView;
import lam.fpoly.adminmanager.Dao.TbDanhMucDao;
import lam.fpoly.adminmanager.Dao.TbSanPhamDao;
import lam.fpoly.adminmanager.Model.TbSanPham;
import lam.fpoly.adminmanager.R;

public class Create_Fragment extends Fragment {
    public static GridView idGridView;
    public static TbSanPhamDao sanPhamDao;
    public static MyAdapter_GirdView myAdapterGirdView;
    public static List<TbSanPham> list;
    public static TbDanhMucDao danhMucDao;
    public static int ID_DM = 1;
    private View view;
    public static Context context;
    private TextView soLuongSP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idGridView = view.findViewById(R.id.idGridView);
        soLuongSP = view.findViewById(R.id.soLuongSP);
        context = view.getContext();
        sanPhamDao = new TbSanPhamDao();
        list = new ArrayList<>();

    }

    @Override
    public void onResume() {
        super.onResume();
        list = sanPhamDao.getSpDanhMuc(ID_DM);
        myAdapterGirdView = new MyAdapter_GirdView(context,list);
        idGridView.setAdapter(myAdapterGirdView);
        soLuongSP.setText(list.size()+" sản phẩm");
    }
    public static void setGridView(List<TbSanPham> list1,GridView gridView){
        list1 = sanPhamDao.getSpDanhMuc(ID_DM);
        myAdapterGirdView = new MyAdapter_GirdView(context,list1);
        gridView.setAdapter(myAdapterGirdView);
    }
}