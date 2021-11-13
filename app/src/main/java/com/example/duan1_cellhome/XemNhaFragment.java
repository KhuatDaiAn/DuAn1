package com.example.duan1_cellhome;

import static java.time.LocalDate.now;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.duan1_cellhome.Adapter.NhaDatAdapter;
import com.example.duan1_cellhome.Adapter.TinhThanhSpinnerAdapter;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.Model.TinhThanh;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XemNhaFragment extends Fragment {
    List<NhaDat> list;
    GridView gridViewNhaDat;
    NhaDatAdapter adapter;
    ImageView imgThem;
    Spinner spinnerTimKiem;
    List<TinhThanh> tinhThanhList;
    String selectedTinhThanh=null;
    Button btnTim;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_xem_list_nhadat,container,false);
        gridViewNhaDat=view.findViewById(R.id.gvNhaDat);
        imgThem=view.findViewById(R.id.imgThemNhaDat);
        btnTim=view.findViewById(R.id.btnTim);
        spinnerTimKiem=view.findViewById(R.id.spinnerTimKiemTinhThanh);
        list=new NhaDatDAO(getContext()).getNha();
        adapter=new NhaDatAdapter(getContext(),list);
        gridViewNhaDat.setNumColumns(2);
        gridViewNhaDat.setAdapter(adapter);
        addTinhThanh();

        gridViewNhaDat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NhaDat nhaDat= (NhaDat) adapter.getItem(i);
                Intent intent = new Intent(getContext(), ChiTietNhaDatActivity.class);
                intent.putExtra("maNhaDat",nhaDat.getMaNhaDat());
                startActivity(intent);
            }
        });
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTinhThanh.equals("Tất cả")){
                    list=new NhaDatDAO(getContext()).getNha();
                    adapter=new NhaDatAdapter(getContext(),list);
                    gridViewNhaDat.setNumColumns(2);
                    gridViewNhaDat.setAdapter(adapter);
                }else{
                list=new NhaDatDAO(getContext()).hienThiTheoTinhThanh(selectedTinhThanh);
                adapter=new NhaDatAdapter(getContext(),list);
                gridViewNhaDat.setNumColumns(2);
                gridViewNhaDat.setAdapter(adapter);
            }
            }
        });
        return view;

    }


    public void addTinhThanh(){
        tinhThanhList=new ArrayList<>();
        tinhThanhList.add(new TinhThanh("Tất cả"));
        tinhThanhList.add(new TinhThanh("An Giang"));
        tinhThanhList.add(new TinhThanh("Bà Rịa-Vũng Tàu"));
        tinhThanhList.add(new TinhThanh("Bạc Liêu"));
        tinhThanhList.add(new TinhThanh("Bắc Kạn"));
        tinhThanhList.add(new TinhThanh("Bắc Giang"));
        tinhThanhList.add(new TinhThanh("Bắc Ninh"));
        tinhThanhList.add(new TinhThanh("Bến Tre"));
        tinhThanhList.add(new TinhThanh("Bình Dương"));
        tinhThanhList.add(new TinhThanh("Bình Định"));
        tinhThanhList.add(new TinhThanh("Bình Phước"));
        TinhThanhSpinnerAdapter tinhThanhspinner=new TinhThanhSpinnerAdapter(getContext(),tinhThanhList);
        spinnerTimKiem.setAdapter(tinhThanhspinner);
        spinnerTimKiem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TinhThanh tinhThanh = (TinhThanh) tinhThanhspinner.getItem(position);
                selectedTinhThanh=tinhThanh.getTenTinhThanh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTinhThanh=null;
            }
        });
    }

}
