package com.example.eventin_pengaju.Module;

import com.example.eventin_pengaju.Model.ModelAjuan;
import com.example.eventin_pengaju.Model.ModelEviden;
import com.example.eventin_pengaju.Model.ModelJenisSponsor;
import com.example.eventin_pengaju.Model.ModelMainMenu;
import com.example.eventin_pengaju.Model.ModelPengaju;
import com.example.eventin_pengaju.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface EventInDB
{
    @POST("/MDM_EVENTIN/ControllerAndroid/pendaftaranPengaju")
    @FormUrlEncoded
    Call<ModelPengaju> pendaftaranPengaju(@Field("namaPengaju") String namaPengaju, @Field("emailPengaju") String emailPengaju, @Field("pwPengaju") String pwPengaju);

    @POST("/MDM_EVENTIN/ControllerAndroid/cekLoginPengaju")
    @FormUrlEncoded
    Call<ModelPengaju> cekLoginPengaju(@Field("emailPengaju") String emailPengaju, @Field("pwPengaju") String pwPengaju);

    @GET("/MDM_EVENTIN/ControllerAndroid/getPerusahaan")
    Call<ArrayList<ModelMainMenu>> getPerusahaanMainmenu();

    @POST("/MDM_EVENTIN/ControllerAndroid/getNotifPengaju")
    @FormUrlEncoded
    Call<ModelPengaju> getMainMenuPengaju(@Field("idPengaju")String idPengaju);

    @POST("/MDM_EVENTIN/ControllerAndroid/getWawancara")
    @FormUrlEncoded
    Call<ArrayList<ModelAjuan>> getWawancara(@Field("idPengaju") String idPengaju);

    @Multipart
    @POST("/MDM_EVENTIN/ControllerAndroid/tambahAjuan")
    Call<ResponseBody> ajukanEvent(@Part("idPengaju") RequestBody ID_PENGAJU, @Part("idPerusahaan")RequestBody ID_PERUSAHAAN, @Part("namaJenis")RequestBody namaJenis,
                                   @Part("nHpUtama") RequestBody nHpUtama, @Part("nHpAlt")RequestBody nHpAlt, @Part("lembagaPengaju")RequestBody lembagaPengaju,
                                   @Part("judulAjuan")RequestBody judulAjuan, @Part("tglMacara") RequestBody tglMacara, @Part("tglAacara")RequestBody tglAacara,
                                   @Part("lokasiAcara") RequestBody lokasiAcara, @Part MultipartBody.Part proposal, @Part("keterangan") RequestBody keterangan,
                                   @Part("nominalAjuan")RequestBody nominalAjuan);

    @GET("/MDM_EVENTIN/ControllerAndroid/getjenisAjuan")
    Call<List<ModelJenisSponsor>> getJenisSponsor();

    @POST("MDM_EVENTIN/ControllerAndroid/getEviden")
    @FormUrlEncoded
    Call<ArrayList<ModelEviden>> getEviden(@Field("idPengaju") String idPengaju, @Field("sts")String sts);

    @Multipart
    @POST("/MDM_EVENTIN/ControllerAndroid/updateEviden")
    Call<ResponseBody> updateEviden(@Part("idEviden") RequestBody idEviden, @Part MultipartBody.Part eviden);

    @POST("/MDM_EVENTIN/ControllerAndroid/getHistory")
    @FormUrlEncoded
    Call<ArrayList<ModelAjuan>> getAjuan(@Field("idPengaju") String  idPengaju);
}
