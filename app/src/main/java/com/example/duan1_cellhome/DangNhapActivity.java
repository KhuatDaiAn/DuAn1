package com.example.duan1_cellhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.Thanhvien;

public class DangNhapActivity extends AppCompatActivity {
    private TextView txtDangKy;
    Button btnDangNhap;
    EditText edtUsername,edtPassword;
    String username , password;
    CheckBox nho;
    SharedPreferences LuuThongTinDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        txtDangKy = findViewById(R.id.txtDangKy);
        edtUsername=findViewById(R.id.edtUserNameDN);
        edtPassword=findViewById(R.id.edtPaswordDN);
        nho = findViewById(R.id.chkNho);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        //hàm check lưu đăng nhập hay không
        LuuThongTinDangNhap = getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        edtUsername.setText(LuuThongTinDangNhap.getString("TK",""));
        edtPassword.setText(LuuThongTinDangNhap.getString("MK",""));
        nho.setChecked(LuuThongTinDangNhap.getBoolean("CHECK",false));
//        checkloginStatus();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                if (username.equals("")&& password.equals("")){
                    Toast.makeText(DangNhapActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }else if(username.equals("")){
                    Toast.makeText(DangNhapActivity.this, "Không được để trống username", Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(DangNhapActivity.this, "Không được để trống password", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean check=(new ThanhvienDao(DangNhapActivity.this)).login(username,password);
                    if (check==true){
                        Thanhvien thanhvien = new ThanhvienDao(getApplicationContext()).getDuLieu(username);
                        int vaiTro = thanhvien.getVaitro();
                        if (vaiTro==0){
                            setLuuThongTinDangNhap(username,password,nho.isChecked());
                            //ghi nhớ đăng nhập
//                            SharedPreferences.Editor editor=getSharedPreferences("login_status",MODE_PRIVATE).edit();
//                            editor.putBoolean("isLoggedAdmin",true);
//                            editor.apply();
                            //chuyển qua màn hình chính
                            Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                            intent.putExtra("tenTK",username);
                            intent.putExtra("matKhau",password);
                            startActivity(intent);
                        }else{
                            setLuuThongTinDangNhap(username,password,nho.isChecked());
                            //ghi nhớ đăng nhập
//                            SharedPreferences.Editor editor=getSharedPreferences("login_status",MODE_PRIVATE).edit();
//                            editor.putBoolean("isLoggedNguoiDung",true);
//                            editor.apply();
                            //chuyển qua màn hình chính
                            Intent intent = new Intent(DangNhapActivity.this, MenuNguoiDung.class);
                            intent.putExtra("tenTK",username);
                            intent.putExtra("matKhau",password);
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

    private void checkloginStatus(){
        SharedPreferences editor=getSharedPreferences("login_status",MODE_PRIVATE);
        boolean isLoggedAdmin = editor.getBoolean("isLoggedAdmin",false);
        if(isLoggedAdmin==true){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
            return;
        }

        boolean isLoggedNguoiDung = editor.getBoolean("isLoggedNguoiDung",false);
        if(isLoggedNguoiDung==true){
            startActivity(new Intent(getApplicationContext(),MenuNguoiDung.class));
            finish();
            return;
        }

    }
    public void setLuuThongTinDangNhap(String tk,String mk, boolean check){
        SharedPreferences sharedPreferences = getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (check == false){
            editor.clear();
        }else {
            editor.putString("TK",tk);
            editor.putString("MK",mk);
            editor.putBoolean("CHECK",check);
        }
        editor.commit();
    }

}