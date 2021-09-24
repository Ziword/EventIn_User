package com.example.eventin_pengaju.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelPengajuan implements Parcelable {
    String ID_AJUAN, ID_PENGAJU, ID_PERUSAHAAN, ID_STATUS, ID_JENIS, NO_HP_UTAMA, NO_HP_ALT, LEMBAGA_PENGAJU, JUDUL_AJUAN, TGL_MULAI_ACARA, TGL_AKHIR_ACARA, LOKASI_ACARA, PROPOSAL_PENGAJU, TGL_AJUAN, KETERANGAN_AJUAN, NOMINAL_AJUAN;

    public String getID_AJUAN() {
        return ID_AJUAN;
    }

    public void setID_AJUAN(String ID_AJUAN) {
        this.ID_AJUAN = ID_AJUAN;
    }

    public String getID_PENGAJU() {
        return ID_PENGAJU;
    }

    public void setID_PENGAJU(String ID_PENGAJU) {
        this.ID_PENGAJU = ID_PENGAJU;
    }

    public String getID_PERUSAHAAN() {
        return ID_PERUSAHAAN;
    }

    public void setID_PERUSAHAAN(String ID_PERUSAHAAN) {
        this.ID_PERUSAHAAN = ID_PERUSAHAAN;
    }

    public String getID_STATUS() {
        return ID_STATUS;
    }

    public void setID_STATUS(String ID_STATUS) {
        this.ID_STATUS = ID_STATUS;
    }

    public String getID_JENIS() {
        return ID_JENIS;
    }

    public void setID_JENIS(String ID_JENIS) {
        this.ID_JENIS = ID_JENIS;
    }

    public String getNO_HP_UTAMA() {
        return NO_HP_UTAMA;
    }

    public void setNO_HP_UTAMA(String NO_HP_UTAMA) {
        this.NO_HP_UTAMA = NO_HP_UTAMA;
    }

    public String getNO_HP_ALT() {
        return NO_HP_ALT;
    }

    public void setNO_HP_ALT(String NO_HP_ALT) {
        this.NO_HP_ALT = NO_HP_ALT;
    }

    public String getLEMBAGA_PENGAJU() {
        return LEMBAGA_PENGAJU;
    }

    public void setLEMBAGA_PENGAJU(String LEMBAGA_PENGAJU) {
        this.LEMBAGA_PENGAJU = LEMBAGA_PENGAJU;
    }

    public String getJUDUL_AJUAN() {
        return JUDUL_AJUAN;
    }

    public void setJUDUL_AJUAN(String JUDUL_AJUAN) {
        this.JUDUL_AJUAN = JUDUL_AJUAN;
    }

    public String getTGL_MULAI_ACARA() {
        return TGL_MULAI_ACARA;
    }

    public void setTGL_MULAI_ACARA(String TGL_MULAI_ACARA) {
        this.TGL_MULAI_ACARA = TGL_MULAI_ACARA;
    }

    public String getTGL_AKHIR_ACARA() {
        return TGL_AKHIR_ACARA;
    }

    public void setTGL_AKHIR_ACARA(String TGL_AKHIR_ACARA) {
        this.TGL_AKHIR_ACARA = TGL_AKHIR_ACARA;
    }

    public String getLOKASI_ACARA() {
        return LOKASI_ACARA;
    }

    public void setLOKASI_ACARA(String LOKASI_ACARA) {
        this.LOKASI_ACARA = LOKASI_ACARA;
    }

    public String getPROPOSAL_PENGAJU() {
        return PROPOSAL_PENGAJU;
    }

    public void setPROPOSAL_PENGAJU(String PROPOSAL_PENGAJU) {
        this.PROPOSAL_PENGAJU = PROPOSAL_PENGAJU;
    }

    public String getTGL_AJUAN() {
        return TGL_AJUAN;
    }

    public void setTGL_AJUAN(String TGL_AJUAN) {
        this.TGL_AJUAN = TGL_AJUAN;
    }

    public String getKETERANGAN_AJUAN() {
        return KETERANGAN_AJUAN;
    }

    public void setKETERANGAN_AJUAN(String KETERANGAN_AJUAN) {
        this.KETERANGAN_AJUAN = KETERANGAN_AJUAN;
    }

    public String getNOMINAL_AJUAN() {
        return NOMINAL_AJUAN;
    }

    public void setNOMINAL_AJUAN(String NOMINAL_AJUAN) {
        this.NOMINAL_AJUAN = NOMINAL_AJUAN;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID_AJUAN);
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
        dest.writeString(this.TGL_AJUAN);
        dest.writeString(this.KETERANGAN_AJUAN);
        dest.writeString(this.NOMINAL_AJUAN);
    }

    public void readFromParcel(Parcel source) {
        this.ID_AJUAN = source.readString();
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
        this.TGL_AJUAN = source.readString();
        this.KETERANGAN_AJUAN = source.readString();
        this.NOMINAL_AJUAN = source.readString();
    }

    public ModelPengajuan() {
    }

    protected ModelPengajuan(Parcel in) {
        this.ID_AJUAN = in.readString();
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
        this.TGL_AJUAN = in.readString();
        this.KETERANGAN_AJUAN = in.readString();
        this.NOMINAL_AJUAN = in.readString();
    }

    public static final Parcelable.Creator<ModelPengajuan> CREATOR = new Parcelable.Creator<ModelPengajuan>() {
        @Override
        public ModelPengajuan createFromParcel(Parcel source) {
            return new ModelPengajuan(source);
        }

        @Override
        public ModelPengajuan[] newArray(int size) {
            return new ModelPengajuan[size];
        }
    };
}
