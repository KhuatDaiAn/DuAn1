package com.example.duan1_cellhome.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.duan1_cellhome.Model.Top;
import com.example.duan1_cellhome.R;

import java.util.List;

public class ThongKeTop10Adapter extends BaseAdapter {
    Context context;
    List<Top> topList;

    public ThongKeTop10Adapter(Context context, List<Top> topList) {
        this.context = context;
        this.topList = topList;
    }

    @Override
    public int getCount() {
        return topList.size();
    }

    @Override
    public Object getItem(int position) {
        return topList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if (convertView==null){
            view=View.inflate(viewGroup.getContext(), R.layout.layout_thongke_top10,null);

        }
        Top top= (Top) getItem(position);
        TextView txtTenTinh=view.findViewById(R.id.txtThongKeTenTinh);
        TextView txtSoLuong=view.findViewById(R.id.txtsoLuong);
        txtTenTinh.setText(top.getTenTinh());
        txtSoLuong.setText("Số lượng nhà đất: "+top.getSoLuong());
        return view;
    }
}
