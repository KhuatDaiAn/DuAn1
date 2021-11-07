package com.example.duan1_cellhome.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.duan1_cellhome.Model.Hinh;
import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.R;

import java.util.List;

public class HinhAdapter extends BaseAdapter {
    Context context;

    List<Hinh> hinhList;

    public HinhAdapter(Context context, List<Hinh> hinhList) {
        this.context = context;
        this.hinhList = hinhList;
    }

    @Override
    public int getCount() {
        return hinhList.size();
    }

    @Override
    public Object getItem(int position) {
        return hinhList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view=convertView;
        if (convertView==null){
            view= View.inflate(viewGroup.getContext(), R.layout.item_hinh,null);

        }
        Hinh hinh= (Hinh) getItem(position);
        ImageView imgHinh=(ImageView) view.findViewById(R.id.img_uphinh);
        byte[] imageArray=hinh.getHinh();
        if(imageArray==null){
            imgHinh.setImageResource(R.drawable.nha1);
        }else{
            imgHinh.setImageBitmap(BitmapFactory.decodeByteArray(imageArray,0,imageArray.length));
        }
        return view;
    }
}
