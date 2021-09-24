package com.example.eventin_pengaju;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.eventin_pengaju.Model.ModelEviden;
import com.example.eventin_pengaju.Module.DB_Init;
import com.example.eventin_pengaju.Module.FilePath;

import org.w3c.dom.Text;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class evidenUpload extends AppCompatActivity {
    TextView tvKembaliUploadE;
    TextView tvnEviUpload, tvpUpload, tglEventU;
    ImageButton btnPilihEvi, btnUpSave;

    Uri fileUri;
    String displayName="", filePath;

    DB_Init db;

    ModelEviden eviden;


    int CODE_PICK_IMAGE = 1002;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eviden_upload);

        tvKembaliUploadE = findViewById(R.id.tvKembaliUploadE);
        tvKembaliUploadE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        eviden = getIntent().getParcelableExtra("EVIDEN");

        db = new DB_Init();

        tvnEviUpload = findViewById(R.id.tvnEviUpload);
        tvpUpload = findViewById(R.id.tvpUpload);
        tglEventU = findViewById(R.id.tglEventU);
        tvnEviUpload.setText(eviden.getJUDUL_AJUAN());
        tvpUpload.setText(eviden.getLEMBAGA_PENGAJU());
        tglEventU.setText(eviden.getTGL_MULAI_ACARA()+" - "+eviden.getTGL_AKHIR_ACARA());

        btnPilihEvi = findViewById(R.id.btnPilihEvi);
        btnPilihEvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "select image"),
                        CODE_PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1002:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    fileUri = data.getData();
                    displayName = getNameFromContentUri(evidenUpload.this, fileUri);
                    try {
                        Glide.with(this).load(new File(FilePath.getPath(evidenUpload.this,fileUri))).into(btnPilihEvi);
                    } catch (Exception e)
                    {
                        System.out.println("RESPONSE : "+e.getMessage());
                    }
                }
                break;
        }

        btnUpSave = findViewById(R.id.btnUpSave);
        btnUpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(displayName.isEmpty())
                {
                    Toast.makeText(evidenUpload.this, "Gambar harus dipilih.",Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    filePath = FilePath.getPath(evidenUpload.this, fileUri);
                } catch (Exception e){
                    Toast.makeText(evidenUpload.this, "Penyimpanan tidak dapat diakses.",Toast.LENGTH_LONG).show();
                }

                RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), new File(filePath));
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("eviden", new File(filePath).getName(), requestBody);
                RequestBody id_eviden = RequestBody.create(MediaType.parse("text/plain"), eviden.getID_EVIDEN());

                Call<ResponseBody> updateEviden = db.getDb().updateEviden(id_eviden, fileToUpload);
                updateEviden.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(evidenUpload.this,"Berhasil menambahkan eviden event.",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(evidenUpload.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });


    }

    public static String getNameFromContentUri(Context context, Uri contentUri){
        Cursor returnCursor = context.getContentResolver().query(contentUri, null, null, null, null);
        int nameColumnIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String fileName = returnCursor.getString(nameColumnIndex);
        return fileName;}
}