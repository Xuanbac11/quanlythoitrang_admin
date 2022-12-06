package lam.fpoly.adminmanager.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lam.fpoly.adminmanager.Model.TbChiTietKhachHang;
import lam.fpoly.adminmanager.Model.TbKhachHang;
import lam.fpoly.adminmanager.R;

public class khachHangChitietAdapter extends RecyclerView.Adapter<khachHangChitietAdapter.khachHangChitietHolder>{
    private List<TbChiTietKhachHang> list;
    public void setData(List<TbChiTietKhachHang> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public khachHangChitietHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitietkhachhang,parent,false);
        return new khachHangChitietHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull khachHangChitietHolder holder, int position) {
        TbChiTietKhachHang obj=list.get(position);
        if (obj == null){
            return;
        }
        holder.id_dm.setText(list.get(position).getId());
        holder.ten_dm.setText(list.get(position).getTendonhang());
        holder.ngay_dm.setText(fmDate(list.get(position).getNgaymua()));

        String tt=list.get(position).getTrangthai();
        holder.tt_dm.setText(tt);
        if(tt.length()>13){
         holder.tt_dm.setText(tt.substring(0,13)+"...");
        }
        holder.tt_dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),tt , Toast.LENGTH_SHORT).show();
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

    public class khachHangChitietHolder extends RecyclerView.ViewHolder {
        TextView id_dm,ten_dm,ngay_dm,tt_dm;
        public khachHangChitietHolder(@NonNull View itemView) {
            super(itemView);
              id_dm=itemView.findViewById(R.id.id_donmua);
              ten_dm=itemView.findViewById(R.id.ten_donmua);
              ngay_dm=itemView.findViewById(R.id.ngay_donmua);
              tt_dm=itemView.findViewById(R.id.tt_donmua);
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
