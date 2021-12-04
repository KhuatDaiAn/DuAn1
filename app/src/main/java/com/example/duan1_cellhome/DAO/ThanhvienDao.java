package com.example.duan1_cellhome.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_cellhome.Database.Database;
import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.Model.Thanhvien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThanhvienDao implements IThanhVienDAO {
    Database myDatabase;

    public ThanhvienDao(Context context) {
        myDatabase = new Database(context);

    }

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
    public Thanhvien getDuLieu(String username) {
        Thanhvien thanhvien = null;
        SQLiteDatabase database = myDatabase.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from ThanhVien where tenTK=?",new String[]{username});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String maTV = cursor.getString(0);
            String hoTen = cursor.getString(1);
            String tenTK = cursor.getString(2);
            String MK = cursor.getString(3);
            String namSinh = cursor.getString(4);
            int soDT = cursor.getInt(5);
            int VaiTro = cursor.getInt(6);
            thanhvien= new Thanhvien(maTV,hoTen,tenTK,MK,namSinh,soDT,VaiTro);
            cursor.moveToNext();
        }
        cursor.close();
        return thanhvien;
    }

    @Override
    public void insert(Thanhvien thanhvien) {
        SQLiteDatabase database = myDatabase.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTV",thanhvien.getMatv());
        values.put("hoTen",thanhvien.getHoTen());
        values.put("tenTK",thanhvien.getTenTK());
        values.put("matKhau",thanhvien.getMk());
        values.put("namSinh",thanhvien.getNamSinh());
        values.put("soDT",thanhvien.getSoDT());
        values.put("vaiTro",thanhvien.getVaitro());
        database.insert("ThanhVien",null,values);

    }

    @Override
    public void update(Thanhvien thanhvien) {
        SQLiteDatabase database = myDatabase.getReadableDatabase();
        ContentValues values = new ContentValues();
        String []params = new String[]{thanhvien.getMatv()};
        values.put("hoTen",thanhvien.hoTen);
        values.put("namSinh",thanhvien.namSinh);
        values.put("soDT",thanhvien.soDT);
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

    public Boolean login(String tenTK, String matKhau) {
        SQLiteDatabase database=myDatabase.getReadableDatabase();
        String sql = "SELECT * FROM ThanhVien WHERE tenTK = ? AND matKhau = ?";
        Cursor cursor=database.rawQuery(sql,new String[]{tenTK,matKhau});
        int count=cursor.getCount();
        cursor.close();
        return count>0;
    }
    public Boolean kiemTraDangKy(String tenTK) {
        SQLiteDatabase database=myDatabase.getReadableDatabase();
        String sql = "SELECT * FROM ThanhVien WHERE tenTK = ? ";
        Cursor cursor=database.rawQuery(sql,new String[]{tenTK});
        int count=cursor.getCount();
        cursor.close();
        return count>0;
    }

}
