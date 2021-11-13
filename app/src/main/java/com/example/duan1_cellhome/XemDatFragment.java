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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.duan1_cellhome.Adapter.NhaDatAdapter;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.Model.NhaDat;

import java.sql.Date;
import java.util.List;
import java.util.Random;

public class XemDatFragment extends Fragment {
    List<NhaDat> list;
    GridView gridViewNhaDat;
    NhaDatAdapter adapter;
    ImageView imgThem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_xem_list_nhadat,container,false);
        gridViewNhaDat=view.findViewById(R.id.gvNhaDat);
        imgThem=view.findViewById(R.id.imgThemNhaDat);
        list=new NhaDatDAO(getContext()).getDat();
        adapter=new NhaDatAdapter(getContext(),list);
        gridViewNhaDat.setNumColumns(2);
        gridViewNhaDat.setAdapter(adapter);

        gridViewNhaDat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NhaDat nhaDat= (NhaDat) adapter.getItem(i);
                Intent intent = new Intent(getContext(), ChiTietNhaDatActivity.class);
                intent.putExtra("maNhaDat",nhaDat.getMaNhaDat());
                startActivity(intent);
            }
        });




        return view;

    }

}
