package com.example.duan1_cellhome.Model;

public class DonHang {
    String maDonHang;
    String maTV;
    String maNhaDat;
    int soDTNM;
    String tenGTND;
    byte[] hinhND;
    String diaChiND;
    int giaTienND;
    int trangThai;

    public DonHang(String maDonHang, String maTV, String maNhaDat, int soDTNM, String tenGTND, byte[] hinhND, String diaChiND, int giaTienND, int trangThai) {
        this.maDonHang = maDonHang;
        this.maTV = maTV;
        this.maNhaDat = maNhaDat;
        this.soDTNM = soDTNM;
        this.tenGTND = tenGTND;
        this.hinhND = hinhND;
        this.diaChiND = diaChiND;
        this.giaTienND = giaTienND;
        this.trangThai = trangThai;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public String getMaTV() {
        return maTV;
    }

    public String getMaNhaDat() {
        return maNhaDat;
    }

    public int getSoDTNM() {
        return soDTNM;
    }

    public String getTenGTND() {
        return tenGTND;
    }

    public byte[] getHinhND() {
        return hinhND;
    }

    public String getDiaChiND() {
        return diaChiND;
    }

    public int getGiaTienND() {
        return giaTienND;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setMaTV(String maTV) {
        this.maTV = maTV;
    }

    public void setMaNhaDat(String maNhaDat) {
        this.maNhaDat = maNhaDat;
    }

    public void setSoDTNM(int soDTNM) {
        this.soDTNM = soDTNM;
    }

    public void setTenGTND(String tenGTND) {
        this.tenGTND = tenGTND;
    }

    public void setHinhND(byte[] hinhND) {
        this.hinhND = hinhND;
    }

    public void setDiaChiND(String diaChiND) {
        this.diaChiND = diaChiND;
    }

    public void setGiaTienND(int giaTienND) {
        this.giaTienND = giaTienND;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
