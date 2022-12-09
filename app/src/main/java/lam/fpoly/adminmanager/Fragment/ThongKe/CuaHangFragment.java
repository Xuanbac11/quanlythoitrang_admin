package lam.fpoly.adminmanager.Fragment.ThongKe;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lam.fpoly.adminmanager.Adapter.ThongKeAdapter;
import lam.fpoly.adminmanager.Dao.TbChiTietDonHangDao;
import lam.fpoly.adminmanager.Dao.TbDonHangDao;
import lam.fpoly.adminmanager.Dao.TbSanPhamDao;
import lam.fpoly.adminmanager.Model.TbChiTietDonHang;
import lam.fpoly.adminmanager.Model.TbDonHang;
import lam.fpoly.adminmanager.R;


public class CuaHangFragment extends Fragment {
    RecyclerView rcvSanPham;
    TextView tvTongThu, tvLoiNhuan ,tvDateFrom ,tvDateTo;
    LinearLayout idLayoutSelectDate;
    TabLayout rcvTabLayOut;
    SimpleDateFormat dateFormat =new SimpleDateFormat("MM/dd/yyyy");
    Calendar cal = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    int nam,thang,ngay;
    ThongKeAdapter adapter;
    List<TbChiTietDonHang> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cua_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvSanPham = view.findViewById(R.id.rcvSanPham);
        tvTongThu = view.findViewById(R.id.tvTongThu);
        tvLoiNhuan = view.findViewById(R.id.tvLoiNhuan);
        rcvTabLayOut = view.findViewById(R.id.rcvTabLayOut);
        tvDateFrom = view.findViewById(R.id.tvDateFrom);
        tvDateTo = view.findViewById(R.id.tvDateTo);
        idLayoutSelectDate = view.findViewById(R.id.idLayoutSelectDate);

        rcvTab();

        tvLoiNhuan.setText(loiNhuan("01/01/1000","01/01/3000")+".000 đ");
        tvTongThu.setText(doanhThu("01/01/1000","01/01/3000")+".000 đ");

        TbChiTietDonHangDao tbChiTietDonHangDao = new TbChiTietDonHangDao();

        list = tbChiTietDonHangDao.getAll();

        adapter = new ThongKeAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rcvSanPham.setLayoutManager(manager);
        rcvSanPham.setAdapter(adapter);

