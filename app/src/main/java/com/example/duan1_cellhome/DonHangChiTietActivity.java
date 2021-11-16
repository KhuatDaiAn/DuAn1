package com.example.duan1_cellhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_cellhome.Adapter.HinhAdapter;
import com.example.duan1_cellhome.DAO.DonHangDAO;
import com.example.duan1_cellhome.DAO.HinhDAO;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.Model.Hinh;
import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.Model.Thanhvien;

import java.util.List;
import java.util.Random;

public class DonHangChiTietActivity extends AppCompatActivity {
    TextView txtTenGTNhaDat,txtDiaChi,txtMoTa,txtTien,txtMaTVMuaHang,txtSDTKhachHang;
    ImageView imgHinh;
    String maDonHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_chi_tiet);
        txtTenGTNhaDat = findViewById(R.id.txtTenGTNhaDat);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtTien = findViewById(R.id.txtTien);
        txtMaTVMuaHang = findViewById(R.id.txtMaTVMuaHang);
        txtSDTKhachHang = findViewById(R.id.txtSDTKhachHang);
        imgHinh = findViewById(R.id.imgHinh);
        ganDuLieuDonHang();

    }
    private void ganDuLieuDonHang() {
        Intent intent=getIntent();
        maDonHang=intent.getStringExtra("maDonHang");
        DonHang donHang=new DonHangDAO(this).getMaDonHang(maDonHang);
        txtTenGTNhaDat.setText(donHang.getTenGTND());
        txtDiaChi.setText(donHang.getDiaChiND());
//        txtTien.setText(donHang.getGiaTienND()+"");
        txtMaTVMuaHang.setText(donHang.getMaTV());
        txtSDTKhachHang.setText(donHang.getSoDTNM()+"");
//        byte[] imageArray=donHang.getHinhND();
//        if(imageArray==null){
//            imgHinh.setImageResource(R.drawable.nha1);
//        }else{
//            imgHinh.setImageBitmap(BitmapFactory.decodeByteArray(imageArray,0,imageArray.length));
//        }
    }
}