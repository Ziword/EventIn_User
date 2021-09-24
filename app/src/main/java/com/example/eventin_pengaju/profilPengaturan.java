package com.example.eventin_pengaju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profilPengaturan extends AppCompatActivity {

    private TextView tvKembali,tvPw,tvEm,tvNamaProf,tvJk,tvAddress,tvNum,tvTtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_pengaturan);

        tvKembali = findViewById(R.id.tvKembali);
        tvEm = findViewById(R.id.tvEm);
        tvPw = findViewById(R.id.tvPw);
        tvNamaProf = findViewById(R.id.tvNamaProf);
        tvJk = findViewById(R.id.tvJk);
        tvAddress = findViewById(R.id.tvAddress);
        tvNum = findViewById(R.id.tvNum);
        tvTtl = findViewById(R.id.tvTtl);

        tvKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profilPengaturan.this, profilHome.class));
            }
        });
    }
}