package com.example.duan1_cellhome;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1_cellhome.Adapter.NhaDatAdapter;
import com.example.duan1_cellhome.Adapter.TinhThanhSpinnerAdapter;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.Model.Thanhvien;
import com.example.duan1_cellhome.Model.TinhThanh;

import java.util.ArrayList;
import java.util.List;

public class DoiMatKhauFragment extends Fragment {
    EditText edtOldPass, edtNewPass, edtConfirmPass;
    Button btnDMK, btnHuy;
    String oldPass, newPass, confirmPass;
    String tenThanhVien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_doi_mat_khau,container,false);
        edtOldPass = view.findViewById(R.id.edtOldPasswordDMK);
        edtNewPass = view.findViewById(R.id.edtNewPasswordDMK);
        edtConfirmPass = view.findViewById(R.id.edtConfirmPaswordDMK);
        btnDMK = view.findViewById(R.id.btnDMK);
        btnHuy = view.findViewById(R.id.btnHuyDMK);
        //lấy dữ liệu từ intent
        Intent intent = getActivity().getIntent();
        tenThanhVien = intent.getStringExtra("tenTK");
        Thanhvien thanhVien = new ThanhvienDao(getContext()).getDuLieu(tenThanhVien);
        String maThanhVien = thanhVien.getMatv();
        String matKhau = thanhVien.getMk();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNewPass.setText("");
                edtOldPass.setText("");
                edtConfirmPass.setText("");
            }
        });
        btnDMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass = edtOldPass.getText().toString();
                newPass = edtNewPass.getText().toString();
                confirmPass = edtConfirmPass.getText().toString();

                if (oldPass.equals("") && newPass.equals("") && confirmPass.equals("")) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    edtOldPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtNewPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtConfirmPass.setBackgroundColor(Color.parseColor("#f64f59"));
                }else if (oldPass.equals("") && newPass.equals("")) {
                    Toast.makeText(getContext(), "Thiếu mật khẩu cũ, mới", Toast.LENGTH_SHORT).show();
                    edtConfirmPass.setBackgroundResource(R.drawable.custom_backgroud);
                    edtOldPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtNewPass.setBackgroundColor(Color.parseColor("#f64f59"));
                }else if (oldPass.equals("") && confirmPass.equals("")) {
                    Toast.makeText(getContext(), "Thiếu mật khẩu cũ, nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                    edtOldPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtConfirmPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtNewPass.setBackgroundResource(R.drawable.custom_backgroud);
                } else if (newPass.equals("") && confirmPass.equals("")) {
                    Toast.makeText(getContext(), "Thiếu mật khẩu mới, nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                    edtNewPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtConfirmPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtOldPass.setBackgroundResource(R.drawable.custom_backgroud);
                }else if (newPass.equals("")) {
                    Toast.makeText(getContext(), "Thiếu mật khẩu mới", Toast.LENGTH_SHORT).show();
                    edtNewPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtOldPass.setBackgroundResource(R.drawable.custom_backgroud);
                    edtConfirmPass.setBackgroundResource(R.drawable.custom_backgroud);
                }else if (oldPass.equals("")) {
                    Toast.makeText(getContext(), "Thiếu mật khẩu cũ", Toast.LENGTH_SHORT).show();
                    edtOldPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtNewPass.setBackgroundResource(R.drawable.custom_backgroud);
                    edtConfirmPass.setBackgroundResource(R.drawable.custom_backgroud);
                }else if (confirmPass.equals("")) {
                    Toast.makeText(getContext(), "Thiếu nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                    edtConfirmPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtNewPass.setBackgroundResource(R.drawable.custom_backgroud);
                    edtOldPass.setBackgroundResource(R.drawable.custom_backgroud);
                }else if (!matKhau.equals(oldPass)) {
                    Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                    edtOldPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtNewPass.setBackgroundResource(R.drawable.custom_backgroud);
                    edtConfirmPass.setBackgroundResource(R.drawable.custom_backgroud);
                } else if (newPass.equals(matKhau)) {
                    Toast.makeText(getContext(), "Mật khẩu mới không được trùng mật khẩu cũ", Toast.LENGTH_SHORT).show();
                    edtNewPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtOldPass.setBackgroundResource(R.drawable.custom_backgroud);
                    edtConfirmPass.setBackgroundResource(R.drawable.custom_backgroud);
                }  else if (!newPass.equals(confirmPass)) {
                    Toast.makeText(getContext(), "Xác nhận mật khẩu mới không đúng", Toast.LENGTH_SHORT).show();
                    edtConfirmPass.setBackgroundColor(Color.parseColor("#f64f59"));
                    edtNewPass.setBackgroundResource(R.drawable.custom_backgroud);
                    edtOldPass.setBackgroundResource(R.drawable.custom_backgroud);
                } else {
                    //thay đổi mật khẩu
                    Thanhvien thanhvien = new Thanhvien(maThanhVien, "hoTen", "tenTK", newPass, "namSinh", 0, 0);
                    ThanhvienDao dao = new ThanhvienDao(getContext());
                    dao.updateMK(thanhvien);
                    Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(getContext(),DangNhapActivity.class);
                    startActivity(intent1);
                }
            }

        });
        return view;

    }



}
