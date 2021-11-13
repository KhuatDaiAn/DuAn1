package com.example.duan1_cellhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.Thanhvien;

public class DangNhapActivity extends AppCompatActivity {
    private TextView txtDangKy;
    EditText edtUsername,edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        txtDangKy = findViewById(R.id.txtDangKy);
        edtUsername=findViewById(R.id.edtUserNameDN);
        edtPassword=findViewById(R.id.edtPaswordDN);
        boolean check=new ThanhvienDao(getApplicationContext()).login(edtUsername.getText().toString(),edtPassword.getText().toString());
        if (check==true){
            String username=edtUsername.getText().toString();
            Thanhvien thanhvien=new ThanhvienDao(getApplicationContext()).getDuLieu(username);
            if (thanhvien.getVaitro()==0){
                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                intent.putExtra("tentk",username);
                startActivity(intent);
            }else{
                Intent intent = new Intent(DangNhapActivity.this, MenuNguoiDung.class);
                intent.putExtra("tentk",username);
                startActivity(intent);
            }

        }else{
            Toast.makeText(getApplicationContext(), "Tên tài khoản hoặc mật khẩu đã sai", Toast.LENGTH_SHORT).show();
        }
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}