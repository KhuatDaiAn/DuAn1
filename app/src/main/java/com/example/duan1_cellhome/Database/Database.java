package com.example.duan1_cellhome.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan1_cellhome.R;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "BDSDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        String sql="CREATE TABLE  ThanhVien(maTV TEXT PRIMARY KEY NOT NULL, hoTen TEXT NOT NULL, tenTK TEXT NOT NULL, matKhau TEXT, namSinh TEXT, soDT INTEGER, vaiTro INTEGER)";
        db.execSQL(sql);
        sql="CREATE TABLE  NhaDat(maNhaDat TEXT PRIMARY KEY NOT NULL, tenGT TEXT NOT NULL, hinh BLOB REFERENCES Hinh(hinh), tinhThanh TEXT,ngayDang DATE, diaChi TEXT, giaTien INTEGER, dienTich TEXT, moTa TEXT, vaitro INTEGER)";
        db.execSQL(sql);
        sql="CREATE TABLE  Hinh(maHinh TEXT PRIMARY KEY,maNhaDat TEXT REFERENCES NhaDat(maNhaDat),hinh BLOB )";
        db.execSQL(sql);
        sql="CREATE TABLE  donHang(maDonHang TEXT PRIMARY KEY NOT NULL, " +
                "maTV TEXT REFERENCES ThanhVien(maTV)," +
                "maNhaDat TEXT REFERENCES NhaDat(maNhaDat)," +
                "soDTNM INTEGER," +
                "tenGTND TEXT NOT NULL," +
                "hinhND BLOB," +
                "diaChiND TEXT NOT NULL, giaTienND INTEGER, trangThai INTEGER)";
        db.execSQL(sql);

        db.execSQL("INSERT INTO ThanhVien VALUES('abc','Khuat Dai An','an1','123','1999',0198139131,1)");
        db.execSQL("INSERT INTO ThanhVien VALUES('abc1','Khuat Dai An','an2','123','1999',0198139131,1)");
        db.execSQL("INSERT INTO ThanhVien VALUES('abc2','Khuat Dai An','an3','123','1999',0198139131,1)");
        db.execSQL("INSERT INTO ThanhVien VALUES('1','Khuat Dai An','admin','123','1999',0198139131,0)");

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
