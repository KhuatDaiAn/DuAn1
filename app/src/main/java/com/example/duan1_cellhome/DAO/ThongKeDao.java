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
        Cursor cursor=database.rawQuery("SELECT tinhThanh,count(tinhThanh) as soLuong FROM NhaDat GROUP BY tinhThanh ORDER BY soLuong DESC LIMIT 10",null);
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

    @SuppressLint("Range")
    @Override
    public int getDoanhThu(String tuNgay, String denNgay) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        List<Integer> list=new ArrayList<Integer>();
        Cursor cursor=database.rawQuery("SELECT SUM(giaTienND) as doanhThu FROM DonHang WHERE ngay BETWEEN ? AND ? AND trangThai==0",new String[]{tuNgay,denNgay});
       while(cursor.moveToNext()){
           try{
               list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
           }catch (Exception e){
               list.add(0);
           }
       }
        return list.get(0);
    }
    @SuppressLint("Range")
    public int getTienNhaTheoNgay(String tuNgay, String denNgay) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        List<Integer> list=new ArrayList<Integer>();
        Cursor cursor=database.rawQuery("SELECT SUM(giaTienND) as doanhThuTienNha FROM DonHang WHERE ngay BETWEEN ? AND ? AND trangThai==0 AND maNhaDat LIKE 'Nha%'",new String[]{tuNgay,denNgay});
        while(cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThuTienNha"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

    @SuppressLint("Range")
    public int getTienDatTheoNgay(String tuNgay, String denNgay) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        List<Integer> list=new ArrayList<Integer>();
        Cursor cursor=database.rawQuery("SELECT SUM(giaTienND) as doanhThuTienDat FROM DonHang WHERE ngay BETWEEN ? AND ? AND trangThai==0 AND maNhaDat LIKE 'Dat%'",new String[]{tuNgay,denNgay});
        while(cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThuTienDat"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }


    @SuppressLint("Range")
    public int getTienNha() {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        List<Integer> list=new ArrayList<Integer>();
        Cursor cursor=database.rawQuery("SELECT SUM(giaTienND) as tongTienNha FROM DonHang WHERE trangThai==0 AND maNhaDat LIKE 'Nha%'",null);
        while(cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tongTienNha"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

    @SuppressLint("Range")
    public int getTienDat() {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        List<Integer> list=new ArrayList<Integer>();
        Cursor cursor=database.rawQuery("SELECT SUM(giaTienND) as tongTienDat FROM DonHang WHERE trangThai==0 AND maNhaDat LIKE 'Dat%'",null);
        while(cursor.moveToNext()){
            try{
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tongTienDat"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
