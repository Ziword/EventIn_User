package com.example.eventin_pengaju;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eventin_pengaju.Adapter.AdapterMainMenu;
import com.example.eventin_pengaju.Model.ModelMainMenu;
import com.example.eventin_pengaju.Model.ModelPengaju;
import com.example.eventin_pengaju.Module.DB_Init;
import com.example.eventin_pengaju.Module.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView notifEviden, notifWawancara;

    DB_Init db;

    private RecyclerView rv;
    private ArrayList<ModelMainMenu> data = new ArrayList<>();
    private AdapterMainMenu adapterMainMenu;

    SessionManager session;
    HashMap<String, String> user;
    String userID;

    ImageButton btnPortofolio, btnHistory, btnProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notifEviden = findViewById(R.id.notifEviden);
        notifWawancara = findViewById(R.id.notifWawancara);

        db = new DB_Init();

        rv = findViewById(R.id.rvListPerusahaan);
        RecyclerView.LayoutManager layRv = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(layRv);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapterMainMenu = new AdapterMainMenu();

        session = new SessionManager(this);
        user = session.getUserDetails();
        userID = user.get(session.KEY_ID);

        btnPortofolio = findViewById(R.id.btnPortofolio);
        btnPortofolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, eviden.class);
                startActivity(i);
            }
        });

        btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, history.class);
                startActivity(i);
            }
        });

        btnProfil = findViewById(R.id.btnProfil);
        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, profilHome.class);
                startActivity(i);
            }
        });

        Call<ModelPengaju> notif = db.getDb().getMainMenuPengaju(userID);
        notif.enqueue(new Callback<ModelPengaju>() {
            @Override
            public void onResponse(Call<ModelPengaju> call, Response<ModelPengaju> response) {
                if(response.body().getTOTAL_WAWANCARA().isEmpty())
                {
                    notifWawancara.setText("0 Wawancara Mendatang");
                } else {
                    notifWawancara.setText(response.body().getTOTAL_WAWANCARA()+" Wawancara Mendatang");
                }
                if(response.body().getTOTAL_EVIDEN().isEmpty())
                {
                    notifEviden.setText("0 Event Selesai Tanpa Eviden");
                } else {
                    notifEviden.setText(response.body().getTOTAL_EVIDEN()+" Event Selesai Tanpa Eviden");
                }
            }

            @Override
            public void onFailure(Call<ModelPengaju> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        Call<ArrayList<ModelMainMenu>> callData = db.getDb().getPerusahaanMainmenu();
        callData.enqueue(new Callback<ArrayList<ModelMainMenu>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelMainMenu>> call, Response<ArrayList<ModelMainMenu>> response) {
                data = response.body();
                if(response.body().size()>0)
                {
                    adapterMainMenu.setContext(MainActivity.this);
                    adapterMainMenu.setItems(data);
                    rv.setAdapter(adapterMainMenu);
                    adapterMainMenu.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelMainMenu>> call, Throwable t) {

            }
        });
    }

    public void toEviden(View view)
    {
        Intent i = new Intent(MainActivity.this, eviden.class);
        startActivity(i);
    }

    public void toWawancara(View view) {
        Intent i = new Intent(MainActivity.this, WawancaraMendatang.class);
        startActivity(i);
    }
}