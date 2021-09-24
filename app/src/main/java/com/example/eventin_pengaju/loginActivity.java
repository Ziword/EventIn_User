package com.example.eventin_pengaju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventin_pengaju.Model.ModelPengaju;
import com.example.eventin_pengaju.Module.DB_Init;
import com.example.eventin_pengaju.Module.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    EditText etEmailLog, etPwLog;
    ImageButton btnLog;
    TextView etRegistrasi;

    DB_Init db;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DB_Init();

        //        Cek Session
        session = new SessionManager(getApplicationContext());
        if(session.isLoggedIn())
        {
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

        etEmailLog = findViewById(R.id.etEmailLog);
        etPwLog = findViewById(R.id.etPwLog);
        etRegistrasi = findViewById(R.id.etRegistrasi);
        etRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this, registrasiActivity.class));
            }
        });

        btnLog = findViewById(R.id.btnLog);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmailLog.getText().toString().toLowerCase();
                String pass = etPwLog.getText().toString();
                if(email.isEmpty())
                {
                    Toast.makeText(loginActivity.this, "Email tidak boleh kosong.", Toast.LENGTH_LONG).show();
                    etEmailLog.requestFocus();
                    return;
                }
                if(pass.isEmpty())
                {
                    Toast.makeText(loginActivity.this, "Password tidak boleh kosong.", Toast.LENGTH_LONG).show();
                    etEmailLog.requestFocus();
                    return;
                }

                Call<ModelPengaju> login = db.getDb().cekLoginPengaju(email, pass);
                login.enqueue(new Callback<ModelPengaju>() {
                    @Override
                    public void onResponse(Call<ModelPengaju> call, Response<ModelPengaju> response) {
                        if(response.body().getCEK_DATA().equalsIgnoreCase("1"))
                        {
                            session.createLoginSession(response.body().getID_PENGAJU(),response.body().getNAMA_PENGAJU(), response.body().getEMAIL());
                            finish();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(loginActivity.this, "Email atau Password salah!",Toast.LENGTH_LONG).show();
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