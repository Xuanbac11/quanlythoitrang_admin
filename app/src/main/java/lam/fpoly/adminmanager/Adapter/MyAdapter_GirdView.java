package lam.fpoly.adminmanager.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.Dao.TbDanhMucDao;
import lam.fpoly.adminmanager.Dao.TbSanPhamDao;
import lam.fpoly.adminmanager.FragmentViewPager.Create_Fragment;
import lam.fpoly.adminmanager.LoadAnh;
import lam.fpoly.adminmanager.Model.TbDanhMuc;
import lam.fpoly.adminmanager.Model.TbSanPham;
import lam.fpoly.adminmanager.R;

public class MyAdapter_GirdView extends BaseAdapter implements Filterable {
    private Context context;
    public static List<TbSanPham> list;
    int loaii;
    String tenLoai;
    TbSanPhamDao tbSanPhamDao;
    public static ImageView ANH_EDIT;
    public MyAdapter_GirdView(Context context, List<TbSanPham> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IconViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new IconViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layouitem_danhmuc, null);
            viewHolder.itemImg = convertView.findViewById(R.id.item_Img);
            viewHolder.tvSpName = convertView.findViewById(R.id.tvSpName);
            viewHolder.tvSpPrice = convertView.findViewById(R.id.tvSpPrice);
            viewHolder.layout_sp = convertView.findViewById(R.id.layout_sp);

            int i = position;
            TbSanPham obj = list.get(i);


            viewHolder.layout_sp.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.suasanpham_dialog);

                    Window window = dialog.getWindow();
                    if (window == null) {
                        return false;
                    }
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    Spinner spn = dialog.findViewById(R.id.spnLoaisp_editsp);
                    ANH_EDIT = dialog.findViewById(R.id.anh_editsp);
                    EditText tensp = dialog.findViewById(R.id.tensp_editsp);
                    EditText gianhap = dialog.findViewById(R.id.gianhap_editsp);
                    EditText giaban = dialog.findViewById(R.id.giaban_editsp);
                    EditText tonkho = dialog.findViewById(R.id.tonkho_editsp);
                    EditText in4 = dialog.findViewById(R.id.info_editsp);
                    Button cancel = dialog.findViewById(R.id.editsp_btnCancel);
                    Button save = dialog.findViewById(R.id.editsp_btnSave);


                    tensp.setText(obj.getTen_sanPham());
                    Log.i("TAG", "onLongClick: "+obj.getSrcAnh());
                    Picasso.get().load(obj.getSrcAnh()).fit().into(ANH_EDIT);
                    gianhap.setText(obj.getGiaNhap() + ".000đ");
                    giaban.setText(obj.getGiaBan() + ".000đ");
                    tonkho.setText("" + obj.getTonKho());
                    in4.setText(obj.getIn4());

                    ANH_EDIT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, LoadAnh.class);
                            LoadAnh.checkEditOrAdd = false;
                            context.startActivity(intent);
                        }
                    });

                    tbSanPhamDao = new TbSanPhamDao();
                    TbDanhMucDao tbDanhMucDao = new TbDanhMucDao();


                    List<String> listSpn = new ArrayList<>();
                    List<TbDanhMuc> danhMucList = tbDanhMucDao.getAll();
                    for (TbDanhMuc tbdm : danhMucList) {
                        listSpn.add(tbdm.getTen_danhMuc());
                    }

                    ArrayAdapter spnAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, listSpn);
                    spn.setAdapter(spnAdapter);
                    spn.setSelection(obj.getId_danhmuc()-1);
                    spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            loaii = tbDanhMucDao.getID(listSpn.get(position));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //trả link ảnh vào đây
                            String anhsp = LoadAnh.linkUpload;
                            String ten_sp = tensp.getText().toString();
                            int gia_nhap = fmTien(gianhap.getText().toString());
                            int gia_ban = fmTien(giaban.getText().toString());
                            int ton_kho = Integer.parseInt(tonkho.getText().toString());
                            String in_fo = in4.getText().toString();

                            if (anhsp.length() == 0 || ten_sp.length() == 0 || gianhap.getText().toString().length() == 0 || giaban.getText().toString().length() == 0 || tonkho.getText().toString().length() == 0) {
                                Toast.makeText(context, "Phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                            } else {
                                TbSanPham obj = new TbSanPham(ten_sp,anhsp,gia_nhap,gia_ban,ton_kho,loaii,in_fo);
                                tbSanPhamDao.upDateSp(obj);
                                Create_Fragment.setGridView(list,Create_Fragment.idGridView);
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });

                    dialog.show();
                    return false;
                }
            });


            //viewHolder.tvSale = convertView.findViewById(R.id.tvSale);
            //viewHolder.tvSpPrice_sale = convertView.findViewById(R.id.tvSpPrice_sale);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (IconViewHolder) convertView.getTag();
        }
        viewHolder.tvSpName.setText(list.get(position).getTen_sanPham());
        //viewHolder.tvSale.setText("     Sale 50%");
        viewHolder.tvSpPrice.setText(list.get(position).getGiaBan() + ".000đ");
        //viewHolder.tvSpPrice_sale.setText(list.get(position).getGiaBan()/2+".000đ");
        Picasso.get().load(list.get(position).getSrcAnh()).fit().into(viewHolder.itemImg);
        return convertView;
    }

    public static class IconViewHolder {
        private TextView tvSpName, tvSpPrice, tvSale, tvSpPrice_sale;
        private ImageView itemImg;
        private CardView layout_sp;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    private int fmTien(String tien) {
        String[] chuoi = tien.split(".000");
        int kq = Integer.parseInt(chuoi[0]);
        return kq;
    }
}
