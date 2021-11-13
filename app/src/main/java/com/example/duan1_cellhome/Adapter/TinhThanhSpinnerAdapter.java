package com.example.duan1_cellhome.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.Model.TinhThanh;
import com.example.duan1_cellhome.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class TinhThanhSpinnerAdapter extends BaseAdapter {
    Context context;
    List<TinhThanh> tinhThanhList;

    public TinhThanhSpinnerAdapter(Context context, List<TinhThanh> tinhThanhList) {
        this.context = context;
        this.tinhThanhList = tinhThanhList;
    }

    @Override
    public int getCount() {
        return tinhThanhList.size();
    }

    @Override
    public Object getItem(int position) {
        return tinhThanhList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if (convertView==null){
            view= View.inflate(viewGroup.getContext(), R.layout.layout_spinner_tinhthanh,null);

        }
        TinhThanh tinhThanh= (TinhThanh) getItem(position);
        TextView txtTinhThanh=view.findViewById(R.id.txtTenTinhThanh);
        txtTinhThanh.setText(tinhThanh.getTenTinhThanh());

        return view;
    }
}
