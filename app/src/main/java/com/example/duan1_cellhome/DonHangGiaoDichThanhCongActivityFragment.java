package com.example.duan1_cellhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.duan1_cellhome.Adapter.DonHangAdapter;
import com.example.duan1_cellhome.DAO.DonHangDAO;
import com.example.duan1_cellhome.Model.DonHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class DonHangGiaoDichThanhCongActivityFragment extends Fragment {
    List<DonHang> donHangList = new ArrayList<>();
    GridView gridViewDonHang;
    DonHangAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_list_donhang,container,false);
        gridViewDonHang=view.findViewById(R.id.gvDonHang);
        //load dữ liệu đơn hàng gdtc lên gridview
        donHangList=new DonHangDAO(getContext()).getDonHangGiaoDichThanhCong();
        adapter=new DonHangAdapter(getContext(),donHangList);
        gridViewDonHang.setNumColumns(1);
        gridViewDonHang.setAdapter(adapter);


        return view;
    }

}
