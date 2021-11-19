package com.example.duan1_cellhome.DAO;



import com.example.duan1_cellhome.Model.Top;

import java.util.List;

public interface IThongKe {
    List<Top> getTop();
    int getDoanhThu(String tuNgay,String denNgay);
}
