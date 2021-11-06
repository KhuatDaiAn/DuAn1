package com.example.duan1_cellhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.Model.NhaDat;

import java.util.List;

public class ChiTietNhaDatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nha_dat);
        TextView txttenGT=findViewById(R.id.txttenGTChiTiet);
        TextView txtmaNhaDat=findViewById(R.id.txtmaNhaDat);
        TextView txtmoTa=findViewById(R.id.txtmoTa);
        TextView txtgiaTien=findViewById(R.id.txtgiaTienChiTiet);
        TextView txtdiaChi=findViewById(R.id.txtdiaChi);
        ImageView imgHinh=(ImageView) findViewById(R.id.imgHinhChiTiet);

        Intent intent=getIntent();
        String maNhaDat=intent.getStringExtra("maNhaDat");
        NhaDat nhaDat=new NhaDatDAO(this).getMa(maNhaDat);
        txttenGT.setText(nhaDat.getTenGT());
        txtmaNhaDat.setText(nhaDat.getMaNhaDat());
        txtmoTa.setText(nhaDat.getDienTich());
        txtgiaTien.setText(nhaDat.getGiaTien()+"");
        txtdiaChi.setText(nhaDat.getDiaChi());
        byte[] imageArray=nhaDat.getHinh();
        if(imageArray==null){
            imgHinh.setImageResource(R.drawable.nha1);
        }else{
            imgHinh.setImageBitmap(BitmapFactory.decodeByteArray(imageArray,0,imageArray.length));
        }


    }

}