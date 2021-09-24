package com.example.eventin_pengaju;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eventin_pengaju.Model.ModelJenisSponsor;
import com.example.eventin_pengaju.Model.ModelPengajuan;
import com.example.eventin_pengaju.Module.DB_Init;
import com.example.eventin_pengaju.Module.FilePath;
import com.example.eventin_pengaju.Module.SessionManager;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pengajuanDetail extends AppCompatActivity {
    TextView tvKembaliAjuan, tvNominal, namaFile;
    EditText etlokasi, ettglawal, ettglakhir, et_keterangan, etNominal;
    ImageButton btnChose, btnSimpan;
    Spinner spinner_jenis;

    List<String> jenisAjuan = new ArrayList<String>();

    String displayName = "";
    Uri fileUri;
    int CODE_PICK_PDF = 1001;
    String PdfPathHolder;

    DB_Init db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_detail);

        ModelPengajuan pengajuan = getIntent().getParcelableExtra("PENGAJUAN");

        tvKembaliAjuan = findViewById(R.id.tvKembaliAjuan);
        tvKembaliAjuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etlokasi = findViewById(R.id.etlokasi);

        ettglawal = findViewById(R.id.ettglawal);
        ettglawal.setInputType(InputType.TYPE_NULL);
        ettglawal.requestFocus();

        namaFile = findViewById(R.id.namaFile);

        ettglakhir = findViewById(R.id.ettglakhir);
        ettglakhir.setInputType(InputType.TYPE_NULL);
        ettglakhir.requestFocus();

        et_keterangan = findViewById(R.id.et_keterangan);

        tvNominal = findViewById(R.id.tvnominal);
        etNominal = findViewById(R.id.etnominal);

        spinner_jenis = findViewById(R.id.spinner_jenis);
        spinner_jenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 3)
                {
                    tvNominal = findViewById(R.id.tvnominal);
                    etNominal = findViewById(R.id.etnominal);
                    tvNominal.setVisibility(View.VISIBLE);
                    etNominal.setVisibility(View.VISIBLE);
                } else {
                    tvNominal = findViewById(R.id.tvnominal);
                    etNominal = findViewById(R.id.etnominal);
                    tvNominal.setVisibility(View.GONE);
                    etNominal.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        db = new DB_Init();

        btnChose = findViewById(R.id.btnChose);
        btnChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(pengajuanDetail.this).withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        Intent intentPDF = new Intent(Intent.ACTION_GET_CONTENT);
                        intentPDF.setType("application/pdf");
                        intentPDF.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(Intent.createChooser(intentPDF , "Select Picture"), CODE_PICK_PDF);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }
                }).check();
            }
        });

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lokasi = etlokasi.getText().toString();
                String tglAwal = ettglawal.getText().toString();
                String tglAkhir = ettglakhir.getText().toString();
                String keterangan = et_keterangan.getText().toString();
                String nominal = etNominal.getText().toString();

                if(lokasi.isEmpty() || tglAwal.isEmpty() || tglAkhir.isEmpty() || keterangan.isEmpty())
                {
                    Toast.makeText(pengajuanDetail.this, "Data tidak boleh kosong.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(displayName.isEmpty())
                {
                    Toast.makeText(pengajuanDetail.this, "File belum dipilih.", Toast.LENGTH_LONG).show();
                    btnChose.requestFocus();
                    return;
                }

                if(spinner_jenis.getSelectedItem().toString().equalsIgnoreCase("3"))
                {
                    Toast.makeText(pengajuanDetail.this, "Nominal harus diisi.", Toast.LENGTH_LONG).show();
                    spinner_jenis.requestFocus();
                    return;
                }

                System.out.println("NOMINAL : "+nominal);

                // Parsing any Media type file
                try {
                    PdfPathHolder = FilePath.getPath(pengajuanDetail.this, fileUri);
                } catch (Exception e){
                    Toast.makeText(pengajuanDetail.this, "Penyimpanan tidak dapat diakses.",Toast.LENGTH_LONG).show();
                }

                RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), new File(PdfPathHolder));
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("proposal", new File(PdfPathHolder).getName(), requestBody);

                RequestBody id_pengaju = RequestBody.create(MediaType.parse("text/plain"), pengajuan.getID_PENGAJU());
                RequestBody id_perusahaan = RequestBody.create(MediaType.parse("text/plain"), pengajuan.getID_PERUSAHAAN());
                RequestBody namaJenis = RequestBody.create(MediaType.parse("text/plain"), spinner_jenis.getSelectedItem().toString());
                RequestBody hp1 = RequestBody.create(MediaType.parse("text/plain"), pengajuan.getNO_HP_UTAMA());
                RequestBody hp2 = RequestBody.create(MediaType.parse("text/plain"), pengajuan.getNO_HP_ALT());
                RequestBody lembaga = RequestBody.create(MediaType.parse("text/plain"), pengajuan.getLEMBAGA_PENGAJU());
                RequestBody judul = RequestBody.create(MediaType.parse("text/plain"), pengajuan.getJUDUL_AJUAN());
                RequestBody tglA = RequestBody.create(MediaType.parse("text/plain"), tglAwal);
                RequestBody tglAk = RequestBody.create(MediaType.parse("text/plain"), tglAkhir);
                RequestBody lok = RequestBody.create(MediaType.parse("text/plain"), lokasi);
                RequestBody ket = RequestBody.create(MediaType.parse("text/plain"), keterangan);
                RequestBody nom = RequestBody.create(MediaType.parse("text/plain"), nominal);

                Call<ResponseBody> uploadAjuan = db.getDb().ajukanEvent(id_pengaju,id_perusahaan,namaJenis,hp1,hp2,lembaga,judul,tglA,tglAk,lok, fileToUpload,ket,nom);
                uploadAjuan.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(pengajuanDetail.this,"Berhasil mengajukan permintaan dana.",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(pengajuanDetail.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(pengajuanDetail.this,"Error "+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        Call<List<ModelJenisSponsor>> getSpinner = db.getDb().getJenisSponsor();
        getSpinner.enqueue(new Callback<List<ModelJenisSponsor>>() {
            @Override
            public void onResponse(Call<List<ModelJenisSponsor>> call, Response<List<ModelJenisSponsor>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    jenisAjuan.add(response.body().get(i).getNAMA_JENIS());
                }

                ArrayAdapter aa = new ArrayAdapter(pengajuanDetail.this,android.R.layout.simple_spinner_item,jenisAjuan);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                spinner_jenis.setAdapter(aa);
            }

            @Override
            public void onFailure(Call<List<ModelJenisSponsor>> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        switch (requestCode) {
            case 1001:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    fileUri = data.getData();
                    displayName = getNameFromContentUri(pengajuanDetail.this, fileUri);
                    namaFile.setText("Nama File : "+displayName);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setTglMulai(View view)
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        c.add(Calendar.DATE, -1);

//Set yesterday time milliseconds as date pickers minimum date
        DatePickerDialog datePickerDialog = new DatePickerDialog(pengajuanDetail.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ettglawal.setText(year+"-"+month+"-"+dayOfMonth);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();
    }

    public void setTglSelesai(View view)
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        c.add(Calendar.DATE, -1);

//Set yesterday time milliseconds as date pickers minimum date
        DatePickerDialog datePickerDialog = new DatePickerDialog(pengajuanDetail.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ettglakhir.setText(year+"-"+month+"-"+dayOfMonth);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();
    }

    public static String getNameFromContentUri(Context context, Uri contentUri){
        Cursor returnCursor = context.getContentResolver().query(contentUri, null, null, null, null);
        int nameColumnIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String fileName = returnCursor.getString(nameColumnIndex);
        return fileName;}
}