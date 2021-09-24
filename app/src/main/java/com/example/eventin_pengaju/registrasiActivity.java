package com.example.eventin_pengaju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventin_pengaju.Model.ModelPengaju;
import com.example.eventin_pengaju.Module.DB_Init;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registrasiActivity extends AppCompatActivity {

    EditText etNamaReg, etEmailReg, etPwReg;
    CheckBox cbKetentuan;
    ImageButton btnReg;
    TextView etKembali;

    DB_Init db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        db = new DB_Init();

        etNamaReg = findViewById(R.id.etNamaReg);
        etEmailReg = findViewById(R.id.etEmailReg);
        etPwReg = findViewById(R.id.etPwReg);
        cbKetentuan = findViewById(R.id.cbKetentuan);
        btnReg = findViewById(R.id.btnReg);
        etKembali = findViewById(R.id.et_kembali);

        etKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaReg, emailReg, pwReg;
                namaReg = etNamaReg.getText().toString();
                emailReg = etEmailReg.getText().toString().toLowerCase().trim();
                pwReg = etPwReg.getText().toString();

                if(namaReg.isEmpty())
                {
                    Toast.makeText(registrasiActivity.this, "Nama tidak boleh kosong.", Toast.LENGTH_LONG).show();
                    etNamaReg.requestFocus();
                    return;
                }
                if(emailReg.isEmpty())
                {
                    Toast.makeText(registrasiActivity.this, "Email tidak boleh kosong.", Toast.LENGTH_LONG).show();
                    etEmailReg.requestFocus();
                    return;
                }
                if(pwReg.isEmpty())
                {
                    Toast.makeText(registrasiActivity.this, "Password tidak boleh kosong.", Toast.LENGTH_LONG).show();
                    etPwReg.requestFocus();
                    return;
                }
                if(cbKetentuan.isChecked() == false)
                {
                    Toast.makeText(registrasiActivity.this, "Syarat dan ketentuan harus dicentang.", Toast.LENGTH_LONG).show();
                    cbKetentuan.requestFocus();
                    return;
                }

                Call<ModelPengaju> pendaftaranPengaju = db.getDb().pendaftaranPengaju(namaReg, emailReg, pwReg);
                pendaftaranPengaju.enqueue(new Callback<ModelPengaju>() {
                    @Override
                    public void onResponse(Call<ModelPengaju> call, Response<ModelPengaju> response) {
                        if(response.body().getCEK_DATA().equalsIgnoreCase("0"))
                        {
                            Toast.makeText(registrasiActivity.this, "Email telah terdaftar.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(registrasiActivity.this, "Pendaftaran berhasil.", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(registrasiActivity.this, loginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelPengaju> call, Throwable t) {

                    }
                });
            }
        });
    }
}