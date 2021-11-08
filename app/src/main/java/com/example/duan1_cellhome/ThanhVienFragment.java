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
import com.example.duan1_cellhome.Adapter.ThanhvienAdapter;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Database.Database;
import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.Model.Thanhvien;

import java.sql.Date;
import java.util.List;
import java.util.Random;

public class ThanhVienFragment extends Fragment {
    List<Thanhvien>thanhvienList;
    GridView gridViewThanhVien;
    ThanhvienAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_list_thanhvien,container,false);
        gridViewThanhVien = view.findViewById(R.id.gvThanhVien);
        thanhvienList = new ThanhvienDao(getContext()).getAll();
        adapter = new ThanhvienAdapter(getContext(),thanhvienList);
        gridViewThanhVien.setNumColumns(1);
        gridViewThanhVien.setAdapter(adapter);



        return view;

    }


}
