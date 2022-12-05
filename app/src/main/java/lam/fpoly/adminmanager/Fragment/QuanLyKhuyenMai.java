package lam.fpoly.adminmanager.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    String loaii;
    TbKhuyenMai tbKhuyenMai;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int mYear, mMonth, mDay;
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.themkm_dialog);
                Spinner spn = dialog.findViewById(R.id.spnLoai);
                EditText edSale_addkm = dialog.findViewById(R.id.edSale_addkm);
                EditText hsd_addkm = dialog.findViewById(R.id.hsd_addkm);
                EditText maxSale_addkm = dialog.findViewById(R.id.maxSale_addkm);
                ImageView imgDate = dialog.findViewById(R.id.img_date_addkm);
                Button btnCancel = dialog.findViewById(R.id.addkm_btnCCancel);
                Button btnAdd = dialog.findViewById(R.id.addkm_btnAdd);

                List<String> listSpn = new ArrayList<>();
                listSpn.add("freeship");
                listSpn.add("sale");
                ArrayAdapter spnAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, listSpn);
                spn.setAdapter(spnAdapter);

                spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        loaii = listSpn.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                imgDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar c = Calendar.getInstance();
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        mMonth = c.get(Calendar.MONTH);
                        mYear = c.get(Calendar.YEAR);
                        Log.i("TAG", "onClick: "+mDay+"/"+mMonth+"/"+mYear);
                        DatePickerDialog d = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month+1;
                                String date  = month +"/"+dayOfMonth+"/"+year;
                                hsd_addkm.setText(date);
                            }
                        },mYear,mMonth,mDay);
                        d.show();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int sale = Integer.parseInt(edSale_addkm.getText().toString());
                        String hsd = hsd_addkm.getText().toString();
                        int maxSale = Integer.parseInt(maxSale_addkm.getText().toString());

                        if((edSale_addkm.getText().toString().length() == 0 || hsd.length() == 0 || maxSale_addkm.getText().toString().length() == 0)){
                            Toast.makeText(getContext(), "Phải nhập đủ", Toast.LENGTH_SHORT).show();
                        }else{
                            tbKhuyenMai = new TbKhuyenMai(loaii,sale,hsd,maxSale);
                            dao.insertRow(tbKhuyenMai);
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });

    }

}