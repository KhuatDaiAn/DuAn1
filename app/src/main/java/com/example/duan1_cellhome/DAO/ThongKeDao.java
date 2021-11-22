package com.example.duan1_cellhome.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.duan1_cellhome.Database.Database;
import com.example.duan1_cellhome.Model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDao implements IThongKe{
    Database mydatabase;
    Context context;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    public ThongKeDao(Context context){
        this.context=context;
        mydatabase=new Database(context);
    }


    @Override
    public List<Top> getTop() {
        List<Top> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT tinhThanh,count(tinhThanh) as soLuong FROM NhaDat ORDER BY soLuong DESC LIMIT 10",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            @SuppressLint("Range") String tinhThanh=cursor.getString(cursor.getColumnIndex("tinhThanh"));
            @SuppressLint("Range") String soLuong=cursor.getString(cursor.getColumnIndex("soLuong"));
            Top top=new Top(tinhThanh,soLuong);
            list.add(top);
            cursor.moveToNext();

        }
        cursor.close();
        return list;
    }

    @Override
    public int getDoanhThu(String tuNgay, String denNgay) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        List<Integer> list=new ArrayList<Integer>();
        Cursor cursor=database.rawQuery("SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ? AND traSach=1",new String[]{tuNgay,denNgay});
       while(cursor.moveToNext()){
           try{

           }catch (Exception e){
               list.add(0);
           }
       }
        return list.get(0);
    }
}