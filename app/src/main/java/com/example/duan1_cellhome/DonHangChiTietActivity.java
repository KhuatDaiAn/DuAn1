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

import static java.time.LocalDate.now;

public class DonHangChiTietActivity extends AppCompatActivity {
    TextView txtTenGTNhaDat,txtDiaChi,txtMoTa,txtTien,txtMaTVMuaHang,txtSDTKhachHang;
    ImageView imgHinh, imgQuayLai;
    String maDonHang;
    Button btnHoanTatGD;
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
        imgQuayLai = findViewById(R.id.imgQuayLai);
        btnHoanTatGD = findViewById(R.id.btnHoanTatGD);
        btnHoanTatGD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogHoanTatGD();
            }
        });
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
        txtMaTVMuaHang.setText(donHang.getMaTV());
        txtSDTKhachHang.setText(donHang.getSoDTNM()+"");
    }
    public void dialogHoanTatGD() {
        Dialog dialog = new Dialog(DonHangChiTietActivity.this);
        dialog.setContentView(R.layout.dialog_sua_don_hang);
        dialog.setCanceledOnTouchOutside(false);
        CheckBox chkHoaThanh = dialog.findViewById(R.id.chkHoanTatGD);
        Button btnLuuGD=dialog.findViewById(R.id.btnLuuGD);
        Button btnCancel = dialog.findViewById(R.id.btnBo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLuuGD.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int check ;
                Intent intent=getIntent();
                maDonHang=intent.getStringExtra("maDonHang");
                DonHang donHang=new DonHangDAO(DonHangChiTietActivity.this).getMaDonHang(maDonHang);
                DonHangDAO donHangDAO = new DonHangDAO(getApplicationContext());
                if (chkHoaThanh.isChecked()){
                }else {
                }

//                String tenNhaDat=edtTenNhaDat.getText().toString();
//                String tinhThanh=selectedTinhThanh;
//                String diachi=edtdiaChi.getText().toString();
//                String giatien=edtGiaTien.getText().toString();
//                String dientich=edtDienTich.getText().toString();
//                String mota=edtmoTa.getText().toString();
//                int giaTien = Integer.parseInt(giatien);
//                Date ngayDang= java.sql.Date.valueOf(String.valueOf(now()));
//                NhaDat nhaDat = new NhaDat(maNhaDat, tenNhaDat, null, tinhThanh, ngayDang,diachi,giaTien,dientich,mota,0);
//                NhaDatDAO dao = new NhaDatDAO(getApplicationContext());
//                dao.update(nhaDat);
//                dialog.dismiss();
//                ganDuLieu();
            }
        });

        dialog.show();
    }
}