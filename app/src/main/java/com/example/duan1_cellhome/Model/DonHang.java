package com.example.duan1_cellhome.Model;

import java.util.Date;

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
    Date ngay;

    public DonHang(String maDonHang, String maTV, String maNhaDat, int soDTNM, String tenGTND, byte[] hinhND, String diaChiND, int giaTienND, int trangThai, Date ngay) {
        this.maDonHang = maDonHang;
        this.maTV = maTV;
        this.maNhaDat = maNhaDat;
        this.soDTNM = soDTNM;
        this.tenGTND = tenGTND;
        this.hinhND = hinhND;
        this.diaChiND = diaChiND;
        this.giaTienND = giaTienND;
        this.trangThai = trangThai;
        this.ngay = ngay;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getMaTV() {
        return maTV;
    }

    public void setMaTV(String maTV) {
        this.maTV = maTV;
    }

    public String getMaNhaDat() {
        return maNhaDat;
    }

    public void setMaNhaDat(String maNhaDat) {
        this.maNhaDat = maNhaDat;
    }

    public int getSoDTNM() {
        return soDTNM;
    }

    public void setSoDTNM(int soDTNM) {
        this.soDTNM = soDTNM;
    }

    public String getTenGTND() {
        return tenGTND;
    }

    public void setTenGTND(String tenGTND) {
        this.tenGTND = tenGTND;
    }

    public byte[] getHinhND() {
        return hinhND;
    }

    public void setHinhND(byte[] hinhND) {
        this.hinhND = hinhND;
    }

    public String getDiaChiND() {
        return diaChiND;
    }

    public void setDiaChiND(String diaChiND) {
        this.diaChiND = diaChiND;
    }

    public int getGiaTienND() {
        return giaTienND;
    }

    public void setGiaTienND(int giaTienND) {
        this.giaTienND = giaTienND;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
}
