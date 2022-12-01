package lam.fpoly.adminmanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lam.fpoly.adminmanager.Dao.TbChiTietDonHangDao;
import lam.fpoly.adminmanager.Dao.TbKhachHangDao;
import lam.fpoly.adminmanager.Dao.TbSanPhamDao;
import lam.fpoly.adminmanager.Fragment.ChiTietDonHang;
import lam.fpoly.adminmanager.Model.TbChiTietDonHang;
import lam.fpoly.adminmanager.Model.TbDonHang;
import lam.fpoly.adminmanager.Model.TbKhachHang;
import lam.fpoly.adminmanager.Model.TbSanPham;
import lam.fpoly.adminmanager.R;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.GioHangViewHolder>{
    private List<TbDonHang> list;
    int number;

    public void setData(List<TbDonHang> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }

    public DonHangAdapter() {
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quanlydonhang_item,parent,false);
        return new GioHangViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        int i = position;
        TbDonHang tbDonHang = list.get(i);
        if (tbDonHang == null){
            return;
        }

        TbChiTietDonHangDao ctdhDao = new TbChiTietDonHangDao();
        TbChiTietDonHang tbChiTietDonHang = ctdhDao.getHdctID(tbDonHang.getId_donHang());
        TbSanPhamDao sanPhamDao = new TbSanPhamDao();
        TbSanPham tbSanPham = (TbSanPham) sanPhamDao.getSanPhamId(tbChiTietDonHang.getId_sanPham());

        Picasso.get().load(tbSanPham.getSrcAnh()).fit().into(holder.hd_anh_item);
        holder.hd_trangthai_item.setText(tbDonHang.getTrangThai());
        holder.hd_soluong_item.setText(""+tbChiTietDonHang.getSoLuong());
        holder.hd_ten_item.setText(tbSanPham.getTen_sanPham());
        holder.hd_gia_item.setText(tbSanPham.getGiaBan()+".000đ");

        TbKhachHangDao tbKhachHangDao = new TbKhachHangDao();
        TbKhachHang tbKhachHang = tbKhachHangDao.getUser(tbDonHang.getId_khachHang());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context, ChiTietDonHang.class);
                intent.putExtra("ten_kh", tbKhachHang.getTen_khachHang());
                intent.putExtra("sdt_kh", tbKhachHang.getSdt_khachHang());
                intent.putExtra("diachi_kh", tbKhachHang.getDiaChi());
                intent.putExtra("trangThai", tbDonHang.getTrangThai());
                intent.putExtra("ten_sp",tbSanPham.getTen_sanPham());
                intent.putExtra("giaBan", tbSanPham.getGiaBan());
                intent.putExtra("anh", tbSanPham.getSrcAnh());
                intent.putExtra("soLuong", tbChiTietDonHang.getSoLuong());
                intent.putExtra("id_donHang", tbDonHang.getId_donHang());
                holder.context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list.size() != 0){
            return list.size();
        }
        return 0;
    }
    public class GioHangViewHolder extends RecyclerView.ViewHolder{
        TextView hd_ten_item,hd_gia_item,hd_trangthai_item, hd_soluong_item;
        ImageView hd_anh_item;
        Context context;
        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            hd_anh_item = itemView.findViewById(R.id.hd_anh_item);
            hd_ten_item = itemView.findViewById(R.id.hd_ten_item);
            hd_gia_item = itemView.findViewById(R.id.hd_gia_item);
            hd_trangthai_item = itemView.findViewById(R.id.hd_trangthai_item);
            hd_soluong_item = itemView.findViewById(R.id.hd_soluong_item);
            context = itemView.getContext();
        }
    }
}
