package com.example.duan1_cellhome.DAO;

import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.Model.NhaDat;

import java.util.List;

public interface IDonHang {
    List<DonHang> getDonHang();
    void insert(DonHang donHang);
    void delete(String maDonHang);
    DonHang getMaDonHang(String maDonHang);
    void updateTrangThai(DonHang donHang);
}
