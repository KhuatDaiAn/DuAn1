package com.example.duan1_cellhome.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.R;

import java.util.List;

public class DonHangAdapter extends BaseAdapter {
    Context context;
    List<DonHang> donHangList;

    public DonHangAdapter(Context context, List<DonHang> donHangList) {
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

        TextView txtMaDonhang = view.findViewById(R.id.txtMaDonHang);
        TextView txtTenGT=view.findViewById(R.id.txtTenGT);
        TextView txtTinhThanh=view.findViewById(R.id.txtTinhThanh);
        TextView txtGiaTien=view.findViewById(R.id.txtGiaTien);
        ImageView imgHinhND =(ImageView) view.findViewById(R.id.imgHinhND);

        txtMaDonhang.setText(donHang.getMaDonHang());
        txtTenGT.setText(donHang.getTenGTND());
        txtTinhThanh.setText(donHang.getDiaChiND());
        txtGiaTien.setText(donHang.getGiaTienND()+"");
        byte[] img=donHang.getHinhND();
        if(img == null){
            imgHinhND.setImageResource(R.drawable.nha1);
        }else{
            imgHinhND.setImageBitmap(BitmapFactory.decodeByteArray(img,0,img.length));
        }

        return view;
    }

}
