package com.example.eventin_pengaju;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eventin_pengaju.Adapter.AdapterMainMenu;
import com.example.eventin_pengaju.Adapter.AdapterWawancaraMendatang;
import com.example.eventin_pengaju.Model.ModelAjuan;
import com.example.eventin_pengaju.Model.ModelMainMenu;
import com.example.eventin_pengaju.Module.DB_Init;
import com.example.eventin_pengaju.Module.SessionManager;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WawancaraMendatang extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<ModelAjuan> data = new ArrayList<>();
    private AdapterWawancaraMendatang adapterWawancaraMendatang;
    DB_Init db;

    TextView tvKembaliWawancara;

    SessionManager session;
    HashMap<String, String> user;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wawancara_mendatang);

        session = new SessionManager(this);
        user = session.getUserDetails();
        userID = user.get(session.KEY_ID);

        rv = findViewById(R.id.rv_wawancara);
        RecyclerView.LayoutManager layRv = new LinearLayoutManager(WawancaraMendatang.this);
        rv.setLayoutManager(layRv);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapterWawancaraMendatang = new AdapterWawancaraMendatang();

        db = new DB_Init();

        tvKembaliWawancara = findViewById(R.id.tvKembaliWawancara);
        tvKembaliWawancara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Call<ArrayList<ModelAjuan>> getWawancara = db.getDb().getWawancara(userID);
        getWawancara.enqueue(new Callback<ArrayList<ModelAjuan>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                data = response.body();
                if(response.body().size()>0)
                {
                    adapterWawancaraMendatang.setContext(WawancaraMendatang.this);
                    adapterWawancaraMendatang.setItems(data);
                    rv.setAdapter(adapterWawancaraMendatang);
                    adapterWawancaraMendatang.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {

            }
        });
    }
}