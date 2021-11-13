package com.example.duan1_cellhome.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_cellhome.Database.Database;
import com.example.duan1_cellhome.Model.NhaDat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NhaDatDAO implements INhaDatDAO{
    Database mydatabase;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    public NhaDatDAO(Context context){
        mydatabase=new Database(context);
    }



    @Override
    public List<NhaDat> getNha() {
        List<NhaDat> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM NhaDat WHERE vaitro==0",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maNhaDat=cursor.getString(0);
            String tenGT=cursor.getString(1);
            byte[] hinh=cursor.getBlob(2);
            String tinhThanh=cursor.getString(3);
            Date ngayDang = null;
            try {
                ngayDang = sdf.parse(cursor.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String diaChi=cursor.getString(5);
            int giaTien=cursor.getInt(6);
            String dienTich=cursor.getString(7);
            String moTa=cursor.getString(8);
            int vaitro=cursor.getInt(9);
            NhaDat nhaDat=new NhaDat(maNhaDat,tenGT,hinh,tinhThanh,ngayDang,diaChi,giaTien,dienTich,moTa,vaitro);
            list.add(nhaDat);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    @Override
    public List<NhaDat> getDat() {
        List<NhaDat> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM NhaDat WHERE vaitro==1",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maNhaDat=cursor.getString(0);
            String tenGT=cursor.getString(1);
            byte[] hinh=cursor.getBlob(2);
            String tinhThanh=cursor.getString(3);
            Date ngayDang = null;
            try {
                ngayDang = sdf.parse(cursor.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String diaChi=cursor.getString(5);
            int giaTien=cursor.getInt(6);
            String dienTich=cursor.getString(7);
            String moTa=cursor.getString(8);
            int vaitro=cursor.getInt(9);
            NhaDat nhaDat=new NhaDat(maNhaDat,tenGT,hinh,tinhThanh,ngayDang,diaChi,giaTien,dienTich,moTa,vaitro);
            list.add(nhaDat);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<NhaDat> hienThiTheoTinhThanh(String tentinhthanh) {
        List<NhaDat> list=new ArrayList<>();
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM NhaDat WHERE tinhThanh=?",new String[]{tentinhthanh});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maNhaDat=cursor.getString(0);
            String tenGT=cursor.getString(1);
            byte[] hinh=cursor.getBlob(2);
            String tinhThanh=cursor.getString(3);
            Date ngayDang = null;
            try {
                ngayDang = sdf.parse(cursor.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String diaChi=cursor.getString(5);
            int giaTien=cursor.getInt(6);
            String dienTich=cursor.getString(7);
            String moTa=cursor.getString(8);
            int vaitro=cursor.getInt(9);
            NhaDat nhaDat=new NhaDat(maNhaDat,tenGT,hinh,tinhThanh,ngayDang,diaChi,giaTien,dienTich,moTa,vaitro);
            list.add(nhaDat);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }




    public NhaDat getMa(String manhadat) {
        NhaDat nhaDat=null;
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM NhaDat WHERE maNhaDat= ?",new String[]{manhadat});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String maNhaDat=cursor.getString(0);
            String tenGT=cursor.getString(1);
            byte[] hinh=cursor.getBlob(2);
            String tinhThanh=cursor.getString(3);
            Date ngayDang = null;
            try {
                ngayDang = sdf.parse(cursor.getString(4));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String diaChi=cursor.getString(5);
            int giaTien=cursor.getInt(6);
            String dienTich=cursor.getString(7);
            String moTa=cursor.getString(8);
            int vaitro=cursor.getInt(9);
            nhaDat=new NhaDat(maNhaDat,tenGT,hinh,tinhThanh,ngayDang,diaChi,giaTien,dienTich,moTa,vaitro);
            cursor.moveToNext();
        }
        cursor.close();
        return nhaDat;
    }

    @Override
    public void insert(NhaDat nhaDat) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("maNhaDat",nhaDat.getMaNhaDat());
        values.put("tenGT",nhaDat.getTenGT());
        values.put("hinh",nhaDat.getHinh());
        values.put("tinhThanh",nhaDat.getTinhThanh());
        values.put("ngayDang",sdf.format(nhaDat.getNgayDang()));
        values.put("diaChi",nhaDat.getDiaChi());
        values.put("giaTien",nhaDat.getGiaTien());
        values.put("dienTich",nhaDat.getDienTich());
        values.put("moTa",nhaDat.getMoTa());
        values.put("vaitro",nhaDat.getVaitro());
        database.insert("NhaDat",null,values);

    }


    @Override
    public void update(NhaDat nhaDat) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        String[] params=new String[]{nhaDat.getMaNhaDat()};

        values.put("tenGT",nhaDat.getTenGT());
        values.put("hinh",nhaDat.getHinh());
        values.put("tinhThanh",nhaDat.getTinhThanh());
        values.put("ngayDang",sdf.format(nhaDat.getNgayDang()));
        values.put("diaChi",nhaDat.getDiaChi());
        values.put("giaTien",nhaDat.getGiaTien());
        values.put("dienTich",nhaDat.getDienTich());
        values.put("moTa",nhaDat.getMoTa());
        values.put("vaitro",nhaDat.getVaitro());
        database.update("NhaDat",values,"maNhaDat = ?",params);

    }
    public void updateHinh(NhaDat nhaDat) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        ContentValues values=new ContentValues();
        String[] params=new String[]{nhaDat.getMaNhaDat()};
        values.put("hinh",nhaDat.getHinh());
        database.update("NhaDat",values,"maNhaDat = ?",params);
    }
    @Override
    public void delete(String maNhaDat) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String[] params=new String[]{maNhaDat};
        database.delete("NhaDat","maNhaDat = ?",params);

    }
}
