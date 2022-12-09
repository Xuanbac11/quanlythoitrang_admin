package lam.fpoly.adminmanager.Adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lam.fpoly.adminmanager.Dao.TbSanPhamDao;
import lam.fpoly.adminmanager.Model.TbChiTietDonHang;
import lam.fpoly.adminmanager.Model.TbSanPham;
import lam.fpoly.adminmanager.R;

public class ThongKeAdapter extends RecyclerView.Adapter<ThongKeAdapter.GioHangViewHolder>{
    private List<TbChiTietDonHang> list;

    public void setData(List<TbChiTietDonHang> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsanpham_thongke,parent,false);
        return new GioHangViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        int i = position;
        TbChiTietDonHang tbChiTietDonHang = list.get(i);
        if (tbChiTietDonHang == null){
            return;
        }

        TbSanPhamDao sanPhamDao = new TbSanPhamDao();
        TbSanPham tbSanPham = sanPhamDao.getSanPham(tbChiTietDonHang.getId_sanPham());

        Picasso.get().load(tbSanPham.getSrcAnh()).fit().into(holder.imgItem_TK);
        holder.tvItemName_TK.setText(tbSanPham.getTen_sanPham());
        holder.tvItemGiaBan_TK.setText(String.valueOf(tbSanPham.getGiaBan()));
        holder.tvItemSL_DaBan_TK.setText(String.valueOf(tbChiTietDonHang.getSoLuong()));
        holder.tvItem_TongThu_TK.setText(String.valueOf(tbSanPham.getGiaBan() * tbChiTietDonHang.getSoLuong()));

    }

    @Override
    public int getItemCount() {
        if (list.size() != 0){
            return list.size();
        }
        return 0;
    }
    public class GioHangViewHolder extends RecyclerView.ViewHolder{
        ImageView imgItem_TK;
        TextView tvItem_TongThu_TK, tvItemSL_DaBan_TK ,tvItemGiaBan_TK ,tvItemName_TK;
        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItem_TK = itemView.findViewById(R.id.imgItem_TK);
            tvItem_TongThu_TK = itemView.findViewById(R.id.tvItem_TongThu_TK);
            tvItemSL_DaBan_TK = itemView.findViewById(R.id.tvItemSL_DaBan_TK);
            tvItemGiaBan_TK = itemView.findViewById(R.id.tvItemGiaBan_TK);
            tvItemName_TK = itemView.findViewById(R.id.tvItemName_TK);
        }
    }
}
