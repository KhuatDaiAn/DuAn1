package com.example.duan1_cellhome.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1_cellhome.Database.Database;
import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.Model.NhaDat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonHangDAO implements IDonHang{
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    Database mydatabase;

    public DonHangDAO(Context context){
        mydatabase=new Database(context);
    }

    @Override
    public List<DonHang> getDonHang() {
        List<DonHang> donHangList = new ArrayList<>();
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM DonHang WHERE trangThai==1", null);
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
            Date ngayDang = null;
            try {
                ngayDang = sdf.parse(cursor.getString(9));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            donHangList.add( new DonHang(maDonHang, maThanhVien, maNhaDat, soDTNM, tenGTND, hinh, diaChiND, giaTien, trangThai,ngayDang));
            cursor.moveToNext();
        }
        cursor.close();
        return donHangList;
    }

    public List<DonHang> getDonHangGiaoDichThanhCong() {
        List<DonHang> donHangList = new ArrayList<>();
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM DonHang WHERE trangThai==0", null);
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
            Date ngayDang = null;
            try {
                ngayDang = sdf.parse(cursor.getString(9));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            donHangList.add( new DonHang(maDonHang, maThanhVien, maNhaDat, soDTNM, tenGTND, hinh, diaChiND, giaTien, trangThai,ngayDang));
            cursor.moveToNext();
        }
        cursor.close();
        return donHangList;
    }
    public List<DonHang> getDonHangCaNhan(String maTV) {
        List<DonHang> donHangList = new ArrayList<>();
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM DonHang WHERE trangThai==1 AND maTV=?", new String[]{maTV});
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
            Date ngayDang = null;
            try {
                ngayDang = sdf.parse(cursor.getString(9));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            donHangList.add( new DonHang(maDonHang, maThanhVien, maNhaDat, soDTNM, tenGTND, hinh, diaChiND, giaTien, trangThai,ngayDang));
            cursor.moveToNext();
        }
        cursor.close();
        return donHangList;
    }

    @Override
    public DonHang getMaDonHang(String maDonHang) {
        DonHang donHang=null;
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM DonHang WHERE maDonHang= ?",new String[]{maDonHang});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String madonHang = cursor.getString(0);
            String maThanhVien = cursor.getString(1);
            String maNhaDat = cursor.getString(2);
            int soDTNM = cursor.getInt(3);
            String tenGTND = cursor.getString(4);
            byte[] hinh = cursor.getBlob(5);
            String diaChiND = cursor.getString(6);
            int giaTien = cursor.getInt(7);
            int trangThai = cursor.getInt(8);
            Date ngayDang = null;
            try {
                ngayDang = sdf.parse(cursor.getString(9));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            donHang = new DonHang(madonHang, maThanhVien, maNhaDat, soDTNM, tenGTND, hinh, diaChiND, giaTien, trangThai,ngayDang);
            cursor.moveToNext();
        }

        cursor.close();
        return donHang;
    }

    @Override
    public void updateTrangThai(DonHang donHang) {
        SQLiteDatabase database = mydatabase.getReadableDatabase();
        ContentValues values = new ContentValues();
        String []params = new String[]{donHang.getMaDonHang()};
        values.put("trangThai",donHang.getTrangThai());
        values.put("ngay",sdf.format(donHang.getNgay()));
        database.update("DonHang",values,"maDonHang=?",params);
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
            values.put("ngay",sdf.format(donHang.getNgay()));
            database.insert("DonHang",null,values);
    }

    @Override
    public void delete(String maDonHang) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String[] params=new String[]{maDonHang};
        database.delete("DonHang","maDonHang = ?",params);
    }

    public Boolean kiemtradangkyDonHang(String maTV, String maNhaDat) {
        SQLiteDatabase database=mydatabase.getReadableDatabase();
        String sql = "SELECT * FROM DonHang WHERE maTV = ? AND maNhaDat = ?";
        Cursor cursor=database.rawQuery(sql,new String[]{maTV,maNhaDat});
        int count=cursor.getCount();
        cursor.close();
        return count>0;
    }

}
