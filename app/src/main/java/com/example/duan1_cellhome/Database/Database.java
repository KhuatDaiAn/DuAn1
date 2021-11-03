package com.example.duan1_cellhome.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "BDSDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        String sql="CREATE TABLE  ThanhVien(maTV TEXT PRIMARY KEY, hoTen TEXT NOT NULL, tenTK TEXT NOT NULL, matKhau TEXT, namSinh TEXT, soDT INTEGER, vaiTro INTEGER)";
        db.execSQL(sql);
        sql="CREATE TABLE  NhaDat(maNhaDat TEXT PRIMARY KEY, tenGT TEXT NOT NULL, hinh BLOB REFERENCES Hinh(hinh), tinhThanh TEXT,ngayDang DATE, diaChi TEXT, giaTien INTEGER, dienTich TEXT, moTa TEXT)";
        db.execSQL(sql);
        sql="CREATE TABLE  Hinh(hinh BLOB PRIMARY KEY)";
        db.execSQL(sql);
        sql="CREATE TABLE  donHang(maDonHang TEXT PRIMARY KEY, " +
                "maTV TEXT REFERENCES ThanhVien(maTV)," +
                "maNhaDat TEXT REFERENCES NhaDat(maNhaDat)," +
                "soDTNM INTEGER," +
                "tenGTND TEXT NOT NULL," +
                "hinhND BLOB," +
                "diaChiND TEXT NOT NULL, giaTienND INTEGER, trangThai INTEGER)";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS ThanhVien");
        db.execSQL("DROP TABLE IF EXISTS NhaDat");
        db.execSQL("DROP TABLE IF EXISTS Hinh");
        db.execSQL("DROP TABLE IF EXISTS donHang");
        onCreate(db);
    }
}
