package com.example.duan1_cellhome;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_cellhome.DAO.DonHangDAO;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.Model.NhaDat;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static java.time.LocalDate.now;

public class DonHangChiTietActivity extends AppCompatActivity {
    TextView txtTenGTNhaDat,txtDiaChi,txtTien,txtMaTVMuaHang,txtSDTKhachHang, txtNgayMua;
    ImageView imgHinh, imgQuayLai;
    String maDonHang;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_chi_tiet);
        txtTenGTNhaDat = findViewById(R.id.txtTenGTNhaDat);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtTien = findViewById(R.id.txtTien);
        txtMaTVMuaHang = findViewById(R.id.txtMaTVMuaHang);
        txtSDTKhachHang = findViewById(R.id.txtSDTKhachHang);
        txtNgayMua = findViewById(R.id.txtNgayMua);
        imgHinh = findViewById(R.id.imgHinh);
        imgQuayLai = findViewById(R.id.imgQuayLai);
        imgQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ganDuLieuDonHang();

    }
    private void ganDuLieuDonHang() {
        Intent intent=getIntent();
        maDonHang=intent.getStringExtra("maDonHang");
        DonHang donHang=new DonHangDAO(this).getMaDonHang(maDonHang);
        txtTenGTNhaDat.setText(donHang.getTenGTND());
        txtDiaChi.setText(donHang.getDiaChiND());
        txtTien.setText(donHang.getGiaTienND()+"");
        txtNgayMua.setText(sdf.format(donHang.getNgay()));
        txtMaTVMuaHang.setText(donHang.getMaTV());
        txtSDTKhachHang.setText(donHang.getSoDTNM()+"");
    }

}