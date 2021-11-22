package com.example.duan1_cellhome;

import static java.time.LocalDate.now;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.duan1_cellhome.Adapter.DonHangAdapter;
import com.example.duan1_cellhome.DAO.DonHangDAO;
import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.Model.NhaDat;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DonHangFragment extends Fragment {
    List<DonHang> donHangList = new ArrayList<>();
    GridView gridViewDonHang;
    DonHangAdapter adapter;
    int trangThai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_list_donhang,container,false);
        gridViewDonHang=view.findViewById(R.id.gvDonHang);
        donHangList=new DonHangDAO(getContext()).getDonHang();
        adapter=new DonHangAdapter(this,donHangList);
        gridViewDonHang.setNumColumns(1);
        gridViewDonHang.setAdapter(adapter);

        gridViewDonHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DonHang donHang= (DonHang) adapter.getItem(i);
                Intent intent = new Intent(getContext(), DonHangChiTietActivity.class);
                intent.putExtra("maDonHang",donHang.getMaDonHang());
                startActivity(intent);
            }
        });

        return view;
    }

    public void dialogHoanTatGD(String maDonHang) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_sua_don_hang);
        dialog.setCanceledOnTouchOutside(false);
        CheckBox chkHoaThanh = dialog.findViewById(R.id.chkHoanTatGD);
        Button btnLuuGD=dialog.findViewById(R.id.btnLuuGD);
        Button btnCancel = dialog.findViewById(R.id.btnBo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLuuGD.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (chkHoaThanh.isChecked()){
                    trangThai=0;
                }else {
                    trangThai=1;
                }
                Date ngayDang= java.sql.Date.valueOf(String.valueOf(now()));
                DonHang donHang=new DonHang(maDonHang,null,null,03103123,"null",null,"null",1231,trangThai,ngayDang);
                DonHangDAO dao=new DonHangDAO(getContext());
                dao.updateTrangThai(donHang);
            }
        });

        dialog.show();
    }

}
