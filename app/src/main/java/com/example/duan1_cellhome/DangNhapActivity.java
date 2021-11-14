package com.example.duan1_cellhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.Thanhvien;

public class DangNhapActivity extends AppCompatActivity {
    private TextView txtDangKy;
    Button btnDangNhap;
    EditText edtUsername,edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        txtDangKy = findViewById(R.id.txtDangKy);
        edtUsername=findViewById(R.id.edtUserNameDN);
        edtPassword=findViewById(R.id.edtPaswordDN);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edtUsername.getText().toString();
                String password=edtPassword.getText().toString();
                if (username.equals("")&& password.equals("")){
                    Toast.makeText(DangNhapActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }else if (username.equals("")){
                    Toast.makeText(DangNhapActivity.this, "Không được để trống username", Toast.LENGTH_SHORT).show();
                }else if (password.equals("")){
                    Toast.makeText(DangNhapActivity.this, "Không được để trống password", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean check=(new ThanhvienDao(DangNhapActivity.this)).login(username,password);
                    if (check==true){
                        Thanhvien thanhvien=new ThanhvienDao(getApplicationContext()).getDuLieu(username);
                        int vaiTro = thanhvien.getVaitro();
                        if (vaiTro==0){
                            Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                            intent.putExtra("tenTK",username);
                            intent.putExtra("matKhau",password);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(DangNhapActivity.this, MenuNguoiDung.class);
                            intent.putExtra("tenTK",username);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Tên tài khoản hoặc mật khẩu đã sai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}