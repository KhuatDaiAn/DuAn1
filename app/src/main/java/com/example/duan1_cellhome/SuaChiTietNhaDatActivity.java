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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_cellhome.Adapter.HinhAdapter;
import com.example.duan1_cellhome.Adapter.NhaDatAdapter;
import com.example.duan1_cellhome.Adapter.TinhThanhSpinnerAdapter;
import com.example.duan1_cellhome.Adapter.UpNhiuHinhAdapter;
import com.example.duan1_cellhome.DAO.HinhDAO;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.Model.Hinh;
import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.Model.TinhThanh;
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
    String maNhaDat;
    TextView txttenGT,txtmaNhaDat,txtmoTa,txtgiaTien,txtdiaChi;
    ImageView imgHinh,imgUpNhieuHinh,imgThoat,imgReload;
    List<Hinh> list;
    Button btnSua;
    Animation animationZoom;
    Spinner spinnerTinhThanh;
    String selectedTinhThanh=null;
    List<TinhThanh> tinhThanhList;
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

        imgReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ganDuLieu();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dialogSuaNhaDat();
            }
        });

    }

    private void ganDuLieu() {
        Intent intent=getIntent();
        maNhaDat=intent.getStringExtra("maNhaDat");
        NhaDat nhaDat=new NhaDatDAO(this).getMa(maNhaDat);
        txttenGT.setText(nhaDat.getTenGT());
        txtmaNhaDat.setText(nhaDat.getMaNhaDat());
        txtmoTa.setText(nhaDat.getMoTa());
        txtgiaTien.setText(nhaDat.getGiaTien()+"");
        txtdiaChi.setText(nhaDat.getDiaChi());
        byte[] imageArray=nhaDat.getHinh();
        if(imageArray==null){
            imgHinh.setImageResource(R.drawable.no_image);
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
        imgThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
        imgReload=(ImageView) findViewById(R.id.imgReload);
        imgUpNhieuHinh=(ImageView) findViewById(R.id.imgChonHinh);
        recyclerView=findViewById(R.id.recyclerViewNhieuHinh);
        gvHinh=findViewById(R.id.gvNhieuHinh);
        imgThoat=findViewById(R.id.imgThoat);
        btnSua=findViewById(R.id.btnSua);

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


    public void dialogSuaNhaDat() {
        Dialog dialog = new Dialog(SuaChiTietNhaDatActivity.this);
        dialog.setContentView(R.layout.dialog_sua_nha_dat);
        dialog.setCanceledOnTouchOutside(false);
        EditText edtTenNhaDat=dialog.findViewById(R.id.edtTenNhaDat);
        spinnerTinhThanh=dialog.findViewById(R.id.spinnerTinhThanh);
        EditText edtdiaChi=dialog.findViewById(R.id.edtDiaChi);
        EditText edtGiaTien=dialog.findViewById(R.id.edtGiaTien);
        EditText edtDienTich=dialog.findViewById(R.id.edtDienTich);
        EditText edtmoTa=dialog.findViewById(R.id.edtMoTa);
        Button btnSua=dialog.findViewById(R.id.btnSuaNhaDat);
        addTinhThanh();
        btnSua.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String tenNhaDat=edtTenNhaDat.getText().toString();
                String tinhThanh=selectedTinhThanh;
                String diachi=edtdiaChi.getText().toString();
                String giatien=edtGiaTien.getText().toString();
                String dientich=edtDienTich.getText().toString();
                String mota=edtmoTa.getText().toString();
                int giaTien = Integer.parseInt(giatien);
                Date ngayDang= java.sql.Date.valueOf(String.valueOf(now()));
                NhaDat nhaDat = new NhaDat(maNhaDat, tenNhaDat, null, tinhThanh, ngayDang,diachi,giaTien,dientich,mota,0);
                NhaDatDAO dao = new NhaDatDAO(getApplicationContext());
                dao.update(nhaDat);
                dialog.dismiss();
                ganDuLieu();
            }
        });

        dialog.show();
    }
    public void addTinhThanh(){
        tinhThanhList=new ArrayList<>();
        tinhThanhList.add(new TinhThanh("An Giang"));
        tinhThanhList.add(new TinhThanh("Bà Rịa-Vũng Tàu"));
        tinhThanhList.add(new TinhThanh("Bạc Liêu"));
        tinhThanhList.add(new TinhThanh("Bắc Kạn"));
        tinhThanhList.add(new TinhThanh("Bắc Giang"));
        tinhThanhList.add(new TinhThanh("Bắc Ninh"));
        tinhThanhList.add(new TinhThanh("Bến Tre"));
        tinhThanhList.add(new TinhThanh("Bình Dương"));
        tinhThanhList.add(new TinhThanh("Bình Định"));
        tinhThanhList.add(new TinhThanh("Bình Phước"));
        tinhThanhList.add(new TinhThanh("Bình Thuận"));
        tinhThanhList.add(new TinhThanh("Cà Mau"));
        tinhThanhList.add(new TinhThanh("Cao Bằng"));
        tinhThanhList.add(new TinhThanh("Cần Thơ "));
        tinhThanhList.add(new TinhThanh("Đà Nẵng"));
        tinhThanhList.add(new TinhThanh("Đắk Lắk"));
        tinhThanhList.add(new TinhThanh("Đắk Nông"));
        tinhThanhList.add(new TinhThanh("Điện Biên"));
        tinhThanhList.add(new TinhThanh("Đồng Nai"));
        tinhThanhList.add(new TinhThanh("Đồng Tháp"));
        tinhThanhList.add(new TinhThanh("Gia Lai"));
        tinhThanhList.add(new TinhThanh("Hà Giang"));
        tinhThanhList.add(new TinhThanh("Hà Nam"));
        tinhThanhList.add(new TinhThanh("Hà Nội"));
        tinhThanhList.add(new TinhThanh("Hà Tây"));
        tinhThanhList.add(new TinhThanh("Hà Tĩnh"));
        tinhThanhList.add(new TinhThanh("Hải Dương"));
        tinhThanhList.add(new TinhThanh("Hải Phòng"));
        tinhThanhList.add(new TinhThanh("Hòa Bình"));
        tinhThanhList.add(new TinhThanh("Hồ Chí Minh"));
        tinhThanhList.add(new TinhThanh("Hậu Giang"));
        tinhThanhList.add(new TinhThanh("Hưng Yên"));
        tinhThanhList.add(new TinhThanh("Khánh Hòa"));
        tinhThanhList.add(new TinhThanh("Kiên Giang"));
        tinhThanhList.add(new TinhThanh("Kon Tum"));
        tinhThanhList.add(new TinhThanh("Lai Châu"));
        tinhThanhList.add(new TinhThanh("Lào Cai"));
        tinhThanhList.add(new TinhThanh("Lạng Sơn"));
        tinhThanhList.add(new TinhThanh("Lâm Đồng"));
        tinhThanhList.add(new TinhThanh("Long An"));
        tinhThanhList.add(new TinhThanh("Nam Định"));
        tinhThanhList.add(new TinhThanh("Nghệ An"));
        tinhThanhList.add(new TinhThanh("Ninh Bình"));
        tinhThanhList.add(new TinhThanh("Ninh Thuận"));
        tinhThanhList.add(new TinhThanh("Phú Thọ"));
        tinhThanhList.add(new TinhThanh("Phú Yên"));
        tinhThanhList.add(new TinhThanh("Quảng Bình"));
        tinhThanhList.add(new TinhThanh("Quảng Nam"));
        tinhThanhList.add(new TinhThanh("Quảng Ngãi"));
        tinhThanhList.add(new TinhThanh("Quảng Ninh"));
        tinhThanhList.add(new TinhThanh("Quảng Trị"));
        tinhThanhList.add(new TinhThanh("Sóc Trăng"));
        tinhThanhList.add(new TinhThanh("Sơn La"));
        tinhThanhList.add(new TinhThanh("Tây Ninh"));
        tinhThanhList.add(new TinhThanh("Thái Bình"));
        tinhThanhList.add(new TinhThanh("Thái Nguyên"));
        tinhThanhList.add(new TinhThanh("Thanh Hóa"));
        tinhThanhList.add(new TinhThanh("Thừa Thiên – Huế"));
        tinhThanhList.add(new TinhThanh("Tiền Giang"));
        tinhThanhList.add(new TinhThanh("Trà Vinh"));
        tinhThanhList.add(new TinhThanh("Tuyên Quang"));
        tinhThanhList.add(new TinhThanh("Vĩnh Long"));
        tinhThanhList.add(new TinhThanh("Vĩnh Phúc"));
        tinhThanhList.add(new TinhThanh("Yên Bái"));

        TinhThanhSpinnerAdapter tinhThanhspinner=new TinhThanhSpinnerAdapter(getApplicationContext(),tinhThanhList);
        spinnerTinhThanh.setAdapter(tinhThanhspinner);
        spinnerTinhThanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TinhThanh tinhThanh = (TinhThanh) tinhThanhspinner.getItem(position);
                selectedTinhThanh=tinhThanh.getTenTinhThanh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTinhThanh=null;
            }
        });
    }

}