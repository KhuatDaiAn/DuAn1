package com.example.duan1_cellhome.Model;

public class Top {
    String TenTinh;
    String soLuong;

    public Top(String tenTinh, String soLuong) {
        TenTinh = tenTinh;
        this.soLuong = soLuong;
    }

    public String getTenTinh() {
        return TenTinh;
    }

    public void setTenTinh(String tenTinh) {
        TenTinh = tenTinh;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }
}
