package com.example.duan1_cellhome.DAO;

import com.example.duan1_cellhome.Model.DonHang;

import java.util.List;

public interface IDonHang {
    List<DonHang> getDonHang();
    void insert(DonHang donHang);
}
