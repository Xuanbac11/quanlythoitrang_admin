package lam.fpoly.adminmanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lam.fpoly.adminmanager.Model.TbKhuyenMai;
import lam.fpoly.adminmanager.R;

public class KhuyenMaiAdapter extends RecyclerView.Adapter<KhuyenMaiAdapter.KhuyenMaiViewHolder>{
    private List<TbKhuyenMai> list;

    public void setData(List<TbKhuyenMai> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public KhuyenMaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.khuyenmai_item,parent,false);
        return new KhuyenMaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhuyenMaiViewHolder holder, int position) {
        int i = position;
        TbKhuyenMai obj = list.get(i);
        if (obj == null){
            return;
        }
        if(obj.getLoai().equals("freeship")){
            holder.layout_km.setBackgroundColor(Color.parseColor("#009688"));
            holder.logo_km.setImageResource(R.drawable.truck_fast);
            holder.km_khuyenmai_item.setText("Miễn phí vận chuyển");
        }else{
            holder.logo_km.setImageResource(R.drawable.shopping_bag);
            holder.km_khuyenmai_item.setText("Giảm "+obj.getSale()+"%");
        }
        holder.km_toida_item.setText("Giảm tối đa: "+obj.getMaxSale()+"k");
        holder.km_hsd_item.setText("HSD: "+fmDate(obj.getHsd()));
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class KhuyenMaiViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout_km;
        ImageView logo_km;
        TextView km_khuyenmai_item,km_toida_item,km_hsd_item;
        Context context;
        public KhuyenMaiViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_km = itemView.findViewById(R.id.layout_km);
            km_khuyenmai_item = itemView.findViewById(R.id.km_khuyenmai_item);
            km_toida_item = itemView.findViewById(R.id.km_toida_item);
            km_hsd_item = itemView.findViewById(R.id.km_hsd_item);
            logo_km = itemView.findViewById(R.id.logo_km);
            context = itemView.getContext();
        }
    }

    private String fmDate(String date){
        String kqua = "";
        String[] tach = date.split("-");
        for (int i = tach.length-1; i>=0; i--){
            kqua += tach[i];
            if(i!=0){
                kqua += "-";
            }
        }
        return kqua;
    }
}
