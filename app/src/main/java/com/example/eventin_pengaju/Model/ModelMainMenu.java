package com.example.eventin_pengaju.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.eventin_pengaju.Module.DB_Init;

public class ModelMainMenu implements Parcelable {
    String ID_PERUSAHAAN, NAMA_PERUSAHAAN, PROFIL_SINGKAT, VISI, WEBSITE_PERUSAHAAN, BIDANG_PERUSAHAAN, LOGO_PERUSAHAAN;
    String getGambarPerusahaan;
    public String getID_PERUSAHAAN() {
        return ID_PERUSAHAAN;
    }

    public String getNAMA_PERUSAHAAN() {
        return NAMA_PERUSAHAAN;
    }

    public String getPROFIL_SINGKAT() {
        return PROFIL_SINGKAT;
    }

    public String getVISI() {
        return VISI;
    }

    public String getWEBSITE_PERUSAHAAN() {
        return WEBSITE_PERUSAHAAN;
    }

    public String getBIDANG_PERUSAHAAN() {
        return BIDANG_PERUSAHAAN;
    }

    public String getLOGO_PERUSAHAAN() {
        return LOGO_PERUSAHAAN;
    }

    public String getGetGambarPerusahaan() {
        return DB_Init.DB_BASE_URL +"MDM_EVENTIN/logo/"+getLOGO_PERUSAHAAN();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID_PERUSAHAAN);
        dest.writeString(this.NAMA_PERUSAHAAN);
        dest.writeString(this.PROFIL_SINGKAT);
        dest.writeString(this.VISI);
        dest.writeString(this.WEBSITE_PERUSAHAAN);
        dest.writeString(this.BIDANG_PERUSAHAAN);
        dest.writeString(this.LOGO_PERUSAHAAN);
        dest.writeString(this.getGambarPerusahaan);
    }

    public void readFromParcel(Parcel source) {
        this.ID_PERUSAHAAN = source.readString();
        this.NAMA_PERUSAHAAN = source.readString();
        this.PROFIL_SINGKAT = source.readString();
        this.VISI = source.readString();
        this.WEBSITE_PERUSAHAAN = source.readString();
        this.BIDANG_PERUSAHAAN = source.readString();
        this.LOGO_PERUSAHAAN = source.readString();
        this.getGambarPerusahaan = source.readString();
    }

    public ModelMainMenu() {
    }

    protected ModelMainMenu(Parcel in) {
        this.ID_PERUSAHAAN = in.readString();
        this.NAMA_PERUSAHAAN = in.readString();
        this.PROFIL_SINGKAT = in.readString();
        this.VISI = in.readString();
        this.WEBSITE_PERUSAHAAN = in.readString();
        this.BIDANG_PERUSAHAAN = in.readString();
        this.LOGO_PERUSAHAAN = in.readString();
        this.getGambarPerusahaan = in.readString();
    }

    public static final Parcelable.Creator<ModelMainMenu> CREATOR = new Parcelable.Creator<ModelMainMenu>() {
        @Override
        public ModelMainMenu createFromParcel(Parcel source) {
            return new ModelMainMenu(source);
        }

        @Override
        public ModelMainMenu[] newArray(int size) {
            return new ModelMainMenu[size];
        }
    };
}
