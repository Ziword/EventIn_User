package com.example.eventin_pengaju.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eventin_pengaju.Model.ModelEviden;
import com.example.eventin_pengaju.R;
import com.example.eventin_pengaju.evidenUpload;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterTEviden extends RecyclerView.Adapter<AdapterTEviden.MyViewHolder> {
    Context context;
    ArrayList<ModelEviden> items;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvNamaEvent, tvNamaPenyelenggara, row_tglAcara;
        ImageButton btnAddEviden;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvNamaEvent = itemView.findViewById(R.id.tvNamaEvent);
            tvNamaPenyelenggara = itemView.findViewById(R.id.tvNamaPenyelenggara);
            row_tglAcara = itemView.findViewById(R.id.row_tglAcara);

            btnAddEviden = itemView.findViewById(R.id.btnAddEviden);
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<ModelEviden> items) {
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterTEviden.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_no_eviden,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterTEviden.MyViewHolder holder, int position) {
        ModelEviden obj = items.get(position);
        holder.tvNamaEvent.setText(obj.getJUDUL_AJUAN());
        holder.tvNamaPenyelenggara.setText(obj.getLEMBAGA_PENGAJU());
        holder.row_tglAcara.setText(obj.getTGL_MULAI_ACARA()+" |");

        holder.btnAddEviden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, evidenUpload.class);
                i.putExtra("EVIDEN", obj);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
