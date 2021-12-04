package com.example.duan1_cellhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.Thanhvien;

import java.util.Calendar;
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
        edtPaswordDK = findViewById(R.id.edtPasswordDK);
        edtNhapLaiPaswordDK = findViewById(R.id.edtNhapLaiPW);
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
        boolean check=new ThanhvienDao(getApplicationContext()).kiemTraDangKy(username);
        String pattern = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        if (username.isEmpty()||password.isEmpty()||Repassword.isEmpty()||hoTen.isEmpty()||namSinh.isEmpty()||sodt.isEmpty()){
            Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
        }else if(!sodt.matches(pattern)){
            Toast.makeText(getApplicationContext(), "Không đúng định dạng số điện thoại", Toast.LENGTH_SHORT).show();
        }else if (check==true){
            Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
        }else if (password.length()<=2){
            Toast.makeText(getApplicationContext(), "Mật khẩu quá ngắn", Toast.LENGTH_LONG).show();
        } else{
            int namsinh=Integer.parseInt(namSinh);
            if (namsinh>=year||(year-namsinh)<=5){
                Toast.makeText(getApplicationContext(), "Chưa đủ tuổi hoặc nhập năm sinh ảo", Toast.LENGTH_SHORT).show();
            }else if (Repassword.equals(password)){
                //tạo tài khoản mới
                int soDT=Integer.parseInt(sodt);
                Thanhvien thanhvien=new Thanhvien(maTV+"",hoTen,username,password,namSinh,soDT,1);
                ThanhvienDao dao=new ThanhvienDao(getApplicationContext());
                dao.insert(thanhvien);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "Xác nhận lại mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

}