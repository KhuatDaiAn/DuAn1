package com.example.duan1_cellhome;

import static java.time.LocalDate.now;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_cellhome.Adapter.HinhAdapter;
import com.example.duan1_cellhome.Adapter.NhaDatAdapter;
import com.example.duan1_cellhome.Adapter.UpNhiuHinhAdapter;
import com.example.duan1_cellhome.DAO.HinhDAO;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.Model.Hinh;
import com.example.duan1_cellhome.Model.NhaDat;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gun0912.tedbottompicker.TedBottomPicker;

public class SuaChiTietNhaDatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UpNhiuHinhAdapter adapter;
    HinhAdapter upHinhAdapter;
    GridView gvHinh;
    TextView txttenGT,txtmaNhaDat,txtmoTa,txtgiaTien,txtdiaChi;
    ImageView imgHinh,imgUpNhieuHinh,imgThoat;
    List<Hinh> list;
    Animation animationZoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_chi_tiet_nha_dat);
        isStoragePermissionGranted();
        AnhXa();
        ganDuLieu();
        imgThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    private void ganDuLieu() {
        Intent intent=getIntent();
        String maNhaDat=intent.getStringExtra("maNhaDat");
        NhaDat nhaDat=new NhaDatDAO(this).getMa(maNhaDat);
        txttenGT.setText(nhaDat.getTenGT());
        txtmaNhaDat.setText(nhaDat.getMaNhaDat());
        txtmoTa.setText(nhaDat.getMoTa());
        txtgiaTien.setText(nhaDat.getGiaTien()+"");
        txtdiaChi.setText(nhaDat.getDiaChi());
        byte[] imageArray=nhaDat.getHinh();
        if(imageArray==null){
            imgHinh.setImageResource(R.drawable.ic_launcher_background);
        }else{
            imgHinh.setImageBitmap(BitmapFactory.decodeByteArray(imageArray,0,imageArray.length));
        }

        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHinh.startAnimation(animationZoom);
            }
        });

        imgUpNhieuHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HinhDAO dao=new HinhDAO(getApplicationContext());
                dao.delete(maNhaDat);
                requestPermission();

            }
        });
        adapter=new UpNhiuHinhAdapter(this);
        @SuppressLint("WrongConstant") GridLayoutManager gridLayoutManager=new GridLayoutManager(this,10, LinearLayout.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter);

        list=new HinhDAO(this).getHinh(maNhaDat);
        upHinhAdapter=new HinhAdapter(getApplicationContext(),list);
        gvHinh.setNumColumns(6);
        gvHinh.setAdapter(upHinhAdapter);
        gvHinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hinh hinh= (Hinh) upHinhAdapter.getItem(i);
                dialogHienThiHinh(hinh.getHinh());
            }
        });
    }


    private void requestPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openBottomPicker();

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(SuaChiTietNhaDatActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private void openBottomPicker() {
        TedBottomPicker.OnMultiImageSelectedListener listener=new TedBottomPicker.OnMultiImageSelectedListener() {
            @Override
            public void onImagesSelected(ArrayList<Uri> uriList) {
                adapter.setData((ArrayList<Uri>) uriList);
            }
        };

        TedBottomPicker tedBottomPicker= new TedBottomPicker.Builder(SuaChiTietNhaDatActivity.this)
                .setOnMultiImageSelectedListener(listener)
                .setCompleteButtonText("Done")
                .setEmptySelectionText("No Image")
                .create();

        tedBottomPicker.show(getSupportFragmentManager());
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }

    public void AnhXa(){
        txttenGT=findViewById(R.id.txttenGTChiTiet);
        txtmaNhaDat=findViewById(R.id.txtmaNhaDat);
        txtmoTa=findViewById(R.id.txtmoTa);
        txtgiaTien=findViewById(R.id.txtgiaTienChiTiet);
        txtdiaChi=findViewById(R.id.txtdiaChi);
        imgHinh=(ImageView) findViewById(R.id.imgHinhChiTiet);
        imgUpNhieuHinh=(ImageView) findViewById(R.id.imgChonHinh);
        recyclerView=findViewById(R.id.recyclerViewNhieuHinh);
        gvHinh=findViewById(R.id.gvNhieuHinh);
        imgThoat=findViewById(R.id.imgThoat);
        animationZoom= AnimationUtils.loadAnimation(this,R.anim.animation_zoom_in);
    }

    public void dialogHienThiHinh(byte[] hinh) {
        Dialog dialog = new Dialog(SuaChiTietNhaDatActivity.this);
        dialog.setContentView(R.layout.dialog_hienthi_hinh);

        ImageView imgHienThi=dialog.findViewById(R.id.imgHienThiHinh);

        if(hinh==null){
            imgHienThi.setImageResource(R.drawable.ic_launcher_background);
        }else{
            imgHienThi.setImageBitmap(BitmapFactory.decodeByteArray(hinh,0,hinh.length));
        }
        dialog.show();
    }

}