        adapter.setData(list);

    }


    private void rcvTab(){
        rcvTabLayOut.addTab(rcvTabLayOut.newTab().setText("Tất cả"));
        rcvTabLayOut.addTab(rcvTabLayOut.newTab().setText("Hôm nay"));
        rcvTabLayOut.addTab(rcvTabLayOut.newTab().setText("Hôm Qua"));
        rcvTabLayOut.addTab(rcvTabLayOut.newTab().setText("Tháng này"));
        rcvTabLayOut.addTab(rcvTabLayOut.newTab().setText("Tháng trước"));
        rcvTabLayOut.addTab(rcvTabLayOut.newTab().setText("Thời gian khác"));
        rcvTabLayOut.setTabMode(TabLayout.MODE_SCROLLABLE);
        rcvTabLayOut.setTabGravity(TabLayout.GRAVITY_CENTER);
        rcvTabLayOut.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (rcvTabLayOut.getSelectedTabPosition()){
                    case 0:
                        tvLoiNhuan.setText(loiNhuan("01/01/1000","01/01/3000")+".000 đ");
                        tvTongThu.setText(doanhThu("01/01/1000","01/01/3000")+".000 đ");
                        break;
                    case 1:
                        tvLoiNhuan.setText(loiNhuan(dateFormat.format(new Date()),dateFormat.format(new Date()))+".000 đ");
                        tvTongThu.setText(doanhThu(dateFormat.format(new Date()),dateFormat.format(new Date()))+".000 đ");
                        break;
                    case 2:
                        cal.add(Calendar.DAY_OF_MONTH, - 1);
                        tvLoiNhuan.setText(loiNhuan(dateFormat.format(cal.getTime()),dateFormat.format(cal.getTime()))+".000 đ");
                        tvTongThu.setText(doanhThu(dateFormat.
                                format(cal.getTime()),dateFormat.format(cal.getTime()))+".000 đ");
                        break;
                    case 3:
                        cal.add(Calendar.MONTH,0);
                        cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(dateFormat.
                                format(new Date()).substring(0,2)) + 1);
                        cal2.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                        tvLoiNhuan.setText(loiNhuan(dateFormat.
                                format(cal.getTime()),dateFormat.format(cal2.getTime()))+".000 đ");
                        tvTongThu.setText(doanhThu(dateFormat.
                                format(cal.getTime()),dateFormat.format(cal2.getTime()))+".000 đ");
                        break;
                    case 4:
                        cal.add(Calendar.MONTH,-1);
                        cal.add(Calendar.DAY_OF_MONTH, - Integer.parseInt(dateFormat.
                                format(new Date()).substring(0,2)) + 1);
                        cal2.add(Calendar.MONTH, -1);
                        cal2.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                        tvLoiNhuan.setText(loiNhuan(dateFormat.
                                format(cal.getTime()),dateFormat.format(cal2.getTime()))+".000 đ");
                        tvTongThu.setText(doanhThu(dateFormat.
                                format(cal.getTime()),dateFormat.format(cal2.getTime()))+".000 đ");
                        break;
                    case 5:
                        idLayoutSelectDate.setVisibility(View.VISIBLE);
                        SelectDate(tvDateFrom);
                        SelectDate(tvDateTo);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                idLayoutSelectDate.setVisibility(View.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void date(){
        if(!tvDateFrom.getText().toString().isEmpty() && !tvDateTo.getText().toString().isEmpty()){
            tvLoiNhuan.setText(loiNhuan(tvDateFrom.getText().toString(), tvDateTo.getText().toString())+".000 đ");
            tvTongThu.setText(doanhThu(tvDateFrom.getText().toString(),tvDateTo.getText().toString())+".000 đ");
        }
    }

    private void SelectDate(TextView textView){
        Calendar calendar = Calendar.getInstance();
        nam = calendar.get(Calendar.YEAR);
        thang = calendar.get(Calendar.MONTH);
        ngay = calendar.get(Calendar.DAY_OF_MONTH);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month +1;
                        thang = month;
                        ngay = dayOfMonth;
                        String date = month+"/"+dayOfMonth+"/"+year;
                        textView.setText(date);
                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });
        date();
    }

    private int loiNhuan(String date1 ,String date2){
        int doanhThu = 0;
        int tongTienNhap = 0;
        TbDonHangDao tbDonHangDao = new TbDonHangDao();
        TbChiTietDonHangDao tbChiTietDonHangDao = new TbChiTietDonHangDao();
        List<TbChiTietDonHang> list1 = new ArrayList<>();
        TbSanPhamDao sanPhamDao = new TbSanPhamDao();
        List<TbDonHang> list = tbDonHangDao.getIdDonHang_TheoDate(date1,date2);
        for (int i = 0 ; i < list.size() ; i++){
            doanhThu += (list.get(i).getTongtien() - 50);
            list1.add(tbChiTietDonHangDao.getHdctID(list.get(i).getId_donHang()));
        }

        for (int j = 0 ; j < list1.size() ; j++){
            tongTienNhap += (sanPhamDao.getSanPhamId(list1.get(j).
                    getId_sanPham()).getGiaNhap() * list1.get(j).getSoLuong());
        }
        return doanhThu - tongTienNhap;
    }

    private int doanhThu(String date1 ,String date2){
        int doanhThu = 0;
        TbDonHangDao tbDonHangDao = new TbDonHangDao();
        List<TbDonHang> list = tbDonHangDao.getIdDonHang_TheoDate(date1,date2);
        for (int i = 0 ; i < list.size() ; i++){
            doanhThu += (list.get(i).getTongtien() - 50);
        }
        return doanhThu;
    }

}