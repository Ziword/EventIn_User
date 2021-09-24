package com.example.eventin_pengaju.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventin_pengaju.Model.ModelAjuan;
import com.example.eventin_pengaju.Model.ModelEviden;
import com.example.eventin_pengaju.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.MyViewHolder>
{
    Context context;
    ArrayList<ModelAjuan> items;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvIdHistory, tvHistoryNama, tvHistoryPenyelenggara, row_tglAcara;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvIdHistory = itemView.findViewById(R.id.tvIdHistory);
            tvHistoryNama = itemView.findViewById(R.id.tvHistoryNama);
            tvHistoryPenyelenggara = itemView.findViewById(R.id.tvHistoryPenyelenggara);
            row_tglAcara = itemView.findViewById(R.id.row_tglAcara);
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<ModelAjuan> items) {
        this.items = items;
    }

    @Override
    public AdapterHistory.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterHistory.MyViewHolder holder, int position) {
        ModelAjuan obj = items.get(position);
        holder.tvIdHistory.setText(obj.getID_AJUAN());
        holder.tvHistoryNama.setText(obj.getJUDUL_AJUAN());
        holder.tvHistoryPenyelenggara.setText(obj.getLEMBAGA_PENGAJU());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
