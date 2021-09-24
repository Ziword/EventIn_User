package com.example.eventin_pengaju.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eventin_pengaju.Model.ModelMainMenu;
import com.example.eventin_pengaju.Module.DB_Init;
import com.example.eventin_pengaju.R;
import com.example.eventin_pengaju.profilPerusahaan;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdapterMainMenu extends RecyclerView.Adapter<AdapterMainMenu.MyViewHolder>
{
    private ArrayList<ModelMainMenu> items;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView logoPerusahaan;
        TextView tvPersentase, txtNPerusahaan, txtProfil, textBidangPerusahaan, txtJumEvent;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            logoPerusahaan = itemView.findViewById(R.id.logoPerusahaan);
            tvPersentase = itemView.findViewById(R.id.tvPersentase);
            txtNPerusahaan = itemView.findViewById(R.id.txtNPerusahaan);
            txtProfil = itemView.findViewById(R.id.txtProfil);
            textBidangPerusahaan = itemView.findViewById(R.id.textBidangPerusahaan);
            txtJumEvent = itemView.findViewById(R.id.txtJumEvent);
        }
    }

    public void setItems(ArrayList<ModelMainMenu> items) {
        this.items = items;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public AdapterMainMenu.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main_menu,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterMainMenu.MyViewHolder holder, int position)
    {
        ModelMainMenu obj = items.get(position);

        Glide.with(context).load(obj.getGetGambarPerusahaan()).into(holder.logoPerusahaan);
        holder.txtNPerusahaan.setText(obj.getNAMA_PERUSAHAAN());
        holder.txtProfil.setText(obj.getPROFIL_SINGKAT());
        holder.textBidangPerusahaan.setText(obj.getBIDANG_PERUSAHAAN());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, profilPerusahaan.class);
                i.putExtra("PERUSAHAAN",obj);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }
}
