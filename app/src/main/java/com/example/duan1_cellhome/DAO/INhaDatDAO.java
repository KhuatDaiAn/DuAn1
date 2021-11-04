package com.example.duan1_cellhome.DAO;

import com.example.duan1_cellhome.Model.NhaDat;

import java.util.List;

public interface INhaDatDAO {
    List<NhaDat> getNha();
    List<NhaDat> getDat();
    void insert(NhaDat nhaDat);
    void update(NhaDat nhaDat);
    void delete(String maNhaDat);
}
