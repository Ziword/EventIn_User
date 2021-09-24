package com.example.eventin_pengaju;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eventin_pengaju.Adapter.AdapterHistory;
import com.example.eventin_pengaju.Adapter.AdapterMainMenu;
import com.example.eventin_pengaju.Model.ModelAjuan;
import com.example.eventin_pengaju.Model.ModelMainMenu;
import com.example.eventin_pengaju.Module.DB_Init;
import com.example.eventin_pengaju.Module.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class history extends AppCompatActivity {
    TextView tvKembaliHistory;

    RecyclerView rv;
    private ArrayList<ModelAjuan> data = new ArrayList<>();
    AdapterHistory adapterHistory;

    SessionManager session;
    HashMap<String, String> user;
    String userID;

    DB_Init db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tvKembaliHistory = findViewById(R.id.tvKembaliHistory);
        tvKembaliHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rv = findViewById(R.id.rv_history);
        RecyclerView.LayoutManager layRv = new LinearLayoutManager(history.this);
        rv.setLayoutManager(layRv);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapterHistory = new AdapterHistory();

        db = new DB_Init();

        session = new SessionManager(this);
        user = session.getUserDetails();
        userID = user.get(session.KEY_ID);

        Call<ArrayList<ModelAjuan>> getHistory = db.getDb().getAjuan(userID);
        getHistory.enqueue(new Callback<ArrayList<ModelAjuan>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                data = response.body();
                if(response.body().size()>0)
                {
                    adapterHistory.setContext(history.this);
                    adapterHistory.setItems(data);
                    rv.setAdapter(adapterHistory);
                    adapterHistory.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {

            }
        });
    }
}