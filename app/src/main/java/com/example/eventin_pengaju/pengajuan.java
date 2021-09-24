package com.example.eventin_pengaju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventin_pengaju.Model.ModelMainMenu;
import com.example.eventin_pengaju.Model.ModelPengajuan;
import com.example.eventin_pengaju.Module.SessionManager;

import org.w3c.dom.Text;

import java.util.HashMap;

public class pengajuan extends AppCompatActivity {
    EditText etLembaga, etTelponUtama, etNoAlternatif, tvJudul;
    ImageButton btnSimpanLanjutkan;

    TextView backMenu;

    SessionManager session;
    HashMap<String, String> user;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan);

        ModelMainMenu perusahaan = getIntent().getParcelableExtra("PERUSAHAAN");

        backMenu = findViewById(R.id.backMenu);
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        session = new SessionManager(this);
        user = session.getUserDetails();
        userID = user.get(session.KEY_ID);

        etLembaga = findViewById(R.id.etLembaga);
        etTelponUtama = findViewById(R.id.etTelponUtama);
        etNoAlternatif = findViewById(R.id.etNoAlternatif);
        tvJudul = findViewById(R.id.tvJudul);

        btnSimpanLanjutkan = findViewById(R.id.btnSimpanLanjutkan);
        btnSimpanLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String lembaga = etLembaga.getText().toString();
                String telponUtama = etTelponUtama.getText().toString();
                String telponAlternatif = etNoAlternatif.getText().toString();
                String judul = tvJudul.getText().toString();

                if(lembaga.isEmpty() || telponAlternatif.isEmpty() || telponUtama.isEmpty() || judul.isEmpty())
                {
                    Toast.makeText(pengajuan.this, "Data tidak boleh kosong.", Toast.LENGTH_LONG).show();
                    return;
                }

                ModelPengajuan obj = new ModelPengajuan();
                obj.setID_PENGAJU(userID);
                obj.setID_PERUSAHAAN(perusahaan.getID_PERUSAHAAN());
                obj.setLEMBAGA_PENGAJU(lembaga);
                obj.setNO_HP_UTAMA(telponUtama);
                obj.setNO_HP_ALT(telponAlternatif);
                obj.setJUDUL_AJUAN(judul);

                Intent i = new Intent(pengajuan.this, pengajuanDetail.class);
                i.putExtra("PENGAJUAN",obj);
                startActivity(i);
            }
        });
    }
}