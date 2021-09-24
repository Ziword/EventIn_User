package com.example.eventin_pengaju.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eventin_pengaju.Model.ModelEviden;
import com.example.eventin_pengaju.R;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdapterDEviden extends RecyclerView.Adapter<AdapterDEviden.MyViewHolder>
{
    Context context;
    ArrayList<ModelEviden> items;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView namaEventWE, tglEventWE;
        ImageView gambarE;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            namaEventWE = itemView.findViewById(R.id.namaEventWE);
            tglEventWE = itemView.findViewById(R.id.tglEventWE);
            gambarE = itemView.findViewById(R.id.gambarE);
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<ModelEviden> items) {
        this.items = items;
    }

    @Override
    public AdapterDEviden.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_with_eviden,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterDEviden.MyViewHolder holder, int position) {
        ModelEviden obj = items.get(position);
        Glide.with(context).load(obj.getAlamatGambar()).into(holder.gambarE);
        holder.namaEventWE.setText("Nama Event" + obj.getJUDUL_AJUAN());
        holder.tglEventWE.setText(obj.getTGL_MULAI_ACARA()+" - "+obj.getTGL_AKHIR_ACARA());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
