package com.example.duan1_cellhome;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


import com.example.duan1_cellhome.DAO.ThongKeDao;

import java.util.Calendar;

public class ThongKeDoanhThuFragment extends Fragment {

    EditText txtTu,txtToi;
    Button btnTinh;
    TextView txt;
    int doanhthu;

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thongkedoanhthu_fragment,container,false);
        txtTu=view.findViewById(R.id.txtTuNgay);
        txtToi=view.findViewById(R.id.txtDenNgay);
        btnTinh=view.findViewById(R.id.btnTinh);
        txt=view.findViewById(R.id.txtTongTien);


        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tungay=  txtTu.getText().toString();
                String denngay= txtToi.getText().toString();
                //tính doanh thu
                doanhthu=new ThongKeDao(getContext()).getDoanhThu(tungay,denngay);
                txt.setText(doanhthu+"");
            }
        });
        txtTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chọn ngày
                datePickerDialog();
            }
        });
        txtToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chọn ngày
                datePickerDialog2();
            }
        });



        return view;

    }



    public void datePickerDialog(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        int month=month1+1;
        int day=calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtTu.setText(dayOfMonth+"/"+month+"/"+year);

            }
        },year,month,day);
        datePickerDialog.show();
    }
    public void datePickerDialog2(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        int month=month1+1;
        int day=calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtToi.setText(dayOfMonth+"/"+month+"/"+year);

            }
        },year,month,day);
        datePickerDialog.show();
    }
}
