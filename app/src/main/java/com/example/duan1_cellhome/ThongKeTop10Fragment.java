package com.example.duan1_cellhome;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.duan1_cellhome.Adapter.ThongKeTop10Adapter;
import com.example.duan1_cellhome.DAO.ThongKeDao;
import com.example.duan1_cellhome.Model.Top;

import java.util.List;

public class ThongKeTop10Fragment extends Fragment {
    List<Top> list;
    ThongKeTop10Adapter adapter;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thongketop10_fragment,container,false);
        lv=view.findViewById(R.id.lvThongKeTop10);
        list=new ThongKeDao(getContext()).getTop();
        adapter=new ThongKeTop10Adapter(getContext(),list);
        lv.setAdapter(adapter);
        return view;
    }
}
