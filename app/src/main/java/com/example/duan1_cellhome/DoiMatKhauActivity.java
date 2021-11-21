package com.example.duan1_cellhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.Thanhvien;

public class DoiMatKhauActivity extends AppCompatActivity {

    EditText edtOldPass, edtNewPass, edtConfirmPass;
    Button btnDMK, btnHuy;
    String oldPass, newPass, confirmPass;
    private String maThanhVien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        AnhXa();
        Intent intent = getIntent();
        maThanhVien = intent.getStringExtra("maThanhVien");
        Thanhvien thanhVien = new ThanhvienDao(this).getMa(maThanhVien);
        String maThanhVien = thanhVien.getMatv();
        String hoTen = thanhVien.getHoTen();
        String tenTK = thanhVien.getTenTK();
        String matKhau = thanhVien.getMk();
        String namSinh =thanhVien.getNamSinh();
        int soDT = thanhVien.getSoDT();
        int vaiTro = thanhVien.getVaitro();



        btnDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass = edtOldPass.getText().toString();
                newPass = edtNewPass.getText().toString();
                confirmPass = edtConfirmPass.getText().toString();

                if (oldPass.equals("") || newPass.equals("") || confirmPass.equals("")) {
                    Toast.makeText(DoiMatKhauActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                } else if (!matKhau.equals(oldPass)) {
                    Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu hiện tại không đúng", Toast.LENGTH_SHORT).show();
                } else if (newPass.equals(matKhau)) {
                    Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu mới không được trùng mật khẩu cũ", Toast.LENGTH_SHORT).show();
                }  else if (!newPass.equals(confirmPass)) {
                    Toast.makeText(DoiMatKhauActivity.this, "Xác nhận mật khẩu mới không đúng", Toast.LENGTH_SHORT).show();
                } else {
                    Thanhvien thanhvien = new Thanhvien(maThanhVien, hoTen, tenTK, matKhau, namSinh, soDT, vaiTro);
                    ThanhvienDao dao = new ThanhvienDao(getApplicationContext());
                    dao.updateMK(thanhvien);
                    Toast.makeText(DoiMatKhauActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(DoiMatKhauActivity.this, MainActivity.class);
                    startActivity(intent1);
                }
            }

        });
    }


    public void AnhXa() {
        edtOldPass = findViewById(R.id.edtOldPasswordDMK);
        edtNewPass = findViewById(R.id.edtNewPasswordDMK);
        edtConfirmPass = findViewById(R.id.edtConfirmPaswordDMK);
        btnDMK = findViewById(R.id.btnDMK);
        btnHuy = findViewById(R.id.btnHuyDMK);
    }
}