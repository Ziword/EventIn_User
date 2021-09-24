package com.example.eventin_pengaju;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eventin_pengaju.Module.SessionManager;

import org.w3c.dom.Text;

import java.util.HashMap;

public class profilHome extends AppCompatActivity {
    SessionManager session;
    HashMap<String, String> user;
    String userID, nama, email;

    TextView tvNamaUser, tvEmail;

    TextView tvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_home);

        session = new SessionManager(this);
        user = session.getUserDetails();
        userID = user.get(session.KEY_ID);
        nama = user.get(session.KEY_NAME);
        email = user.get(session.KEY_EMAIL);

        System.out.println("EMAIL : "+user.get(session.KEY_EMAIL));

        tvNamaUser = findViewById(R.id.tvNamaUser);
        tvEmail = findViewById(R.id.tvEmail);
        tvNamaUser.setText(nama);
        tvEmail.setText(email);


        tvLogout = findViewById(R.id.tvLogout);
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(profilHome.this)
                        .setTitle("Anda yakin ingin keluar ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                session.logoutUser();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .create();
                dialog.show();
            }
        });
    }
}