package com.example.duan1_cellhome;

import static java.time.LocalDate.now;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


import com.example.duan1_cellhome.DAO.ThongKeDao;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ThongKeDoanhThuFragment extends Fragment {

    EditText edtTu,edtToi;
    Button btnTinh;
    TextView txt;
    int doanhthu;
    int sosanh;
    int sosanhNgayHienTai1;
    int sosanhNgayHienTai2;
    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thongkedoanhthu_fragment,container,false);
        edtTu=view.findViewById(R.id.txtTuNgay);
        edtToi=view.findViewById(R.id.txtDenNgay);
        btnTinh=view.findViewById(R.id.btnTinh);
        txt=view.findViewById(R.id.txtTongTien);
        //biểu đồ tròn
        int tongTienNha=new ThongKeDao(getContext()).getTienNha();
        int tongTienDat=new ThongKeDao(getContext()).getTienDat();
        PieChart pieChart=view.findViewById(R.id.pieChart);
        ArrayList<PieEntry> visitors=new ArrayList<>();
        visitors.add(new PieEntry(tongTienNha,"Tiền bán nhà"));
        visitors.add(new PieEntry(tongTienDat,"Tiền bán đất"));
        PieDataSet pieDataSet=new PieDataSet(visitors,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(18f);
        pieDataSet.setLabel("Đơn vị tiền tệ:$ ");
        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Thống Kê");
        pieChart.animate();

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tungay=  edtTu.getText().toString();
                String denngay= edtToi.getText().toString();
                Date date=null;
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date=sdf.parse(tungay);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(tungay.isEmpty()||denngay.isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống ngày", Toast.LENGTH_SHORT).show();
                }else if (!tungay.equals(sdf.format(date))){
                    Toast.makeText(getContext(), "Sai định dạng ngày", Toast.LENGTH_SHORT).show();

                }else{
                    //tính doanh thu
                    DecimalFormat formatter = new DecimalFormat("#,###,###");
                    try {
                        Date date1=sdf.parse(tungay);
                        Date ngayHienTai = java.sql.Date.valueOf(String.valueOf(now()));
                        Date date2=sdf.parse(denngay);
                        sosanh = date1.compareTo(date2);
                        sosanhNgayHienTai1=date1.compareTo(ngayHienTai);
                        sosanhNgayHienTai2=date2.compareTo(ngayHienTai);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (sosanh>0){
                        Toast.makeText(getContext(), "Ngày bắt đầu phải nhỏ hơn ngày kết thúc", Toast.LENGTH_SHORT).show();

                    }else if (sosanhNgayHienTai1>0||sosanhNgayHienTai2>0){
                        Toast.makeText(getContext(), "Nhập ngày phải nhỏ hơn ngày hiện tại", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        doanhthu=new ThongKeDao(getContext()).getDoanhThu(tungay,denngay);
                        int doanhthuTienNha=new ThongKeDao(getContext()).getTienNhaTheoNgay(tungay,denngay);
                        int doanhthuTienDat=new ThongKeDao(getContext()).getTienDatTheoNgay(tungay,denngay);
                        txt.setText(formatter.format(doanhthu)+"$");
                        ArrayList<PieEntry> visitors=new ArrayList<>();
                        visitors.add(new PieEntry(doanhthuTienNha,"Tiền bán nhà"));
                        visitors.add(new PieEntry(doanhthuTienDat,"Tiền bán đất"));
                        PieDataSet pieDataSet=new PieDataSet(visitors,"");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        pieDataSet.setValueTextColor(Color.WHITE);
                        pieDataSet.setValueTextSize(18f);
                        pieDataSet.setLabel("Đơn vị tiền tệ:$ ");
                        PieData pieData=new PieData(pieDataSet);
                        pieChart.setData(pieData);
                        pieChart.getDescription().setEnabled(false);
                        pieChart.setCenterText("Thống Kê");
                        pieChart.animate();
                    }


                }

            }
        });
        edtTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chọn ngày
                datePickerDialog();
            }
        });
        edtToi.setOnClickListener(new View.OnClickListener() {
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
        int month=month1;
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        int day=calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                edtTu.setText(sdf.format(calendar.getTime()));

            }
        },year,month,day);
        datePickerDialog.show();
    }
    public void datePickerDialog2(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        int month=month1;
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        int day=calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                edtToi.setText(sdf.format(calendar.getTime()));

            }
        },year,month,day);
        datePickerDialog.show();
    }
}
