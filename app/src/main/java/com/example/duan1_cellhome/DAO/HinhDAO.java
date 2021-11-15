package com.example.duan1_cellhome.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_cellhome.Database.Database;
import com.example.duan1_cellhome.Model.Hinh;

import java.util.ArrayList;
import java.util.List;

public class HinhDAO {
    Database mydatabase;

    public HinhDAO(Context context){
        mydatabase=new Database(context);
    }


    public List<Hinh> getHinh(String manhadat) {
        List<Hinh> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM Hinh WHERE maNhaDat = ?",new String[]{manhadat});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maHinh=cursor.getString(0);
            String maNhaDat=cursor.getString(1);
            byte[] hinh=cursor.getBlob(2);
            Hinh hinh1=new Hinh(maHinh,maNhaDat,hinh);
            list.add(hinh1);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public Hinh get1Hinh(String manhadat) {
        List<Hinh> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM Hinh WHERE maNhaDat = ?",new String[]{manhadat});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maHinh=cursor.getString(0);
            String maNhaDat=cursor.getString(1);
            byte[] hinh=cursor.getBlob(2);
            Hinh hinh1=new Hinh(maHinh,maNhaDat,hinh);
            list.add(hinh1);
            cursor.moveToNext();
        }
        cursor.close();
        return list.get(0);
    }


    public void insert(Hinh hinh) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("hinh",hinh.getHinh());
        values.put("maNhaDat",hinh.getMaNhaDat());
        database.insert("Hinh",null,values);

    }

    public void delete(String maNhaDat) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();

        String[] params=new String[]{maNhaDat};
        database.delete("Hinh","maNhaDat = ?",params);

    }


}
