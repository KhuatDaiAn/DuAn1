package com.example.duan1_cellhome.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_cellhome.Database.Database;
import com.example.duan1_cellhome.Model.Thanhvien;

import java.util.ArrayList;
import java.util.List;

public class ThanhvienDao implements IThanhVienDAO {
    Database myDatabase;


    @Override
    public List<Thanhvien> getAll() {
        List<Thanhvien>thanhvienList = new ArrayList<>();
        SQLiteDatabase database = myDatabase.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from ThanhVien where vaiTro==1",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maTV = cursor.getString(0);
            String hoTen = cursor.getString(1);
            String tenTK = cursor.getString(2);
            String MK = cursor.getString(3);
            String namSinh = cursor.getString(4);
            int soDT = cursor.getInt(5);
            int VaiTro = cursor.getInt(6);
            Thanhvien thanhvien = new Thanhvien(maTV,hoTen,tenTK,MK,namSinh,soDT,VaiTro);
            thanhvienList.add(thanhvien);
            cursor.moveToNext();
        }
        cursor.close();
        return thanhvienList;
    }

    @Override
    public void insert(Thanhvien thanhvien) {
        SQLiteDatabase database = myDatabase.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTV",thanhvien.getMatv());
        values.put("hoTen",thanhvien.hoTen);
        values.put("tenTK",thanhvien.tenTK);
        values.put("matKhau",thanhvien.Mk);
        values.put(" namSinh",thanhvien.namSinh);
        values.put("soDT",thanhvien.soDT);
        values.put(" vaiTro",thanhvien.vaitro);
        database.insert("ThanhVien",null,values);

    }

    @Override
    public void update(Thanhvien thanhvien) {
        SQLiteDatabase database = myDatabase.getReadableDatabase();
        ContentValues values = new ContentValues();
        String []params = new String[]{thanhvien.getMatv()};
        values.put("hoTen",thanhvien.hoTen);
        values.put("tenTK",thanhvien.tenTK);
        values.put("matKhau",thanhvien.Mk);
        values.put(" namSinh",thanhvien.namSinh);
        values.put("soDT",thanhvien.soDT);
        values.put(" vaiTro",thanhvien.vaitro);
        database.update("ThanhVien",values,"maTV=?",params);



    }

    @Override
    public void delete(String maTV) {
        SQLiteDatabase database = myDatabase.getReadableDatabase();
        String [] params = new String[]{maTV};
        database.delete("ThanhVien","maTV=?",params);

    }

    @Override
    public void updateMK(Thanhvien thanhvien) {
        SQLiteDatabase database = myDatabase.getReadableDatabase();
        ContentValues values = new ContentValues();
        String []params = new String[]{thanhvien.getMatv()};
        values.put("matKhau",thanhvien.getMk());
        database.update("ThanhVien",values,"maTV=?",params);
    }
}
