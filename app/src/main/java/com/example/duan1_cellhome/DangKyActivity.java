package com.example.duan1_cellhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.Thanhvien;

public class DangKyActivity extends AppCompatActivity {

    EditText edtUserNameDK, edtPaswordDK, edtNhapLaiPaswordDK,edtHoTen,edtSoDienThoai,edtNamSinhDK;

    ThanhvienDao dao;

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

        dao = new ThanhvienDao(this);
    }

    public void onRegister(View view) {
        String pwd = edtPaswordDK.getText().toString();
        String repwd = edtNhapLaiPaswordDK.getText().toString();
        if (!pwd.equals(repwd)) {
            Toast.makeText(this, "Password invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        String hoTen = edtUserNameDK.getText().toString();
        if (hoTen.trim().length() == 0) {
            Toast.makeText(this, "Ho ten invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        String soDT = edtSoDienThoai.getText().toString();
        String namSinh = edtNamSinhDK.getText().toString();


        Thanhvien thanhvien = new Thanhvien();
        thanhvien.setHoTen(hoTen);
        thanhvien.setNamSinh(namSinh);
        thanhvien.setSoDT(soDT);
        thanhvien.setVaitro(1); // mặc định ko đổi
        dao.insert(thanhvien);
        // chuyển qua màn hình đăng nhập
        finish();
    }
}