package com.example.duan1_cellhome.Model;

public class Thanhvien {
    public String matv ;
    public String hoTen,namSinh;
    public String tenTK;
    public String Mk;
    public int soDT;
    public int vaitro;

    public Thanhvien(){

    }

    public Thanhvien(String matv, String hoTen, String namSinh, String tenTK, String mk, int soDT, int vaitro) {
        this.matv = matv;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.tenTK = tenTK;
        Mk = mk;
        this.soDT = soDT;
        this.vaitro = vaitro;
    }

    public String getMatv() {
        return matv;
    }

    public void setMatv(String matv) {
        this.matv = matv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMk() {
        return Mk;
    }

    public void setMk(String mk) {
        Mk = mk;
    }

    public int getSoDT() {
        return soDT;
    }

    public void setSoDT(int soDT) {
        this.soDT = soDT;
    }

    public int getVaitro() {
        return vaitro;
    }

    public void setVaitro(int vaitro) {
        this.vaitro = vaitro;
    }
}
