package com.example.eventin_pengaju;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eventin_pengaju.Adapter.AdapterDEviden;
import com.example.eventin_pengaju.Adapter.AdapterMainMenu;
import com.example.eventin_pengaju.Adapter.AdapterTEviden;
import com.example.eventin_pengaju.Model.ModelEviden;
import com.example.eventin_pengaju.Model.ModelMainMenu;
import com.example.eventin_pengaju.Module.DB_Init;
import com.example.eventin_pengaju.Module.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class eviden extends AppCompatActivity {
    TextView tvKembaliEvi;
    AdapterDEviden adapterDEviden;
    AdapterTEviden adapterTEviden;
    RecyclerView rv_noEviden, rv_withEviden;

    DB_Init db;

    SessionManager session;
    HashMap<String, String> user;

    String userID;

    private ArrayList<ModelEviden> tanpaEviden = new ArrayList<>();
    private ArrayList<ModelEviden> denganEviden = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eviden);

        db = new DB_Init();

        session = new SessionManager(this);
        user = session.getUserDetails();
        userID = user.get(session.KEY_ID);

        tvKembaliEvi = findViewById(R.id.tvKembaliEvi);
        tvKembaliEvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rv_noEviden = findViewById(R.id.rv_noEviden);
        RecyclerView.LayoutManager lRvTe = new LinearLayoutManager(eviden.this);
        rv_noEviden.setLayoutManager(lRvTe);
        rv_noEviden.setItemAnimator(new DefaultItemAnimator());
        adapterTEviden = new AdapterTEviden();

        rv_withEviden = findViewById(R.id.rv_withEviden);
        RecyclerView.LayoutManager lRvWe = new LinearLayoutManager(eviden.this);
        rv_withEviden.setLayoutManager(lRvWe);
        rv_withEviden.setItemAnimator(new DefaultItemAnimator());
        adapterDEviden = new AdapterDEviden();

        Call<ArrayList<ModelEviden>> tEviden = db.getDb().getEviden(userID,"0");
        tEviden.enqueue(new Callback<ArrayList<ModelEviden>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelEviden>> call, Response<ArrayList<ModelEviden>> response) {
                tanpaEviden = response.body();
                if(response.body().size()>0)
                {
                    adapterTEviden.setContext(eviden.this);
                    adapterTEviden.setItems(tanpaEviden);
                    rv_noEviden.setAdapter(adapterTEviden);
                    adapterTEviden.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelEviden>> call, Throwable t) {

            }
        });

        Call<ArrayList<ModelEviden>> dEviden = db.getDb().getEviden(userID,"1");
        dEviden.enqueue(new Callback<ArrayList<ModelEviden>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelEviden>> call, Response<ArrayList<ModelEviden>> response) {
                denganEviden = response.body();
                if(response.body().size()>0)
                {
                    adapterDEviden.setContext(eviden.this);
                    adapterDEviden.setItems(denganEviden);
                    rv_withEviden.setAdapter(adapterDEviden);
                    adapterDEviden.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelEviden>> call, Throwable t) {

            }
        });
    }
}