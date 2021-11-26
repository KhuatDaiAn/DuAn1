package com.example.duan1_cellhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.Thanhvien;

import java.util.Random;

public class DangKyActivity extends AppCompatActivity {

    EditText edtUserNameDK, edtPaswordDK, edtNhapLaiPaswordDK,edtHoTen,edtSoDienThoai,edtNamSinhDK;
    Button btnDangKy;
    ImageView imgQuaylai;
    TextView txtCoTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edtUserNameDK = findViewById(R.id.edtUserNameDK);
        edtPaswordDK = findViewById(R.id.edtPaswordDK);
        edtNhapLaiPaswordDK = findViewById(R.id.edtNhapLaiPaswordDK);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        edtNamSinhDK = findViewById(R.id.edtNamSinhDK);
        btnDangKy = findViewById(R.id.btnDangKy);
        txtCoTaiKhoan = findViewById(R.id.txtVeDangNhap);
        imgQuaylai = findViewById(R.id.imgQuayLaiDN);
        imgQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtCoTaiKhoan = findViewById(R.id.txtVeDangNhap);
        txtCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKy();
            }
        });




    }
    public void dangKy(){
        Random random=new Random();
        int maTV=random.nextInt(61);
        String username=edtUserNameDK.getText().toString();
        String password=edtPaswordDK.getText().toString();
        String Repassword=edtNhapLaiPaswordDK.getText().toString();
        String hoTen=edtHoTen.getText().toString();
        String namSinh=edtNamSinhDK.getText().toString();
        String sodt=edtSoDienThoai.getText().toString();
        if (username.isEmpty()||password.isEmpty()||Repassword.isEmpty()||hoTen.isEmpty()||namSinh.isEmpty()||sodt.isEmpty()){
            Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
        }else{
            if (Repassword.equals(password)){
                //tạo tài khoản mới
                int soDT=Integer.parseInt(sodt);
                Thanhvien thanhvien=new Thanhvien(maTV+"",hoTen,username,password,namSinh,soDT,1);
                ThanhvienDao dao=new ThanhvienDao(getApplicationContext());
                dao.insert(thanhvien);
            }else{
                Toast.makeText(getApplicationContext(), "Xác nhận lại mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

}