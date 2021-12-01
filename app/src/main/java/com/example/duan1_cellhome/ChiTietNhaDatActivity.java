package com.example.duan1_cellhome;

import static java.time.LocalDate.now;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_cellhome.Adapter.HinhAdapter;
import com.example.duan1_cellhome.Adapter.NhaDatAdapter;
import com.example.duan1_cellhome.Adapter.UpNhiuHinhAdapter;
import com.example.duan1_cellhome.DAO.DonHangDAO;
import com.example.duan1_cellhome.DAO.HinhDAO;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.Model.Hinh;
import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.Model.Thanhvien;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class ChiTietNhaDatActivity extends AppCompatActivity {
    HinhAdapter upHinhAdapter;
    GridView gvHinh;
    String maNhaDat;
    TextView txttenGT,txtmaNhaDat,txtmoTa,txtgiaTien,txtdiaChi;
    ImageView imgHinh,imgUpNhieuHinh,imgThoat,imgReload;
    List<Hinh> list;
    Animation animationZoom;
    Button btnMua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nha_dat);
        AnhXa();
        ganDuLieu();

    }

    private void ganDuLieu() {
        Intent intent=getIntent();
        maNhaDat=intent.getStringExtra("maNhaDat");
        NhaDat nhaDat=new NhaDatDAO(this).getMa(maNhaDat);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        txttenGT.setText(nhaDat.getTenGT());
        txtmaNhaDat.setText(nhaDat.getMaNhaDat());
        txtmoTa.setText(nhaDat.getMoTa());
        txtmoTa.setMovementMethod(new ScrollingMovementMethod());
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
        //xét hình lên gridview
        list=new HinhDAO(this).getHinh(maNhaDat);
        upHinhAdapter=new HinhAdapter(getApplicationContext(),list);
        gvHinh.setNumColumns(6);
        gvHinh.setAdapter(upHinhAdapter);
        gvHinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //khi bấm vô item thì hiện tấm hình đó
                Hinh hinh= (Hinh) upHinhAdapter.getItem(i);
                dialogHienThiHinh(hinh.getHinh());
            }
        });
        btnMua.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogXoa=new AlertDialog.Builder(ChiTietNhaDatActivity.this);
                dialogXoa.setMessage("Bạn có muốn đăng ký mua:  "+nhaDat.getTenGT()+" này không");
                dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username=intent.getStringExtra("tenTK");
                        Thanhvien thanhvien=new ThanhvienDao(getApplicationContext()).getDuLieu(username);
                        DonHangDAO dao=new DonHangDAO(getApplicationContext());
                        Date ngayDang= java.sql.Date.valueOf(String.valueOf(now()));
                        Random random=new Random();
                        int maDonHang=random.nextInt(61);
                        Boolean check=new DonHangDAO(getApplicationContext()).kiemtradangkyDonHang(thanhvien.getMatv(),nhaDat.getMaNhaDat());
                        if (check==true){
                            Toast.makeText(getApplicationContext(), "Bạn đã đăng ký mua nhà này rồi. Xin vui lòng đợi giao dịch ", Toast.LENGTH_SHORT).show();
                        }else{
                            //thêm đơn hàng khi người dùng bấm mua
                            DonHang donHang=new DonHang(maDonHang+"",thanhvien.getMatv(),nhaDat.getMaNhaDat(),thanhvien.getSoDT(),nhaDat.getTenGT(),nhaDat.getHinh(),nhaDat.getDiaChi(),nhaDat.getGiaTien(),1,ngayDang);
                            dao.insert(donHang);
                            Toast.makeText(getApplicationContext(), "Bạn đã đăng ký mua nhà này", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
                dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialogXoa.show();
            }
        });
        imgThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        gvHinh=findViewById(R.id.gvNhieuHinh);

        imgThoat=findViewById(R.id.imgThoat);
        btnMua=findViewById(R.id.btnMua);
        animationZoom= AnimationUtils.loadAnimation(this,R.anim.animation_zoom_in);
    }

    public void dialogHienThiHinh(byte[] hinh) {
        Dialog dialog = new Dialog(ChiTietNhaDatActivity.this);
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