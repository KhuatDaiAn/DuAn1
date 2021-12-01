package com.example.duan1_cellhome.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_cellhome.DonHangFragment;
import com.example.duan1_cellhome.DonHangNguoiDungFragment;
import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class XemDonHangAdapter extends BaseAdapter {
    DonHangNguoiDungFragment donHangNguoiDungFragment;
    Context context;
    List<DonHang> donHangList;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");


//    public XemDonHangAdapter(DonHangNguoiDungFragment donHangNguoiDungFragment, List<DonHang> donHangList) {
//        this.donHangNguoiDungFragment = donHangNguoiDungFragment;
//        this.donHangList = donHangList;
//    }
    public XemDonHangAdapter(Context context, List<DonHang> donHangList) {
        this.context = context;
        this.donHangList = donHangList;
    }


    @Override
    public int getCount() {
        return donHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return donHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.layout_item_donhang, null);

        }

        DonHang donHang= (DonHang) getItem(position);
        // ánh xạ
        TextView txtMaDonhang = view.findViewById(R.id.txtMaDonHang);
        TextView txtTenGT=view.findViewById(R.id.txtTenGT);
        TextView txtTinhThanh=view.findViewById(R.id.txtTinhThanh);
        TextView txtGiaTien=view.findViewById(R.id.txtGiaTien);
        TextView txtTrangThai=view.findViewById(R.id.txtTrangThai);
        TextView txtngayDang=view.findViewById(R.id.txtNgay);
        ImageView imgHinhND =(ImageView) view.findViewById(R.id.imgHinhND);
        ImageView imgHuy =(ImageView) view.findViewById(R.id.imgHuy);
        ImageView imgSua =(ImageView) view.findViewById(R.id.imgSua);

        txtMaDonhang.setText(donHang.getMaDonHang());
        txtTenGT.setText(donHang.getTenGTND());
        txtTinhThanh.setText(donHang.getDiaChiND());
        txtngayDang.setText(sdf.format(donHang.getNgay()));
        txtGiaTien.setText(donHang.getGiaTienND()+"");

        imgSua.setVisibility(view.GONE);

        byte[] img=donHang.getHinhND();
        if(img == null){
            imgHinhND.setImageResource(R.drawable.nha1);
        }else{
            imgHinhND.setImageBitmap(BitmapFactory.decodeByteArray(img,0,img.length));
        }
        if (donHang.getTrangThai()==1){
            txtTrangThai.setText("Đang chờ giao dịch");
        }


        if (donHang.getTrangThai()==0){
            txtTrangThai.setText("Đã giao dịch");
        }

        imgHuy.setVisibility(view.GONE);
//        imgHuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                donHangNguoiDungFragment.DialogHuyDonHang(donHang.getMaDonHang());
//            }
//        });
        return view;
    }

}
