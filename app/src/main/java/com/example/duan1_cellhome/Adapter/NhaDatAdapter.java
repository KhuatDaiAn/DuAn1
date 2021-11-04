package com.example.duan1_cellhome.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.R;

import java.util.List;

public class NhaDatAdapter extends BaseAdapter {
    Context context;
    List<NhaDat> nhaDatList;

    public NhaDatAdapter(Context context, List<NhaDat> loaiSachList) {
        this.context = context;
        this.nhaDatList = loaiSachList;
    }

    @Override
    public int getCount() {
        return nhaDatList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhaDatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if (convertView==null){
            view= View.inflate(viewGroup.getContext(), R.layout.layout_nhadat,null);

        }
        NhaDat nhaDat= (NhaDat) getItem(position);
        TextView txttenGT=view.findViewById(R.id.txttenGT);
        TextView txttinhThanh=view.findViewById(R.id.txttinhThanh);
        TextView txtdienTich=view.findViewById(R.id.txtdienTich);
        TextView txtgiaTien=view.findViewById(R.id.txtgiaTien);
        txttenGT.setText(nhaDat.getTenGT());
        txttinhThanh.setText(nhaDat.getTinhThanh());
        txtdienTich.setText(nhaDat.getDienTich());
        txtgiaTien.setText(nhaDat.getGiaTien());
        return view;
    }
}
