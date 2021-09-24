package com.example.eventin_pengaju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eventin_pengaju.Model.ModelMainMenu;

public class profilPerusahaan extends AppCompatActivity {
    ImageView logoDPerusahaan;
    TextView txtNDPerusahaan,txtDBidangPerusahaan, txtBack;

    TextView tentangDetail, visi_isi, website_isi, jenis_perusahaan;

    TextView homeBar, tentangBar, eventBar;

    LinearLayout layHomeDetail, layTentangDetail, layEventDetail;

    ImageButton btnAjukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_perusahaan);

        ModelMainMenu perusahaan = getIntent().getParcelableExtra("PERUSAHAAN");

        homeBar = findViewById(R.id.homeBar);
        layHomeDetail = findViewById(R.id.layHomeDetail);

        tentangBar = findViewById(R.id.tentangBar);
        layTentangDetail = findViewById(R.id.layTentangDetail);

        eventBar = findViewById(R.id.eventBar);
        layEventDetail = findViewById(R.id.layEventDetail);

        homeBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layHomeDetail.setVisibility(View.VISIBLE);
                layTentangDetail.setVisibility(View.GONE);
                layEventDetail.setVisibility(View.GONE);

                homeBar.setTextColor(getResources().getColor(R.color.black));
                tentangBar.setTextColor(getResources().getColor(R.color.dark_50));
                eventBar.setTextColor(getResources().getColor(R.color.dark_50));
            }
        });

        tentangBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layHomeDetail.setVisibility(View.GONE);
                layTentangDetail.setVisibility(View.VISIBLE);
                layEventDetail.setVisibility(View.GONE);

                homeBar.setTextColor(getResources().getColor(R.color.dark_50));
                tentangBar.setTextColor(getResources().getColor(R.color.black));
                eventBar.setTextColor(getResources().getColor(R.color.dark_50));
            }
        });

        eventBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layHomeDetail.setVisibility(View.GONE);
                layTentangDetail.setVisibility(View.GONE);
                layEventDetail.setVisibility(View.VISIBLE);

                homeBar.setTextColor(getResources().getColor(R.color.dark_50));
                tentangBar.setTextColor(getResources().getColor(R.color.dark_50));
                eventBar.setTextColor(getResources().getColor(R.color.black));
            }
        });

        logoDPerusahaan = findViewById(R.id.logoDPerusahaan);
        txtNDPerusahaan = findViewById(R.id.txtNDPerusahaan);
        txtDBidangPerusahaan = findViewById(R.id.txtDBidangPerusahaan);

        tentangDetail = findViewById(R.id.tentangDetail);
        visi_isi = findViewById(R.id.visi_isi);
        website_isi = findViewById(R.id.website_isi);
        jenis_perusahaan = findViewById(R.id.jenis_perusahaan);

        Glide.with(this).load(perusahaan.getGetGambarPerusahaan()).into(logoDPerusahaan);
        txtNDPerusahaan.setText(perusahaan.getNAMA_PERUSAHAAN());
        txtDBidangPerusahaan.setText(perusahaan.getBIDANG_PERUSAHAAN());
        tentangDetail.setText(perusahaan.getPROFIL_SINGKAT());
        visi_isi.setText(perusahaan.getVISI());
        website_isi.setText(perusahaan.getWEBSITE_PERUSAHAAN());
        jenis_perusahaan.setText(perusahaan.getBIDANG_PERUSAHAAN());

        txtBack = findViewById(R.id.txtBack);
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAjukan = findViewById(R.id.btnAjukan);
        btnAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(profilPerusahaan.this, pengajuan.class);
                i.putExtra("PERUSAHAAN", perusahaan);
                startActivity(i);
            }
        });
    }
}