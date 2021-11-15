package com.example.duan1_cellhome.Model;

public class Hinh {
    String maHinh;
    String maNhaDat;
    byte[] hinh;


    public Hinh(String maHinh, String maNhaDat, byte[] hinh) {
        this.maHinh = maHinh;
        this.maNhaDat = maNhaDat;
        this.hinh = hinh;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getMaNhaDat() {
        return maNhaDat;
    }

    public void setMaNhaDat(String maNhaDat) {
        this.maNhaDat = maNhaDat;
    }


    public String getMaHinh() {
        return maHinh;
    }

    public void setMaHinh(String maHinh) {
        this.maHinh = maHinh;
    }
}
