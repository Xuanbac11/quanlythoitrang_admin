package lam.fpoly.adminmanager.FragmentViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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
    public static int id;
    private View view;
    public static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_, container, false);
        idGridView = view.findViewById(R.id.idGridView);
        context = view.getContext();
        return view;
    }

//    public void ReceiveData(String data){
//        danhMucDao = new TbDanhMucDao();
//        sanPhamDao = new TbSanPhamDao();
//        id = danhMucDao.getID(data);
//        Log.i("TAG", "ReceiveData: "+data);
//        list = new ArrayList<>();
//        list = sanPhamDao.getSpDanhMuc(id);
//        myAdapterGirdView = new MyAdapter_GirdView(getContext(),list);
//        idGridView.setAdapter(myAdapterGirdView);
//    }

    public static void setGirdView(String data){
        danhMucDao = new TbDanhMucDao();
        sanPhamDao = new TbSanPhamDao();
        id = danhMucDao.getID(data);
        list = new ArrayList<>();
        list = sanPhamDao.getSpDanhMuc(id);
        myAdapterGirdView = new MyAdapter_GirdView(context,list);
        idGridView.setAdapter(myAdapterGirdView);
    }

}