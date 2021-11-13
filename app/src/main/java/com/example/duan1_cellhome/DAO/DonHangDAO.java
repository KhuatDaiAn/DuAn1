package com.example.duan1_cellhome.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_cellhome.Database.Database;
import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.Model.NhaDat;

import java.util.ArrayList;
import java.util.List;

public class DonHangDAO implements IDonHang{
    Database mydatabase;

    public DonHangDAO(Context context){
        mydatabase=new Database(context);
    }

    @Override
    public List<DonHang> getDonHang() {
        List<DonHang> donHangList = new ArrayList<>();
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM donHang", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maDonHang = cursor.getString(0);
            String maThanhVien = cursor.getString(1);
            String maNhaDat = cursor.getString(2);
            int soDTNM = cursor.getInt(3);
            String tenGTND = cursor.getString(4);
            byte[] hinh = cursor.getBlob(5);
            String diaChiND = cursor.getString(6);
            int giaTien = cursor.getInt(7);
            int trangThai = cursor.getInt(8);

            donHangList.add( new DonHang(maDonHang, maThanhVien, maNhaDat, soDTNM, tenGTND, hinh, diaChiND, giaTien, trangThai));
            cursor.moveToNext();
        }
        cursor.close();
        return donHangList;
    }

    @Override
    public void insert(DonHang donHang) {
            SQLiteDatabase database=mydatabase.getReadableDatabase();
            ContentValues values=new ContentValues();
            values.put("maDonHang",donHang.getMaDonHang());
            values.put("maTV",donHang.getMaTV());
            values.put("maNhaDat",donHang.getMaNhaDat());
            values.put("soDTNM",donHang.getSoDTNM());
            values.put("tenGTND",donHang.getTenGTND());
            values.put("hinhND",donHang.getHinhND());
            values.put("diaChiND",donHang.getDiaChiND());
            values.put("giaTienND",donHang.getGiaTienND());
            values.put("trangThai",donHang.getTrangThai());
            database.insert("donHang",null,values);
    }



}
