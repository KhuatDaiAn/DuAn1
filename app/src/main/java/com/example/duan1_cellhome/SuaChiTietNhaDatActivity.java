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
import androidx.recyclerview.widget.LinearLayoutManager;
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
import java.text.DecimalFormat;
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
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        txttenGT.setText(nhaDat.getTenGT());
        txtmaNhaDat.setText(nhaDat.getMaNhaDat());
        txtmoTa.setText(nhaDat.getMoTa());
        txtgiaTien.setText(formatter.format(nhaDat.getGiaTien())+"");
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
        //load h??nh l??n recycleview
        adapter=new UpNhiuHinhAdapter(this);
       // @SuppressLint("WrongConstant") GridLayoutManager gridLayoutManager=new GridLayoutManager(this,10, LinearLayout.VERTICAL,false);
        LinearLayoutManager linearLayout=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter);
        //load h??nh l??n gridview
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
        //c???p quy???n cho l???y ???nh t??? th?? vi???n
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
                //l???y ???nh r add v??
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
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        addTinhThanh();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
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
                Intent intent=getIntent();
                maNhaDat=intent.getStringExtra("maNhaDat");
                NhaDat nhaDat1=new NhaDatDAO(getApplicationContext()).getMa(maNhaDat);
                if (nhaDat1.getLoaiNha()==0){
                    boolean check=new NhaDatDAO(getApplicationContext()).kiemTra(tenNhaDat);
                    if (tenNhaDat.isEmpty()||tinhThanh.isEmpty()||diachi.isEmpty()||giatien.isEmpty()||dientich.isEmpty()||mota.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
                    }else if(tenNhaDat.equals(" ")||tinhThanh.equals(" ")||diachi.equals(" ")||giatien.equals(" ")||dientich.equals(" ")||mota.equals(" ")){
                        Toast.makeText(getApplicationContext(), "Kh??ng ???????c nh???p kho???ng c??ch kh??ng", Toast.LENGTH_SHORT).show();
                    }else if(tenNhaDat.length()<3){
                        Toast.makeText(getApplicationContext(), "T??n gi???i thi???u nh?? kh??ng ???????c qu?? ng???n", Toast.LENGTH_SHORT).show();
                    }else if(diachi.length()<5){
                        Toast.makeText(getApplicationContext(), "?????a ch??? kh??ng ???????c qu?? ng???n", Toast.LENGTH_SHORT).show();
                    }else if(giatien.length()<5){
                        Toast.makeText(getApplicationContext(), "Gi?? ti???n kh??ng qu?? nh???", Toast.LENGTH_SHORT).show();
                    } else if(check==true){
                        Toast.makeText(getApplicationContext(), "S???n ph???m ???? c?? r???i", Toast.LENGTH_SHORT).show();
                    }else{
                        int giaTien = Integer.parseInt(giatien);
                        if (giaTien==0){
                            Toast.makeText(getApplicationContext(), "Kh??ng ???????c nh???p gi?? l?? 0", Toast.LENGTH_SHORT).show();
                        }else {
                            Date ngayDang = java.sql.Date.valueOf(String.valueOf(now()));
                            //s???a th??ng tin nh?? ?????t
                            NhaDat nhaDat = new NhaDat(maNhaDat, tenNhaDat, null, tinhThanh, ngayDang, diachi, giaTien, dientich, mota, 0);
                            NhaDatDAO dao = new NhaDatDAO(getApplicationContext());
                            dao.update(nhaDat);
                            dialog.dismiss();
                            ganDuLieu();
                        }
                    }
                }else if (nhaDat1.getLoaiNha()==1){
                    boolean check=new NhaDatDAO(getApplicationContext()).kiemTra(tenNhaDat);
                    if (tenNhaDat.isEmpty()||tinhThanh.isEmpty()||diachi.isEmpty()||giatien.isEmpty()||dientich.isEmpty()||mota.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Kh??ng ???????c ????? tr???ng", Toast.LENGTH_SHORT).show();
                    }else if(tenNhaDat.equals(" ")||tinhThanh.equals(" ")||diachi.equals(" ")||giatien.equals(" ")||dientich.equals(" ")||mota.equals(" ")){
                        Toast.makeText(getApplicationContext(), "Kh??ng ???????c nh???p kho???ng c??ch kh??ng", Toast.LENGTH_SHORT).show();
                    }else if(tenNhaDat.length()<3){
                        Toast.makeText(getApplicationContext(), "T??n gi???i thi???u nh?? kh??ng ???????c qu?? ng???n", Toast.LENGTH_SHORT).show();
                    }else if(diachi.length()<5){
                        Toast.makeText(getApplicationContext(), "?????a ch??? kh??ng ???????c qu?? ng???n", Toast.LENGTH_SHORT).show();
                    }else if(giatien.length()<5){
                        Toast.makeText(getApplicationContext(), "Gi?? ti???n kh??ng qu?? nh???", Toast.LENGTH_SHORT).show();
                    }else if(check==true){
                        Toast.makeText(getApplicationContext(), "S???n ph???m ???? c?? r???i", Toast.LENGTH_SHORT).show();
                    }else{
                        int giaTien = Integer.parseInt(giatien);
                        if (giaTien==0){
                            Toast.makeText(getApplicationContext(), "Kh??ng ???????c nh???p gi?? l?? 0", Toast.LENGTH_SHORT).show();
                        }else {
                            Date ngayDang = java.sql.Date.valueOf(String.valueOf(now()));
                            //s???a th??ng tin nh?? ?????t
                            NhaDat nhaDat = new NhaDat(maNhaDat, tenNhaDat, null, tinhThanh, ngayDang, diachi, giaTien, dientich, mota, 1);
                            NhaDatDAO dao = new NhaDatDAO(getApplicationContext());
                            dao.update(nhaDat);
                            dialog.dismiss();
                            ganDuLieu();
                        }
                    }
                }


            }
        });


        dialog.show();
    }
    public void addTinhThanh(){
        tinhThanhList=new ArrayList<>();
        tinhThanhList.add(new TinhThanh("An Giang"));
        tinhThanhList.add(new TinhThanh("B?? R???a-V??ng T??u"));
        tinhThanhList.add(new TinhThanh("B???c Li??u"));
        tinhThanhList.add(new TinhThanh("B???c K???n"));
        tinhThanhList.add(new TinhThanh("B???c Giang"));
        tinhThanhList.add(new TinhThanh("B???c Ninh"));
        tinhThanhList.add(new TinhThanh("B???n Tre"));
        tinhThanhList.add(new TinhThanh("B??nh D????ng"));
        tinhThanhList.add(new TinhThanh("B??nh ?????nh"));
        tinhThanhList.add(new TinhThanh("B??nh Ph?????c"));
        tinhThanhList.add(new TinhThanh("B??nh Thu???n"));
        tinhThanhList.add(new TinhThanh("C?? Mau"));
        tinhThanhList.add(new TinhThanh("Cao B???ng"));
        tinhThanhList.add(new TinhThanh("C???n Th?? "));
        tinhThanhList.add(new TinhThanh("???? N???ng"));
        tinhThanhList.add(new TinhThanh("?????k L???k"));
        tinhThanhList.add(new TinhThanh("?????k N??ng"));
        tinhThanhList.add(new TinhThanh("??i???n Bi??n"));
        tinhThanhList.add(new TinhThanh("?????ng Nai"));
        tinhThanhList.add(new TinhThanh("?????ng Th??p"));
        tinhThanhList.add(new TinhThanh("Gia Lai"));
        tinhThanhList.add(new TinhThanh("H?? Giang"));
        tinhThanhList.add(new TinhThanh("H?? Nam"));
        tinhThanhList.add(new TinhThanh("H?? N???i"));
        tinhThanhList.add(new TinhThanh("H?? T??y"));
        tinhThanhList.add(new TinhThanh("H?? T??nh"));
        tinhThanhList.add(new TinhThanh("H???i D????ng"));
        tinhThanhList.add(new TinhThanh("H???i Ph??ng"));
        tinhThanhList.add(new TinhThanh("H??a B??nh"));
        tinhThanhList.add(new TinhThanh("H??? Ch?? Minh"));
        tinhThanhList.add(new TinhThanh("H???u Giang"));
        tinhThanhList.add(new TinhThanh("H??ng Y??n"));
        tinhThanhList.add(new TinhThanh("Kh??nh H??a"));
        tinhThanhList.add(new TinhThanh("Ki??n Giang"));
        tinhThanhList.add(new TinhThanh("Kon Tum"));
        tinhThanhList.add(new TinhThanh("Lai Ch??u"));
        tinhThanhList.add(new TinhThanh("L??o Cai"));
        tinhThanhList.add(new TinhThanh("L???ng S??n"));
        tinhThanhList.add(new TinhThanh("L??m ?????ng"));
        tinhThanhList.add(new TinhThanh("Long An"));
        tinhThanhList.add(new TinhThanh("Nam ?????nh"));
        tinhThanhList.add(new TinhThanh("Ngh??? An"));
        tinhThanhList.add(new TinhThanh("Ninh B??nh"));
        tinhThanhList.add(new TinhThanh("Ninh Thu???n"));
        tinhThanhList.add(new TinhThanh("Ph?? Th???"));
        tinhThanhList.add(new TinhThanh("Ph?? Y??n"));
        tinhThanhList.add(new TinhThanh("Qu???ng B??nh"));
        tinhThanhList.add(new TinhThanh("Qu???ng Nam"));
        tinhThanhList.add(new TinhThanh("Qu???ng Ng??i"));
        tinhThanhList.add(new TinhThanh("Qu???ng Ninh"));
        tinhThanhList.add(new TinhThanh("Qu???ng Tr???"));
        tinhThanhList.add(new TinhThanh("S??c Tr??ng"));
        tinhThanhList.add(new TinhThanh("S??n La"));
        tinhThanhList.add(new TinhThanh("T??y Ninh"));
        tinhThanhList.add(new TinhThanh("Th??i B??nh"));
        tinhThanhList.add(new TinhThanh("Th??i Nguy??n"));
        tinhThanhList.add(new TinhThanh("Thanh H??a"));
        tinhThanhList.add(new TinhThanh("Th???a Thi??n ??? Hu???"));
        tinhThanhList.add(new TinhThanh("Ti???n Giang"));
        tinhThanhList.add(new TinhThanh("Tr?? Vinh"));
        tinhThanhList.add(new TinhThanh("Tuy??n Quang"));
        tinhThanhList.add(new TinhThanh("V??nh Long"));
        tinhThanhList.add(new TinhThanh("V??nh Ph??c"));
        tinhThanhList.add(new TinhThanh("Y??n B??i"));

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