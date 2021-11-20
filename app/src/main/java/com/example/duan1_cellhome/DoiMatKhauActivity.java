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
    Button btnDMK;
    String oldPass, newPass, confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        edtOldPass = findViewById(R.id.edtOldPasswordDMK);
        edtNewPass = findViewById(R.id.edtNewPasswordDMK);
        edtConfirmPass = findViewById(R.id.edtConfirmPaswordDMK);
        btnDMK = findViewById(R.id.btnDMK);

        btnDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass = edtOldPass.getText().toString();
                newPass = edtNewPass.getText().toString();
                confirmPass = edtConfirmPass.getText().toString();

                if (oldPass.equals("") || newPass.equals("") || confirmPass.equals("")) {
                    Toast.makeText(DoiMatKhauActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                } else if (!newPass.equals(confirmPass)) {
                    Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu xác nhận không đúng", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }
}