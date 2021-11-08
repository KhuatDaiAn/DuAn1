package com.example.duan1_cellhome.DAO;

import com.example.duan1_cellhome.Model.Thanhvien;

import java.util.List;

public interface IThanhVienDAO {
    List<Thanhvien>getAll();
    void insert(Thanhvien thanhvien);
    void update(Thanhvien thanhvien);
    void delete(String maTV);
    void updateMK(Thanhvien thanhvien);
}
