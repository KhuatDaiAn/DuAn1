package com.example.duan1_cellhome.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duan1_cellhome.Model.Thanhvien;
import com.example.duan1_cellhome.R;

import java.util.List;

public class ThanhvienAdapter extends BaseAdapter {
    Context context;
    List<Thanhvien> thanhvienList;

    public ThanhvienAdapter(Context context, List<Thanhvien> thanhvienList) {
        this.context = context;
        this.thanhvienList = thanhvienList;
    }

    @Override
    public int getCount() {

        return thanhvienList.size();
    }

    @Override
    public Object getItem(int i) {
        return thanhvienList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = View.inflate(viewGroup.getContext(), R.layout.item_thanhvien,null);

        }
        Thanhvien thanhvien = (Thanhvien)getItem(i) ;
        TextView txtMaTv = view.findViewById(R.id.txtMaTV);
        TextView txtNamSinh= view.findViewById(R.id.txtNamSinh);
        TextView txtHoTen = view.findViewById(R.id.txtHoTen);
        TextView txtSoDT = view.findViewById(R.id.txtSoDT);

        txtMaTv.setText(thanhvien.getMatv());
        txtNamSinh.setText(thanhvien.getNamSinh());
        txtHoTen.setText(thanhvien.getHoTen());
        txtSoDT.setText(thanhvien.getSoDT()+"");

        return view;
    }
}
