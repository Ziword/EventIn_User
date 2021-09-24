package com.example.eventin_pengaju.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.eventin_pengaju.Module.DB_Init;

public class ModelEviden implements Parcelable {
    String ID_EVIDEN, ID_AJUAN, GAMBAR_EVIDEN, KETERANGAN_GAMBAR;
    String ID_PENGAJU, ID_PERUSAHAAN, ID_STATUS, ID_JENIS, NO_HP_UTAMA, NO_HP_ALT,
    LEMBAGA_PENGAJU, JUDUL_AJUAN, TGL_MULAI_ACARA, TGL_AKHIR_ACARA, LOKASI_ACARA, PROPOSAL_PENGAJU, KETERANGAN_AJUAN, NOMINAL_AJUAN;

    String alamatGambar;

    public String getID_EVIDEN() {
        return ID_EVIDEN;
    }

    public String getID_AJUAN() {
        return ID_AJUAN;
    }

    public String getGAMBAR_EVIDEN() {
        return GAMBAR_EVIDEN;
    }

    public String getKETERANGAN_GAMBAR() {
        return KETERANGAN_GAMBAR;
    }

    public String getID_PENGAJU() {
        return ID_PENGAJU;
    }

    public String getID_PERUSAHAAN() {
        return ID_PERUSAHAAN;
    }

    public String getID_STATUS() {
        return ID_STATUS;
    }

    public String getID_JENIS() {
        return ID_JENIS;
    }

    public String getNO_HP_UTAMA() {
        return NO_HP_UTAMA;
    }

    public String getNO_HP_ALT() {
        return NO_HP_ALT;
    }

    public String getLEMBAGA_PENGAJU() {
        return LEMBAGA_PENGAJU;
    }

    public String getJUDUL_AJUAN() {
        return JUDUL_AJUAN;
    }

    public String getTGL_MULAI_ACARA() {
        return TGL_MULAI_ACARA;
    }

    public String getTGL_AKHIR_ACARA() {
        return TGL_AKHIR_ACARA;
    }

    public String getLOKASI_ACARA() {
        return LOKASI_ACARA;
    }

    public String getPROPOSAL_PENGAJU() {
        return PROPOSAL_PENGAJU;
    }

    public String getKETERANGAN_AJUAN() {
        return KETERANGAN_AJUAN;
    }

    public String getNOMINAL_AJUAN() {
        return NOMINAL_AJUAN;
    }

    public String getAlamatGambar() {
        return DB_Init.DB_BASE_URL +"MDM_EVENTIN/storage/eviden_pengaju/"+getGAMBAR_EVIDEN();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID_EVIDEN);
        dest.writeString(this.ID_AJUAN);
        dest.writeString(this.GAMBAR_EVIDEN);
        dest.writeString(this.KETERANGAN_GAMBAR);
        dest.writeString(this.ID_PENGAJU);
        dest.writeString(this.ID_PERUSAHAAN);
        dest.writeString(this.ID_STATUS);
        dest.writeString(this.ID_JENIS);
        dest.writeString(this.NO_HP_UTAMA);
        dest.writeString(this.NO_HP_ALT);
        dest.writeString(this.LEMBAGA_PENGAJU);
        dest.writeString(this.JUDUL_AJUAN);
        dest.writeString(this.TGL_MULAI_ACARA);
        dest.writeString(this.TGL_AKHIR_ACARA);
        dest.writeString(this.LOKASI_ACARA);
        dest.writeString(this.PROPOSAL_PENGAJU);
        dest.writeString(this.KETERANGAN_AJUAN);
        dest.writeString(this.NOMINAL_AJUAN);
        dest.writeString(this.alamatGambar);
    }

    public void readFromParcel(Parcel source) {
        this.ID_EVIDEN = source.readString();
        this.ID_AJUAN = source.readString();
        this.GAMBAR_EVIDEN = source.readString();
        this.KETERANGAN_GAMBAR = source.readString();
        this.ID_PENGAJU = source.readString();
        this.ID_PERUSAHAAN = source.readString();
        this.ID_STATUS = source.readString();
        this.ID_JENIS = source.readString();
        this.NO_HP_UTAMA = source.readString();
        this.NO_HP_ALT = source.readString();
        this.LEMBAGA_PENGAJU = source.readString();
        this.JUDUL_AJUAN = source.readString();
        this.TGL_MULAI_ACARA = source.readString();
        this.TGL_AKHIR_ACARA = source.readString();
        this.LOKASI_ACARA = source.readString();
        this.PROPOSAL_PENGAJU = source.readString();
        this.KETERANGAN_AJUAN = source.readString();
        this.NOMINAL_AJUAN = source.readString();
        this.alamatGambar = source.readString();
    }

    public ModelEviden() {
    }

    protected ModelEviden(Parcel in) {
        this.ID_EVIDEN = in.readString();
        this.ID_AJUAN = in.readString();
        this.GAMBAR_EVIDEN = in.readString();
        this.KETERANGAN_GAMBAR = in.readString();
        this.ID_PENGAJU = in.readString();
        this.ID_PERUSAHAAN = in.readString();
        this.ID_STATUS = in.readString();
        this.ID_JENIS = in.readString();
        this.NO_HP_UTAMA = in.readString();
        this.NO_HP_ALT = in.readString();
        this.LEMBAGA_PENGAJU = in.readString();
        this.JUDUL_AJUAN = in.readString();
        this.TGL_MULAI_ACARA = in.readString();
        this.TGL_AKHIR_ACARA = in.readString();
        this.LOKASI_ACARA = in.readString();
        this.PROPOSAL_PENGAJU = in.readString();
        this.KETERANGAN_AJUAN = in.readString();
        this.NOMINAL_AJUAN = in.readString();
        this.alamatGambar = in.readString();
    }

    public static final Parcelable.Creator<ModelEviden> CREATOR = new Parcelable.Creator<ModelEviden>() {
        @Override
        public ModelEviden createFromParcel(Parcel source) {
            return new ModelEviden(source);
        }

        @Override
        public ModelEviden[] newArray(int size) {
            return new ModelEviden[size];
        }
    };
}
