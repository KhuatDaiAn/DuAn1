package com.example.duan1_cellhome.Model;

public class Hinh {
    byte[] hinh;
    String maNhaDat;

    public Hinh(byte[] hinh, String maNhaDat) {
        this.hinh = hinh;
        this.maNhaDat = maNhaDat;
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
}
