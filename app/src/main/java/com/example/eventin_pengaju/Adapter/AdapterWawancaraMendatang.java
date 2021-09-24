package com.example.eventin_pengaju.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventin_pengaju.Model.ModelAjuan;
import com.example.eventin_pengaju.Model.ModelMainMenu;
import com.example.eventin_pengaju.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterWawancaraMendatang  extends RecyclerView.Adapter<AdapterWawancaraMendatang.MyViewHolder>
{
    private ArrayList<ModelAjuan> items;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvIdWawancara, tvEventWawancara, tvWawancaraPenyelenggara, row_tglAcara, tv_lokasi, tv_waktu;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvIdWawancara = itemView.findViewById(R.id.tvIdWawancara);
            tvEventWawancara = itemView.findViewById(R.id.tvEventWawancara);
            tvWawancaraPenyelenggara = itemView.findViewById(R.id.tvWawancaraPenyelenggara);
            row_tglAcara = itemView.findViewById(R.id.row_tglAcara);
            tv_lokasi = itemView.findViewById(R.id.tv_lokasi);
            tv_waktu = itemView.findViewById(R.id.tv_waktu);
        }
    }

    public void setItems(ArrayList<ModelAjuan> items) {
        this.items = items;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public AdapterWawancaraMendatang.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_wawancara,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterWawancaraMendatang.MyViewHolder holder, int position)
    {
        ModelAjuan obj = items.get(position);
        holder.tvIdWawancara.setText(obj.getID_AJUAN());
        holder.tvEventWawancara.setText(obj.getJUDUL_AJUAN());
        holder.tvWawancaraPenyelenggara.setText(obj.getLEMBAGA_PENGAJU());
        holder.row_tglAcara.setText(obj.getTGL_MULAI_ACARA());
        holder.tv_lokasi.setText(obj.getLOKASI_WAWANCARA());
        holder.tv_waktu.setText(obj.getTGL_WAWANCARA());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
