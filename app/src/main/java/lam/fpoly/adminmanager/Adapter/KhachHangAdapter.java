package lam.fpoly.adminmanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lam.fpoly.adminmanager.Dao.TbKhachHangDao;
import lam.fpoly.adminmanager.MainActivity;
import lam.fpoly.adminmanager.Model.TbKhachHang;
import lam.fpoly.adminmanager.R;
import lam.fpoly.adminmanager.chitietkhachhang.ChitietKH;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.KhachhangHoder>{
    private List<TbKhachHang> list;
    public void setData(List<TbKhachHang> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }




    @NonNull
    @Override
    public KhachhangHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quanlykhachhang,parent,false);
        return new KhachhangHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachhangHoder holder, int position) {
     int i=position;

    TbKhachHang obj=list.get(i);
        if (obj == null){
            return;
        }
        holder.txtusername.setText(obj.getUserName());
        holder.txtphonenumber.setText(""+obj.getSdt_khachHang());
        String diachi=obj.getDiaChi();
        if(diachi.length()>16){
            holder.txtdiachi.setText(diachi.substring(0,16)+"...");
        }

        Picasso.get().load(list.get(position).getAvatar()).fit().into(holder.imganhkh);
        Log.i("TAG", "onBindViewHolder: "+list.get(position).getAvatar());
        holder.imgchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(holder.context, ChitietKH.class);
                i.putExtra("id",obj.getId_khachHang());
                i.putExtra("name",obj.getUserName());
                i.putExtra("sdt",obj.getSdt_khachHang());
                holder.context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class KhachhangHoder extends RecyclerView.ViewHolder{
        ImageView imganhkh,imgchitiet;
        TextView txtusername,txtphonenumber,txtdiachi;
        Context context;
        public KhachhangHoder(@NonNull View itemView) {
            super(itemView);
            imganhkh=itemView.findViewById(R.id.img_ql_anh_kh);
            imgchitiet=itemView.findViewById(R.id.imgql_chitiet_kh);
            txtusername=itemView.findViewById(R.id.txtql_username_kh);
            txtphonenumber=itemView.findViewById(R.id.txtql_sdt_kh);
            txtdiachi=itemView.findViewById(R.id.txtql_diachi_kh);
            context=itemView.getContext();
        }
    }
}
