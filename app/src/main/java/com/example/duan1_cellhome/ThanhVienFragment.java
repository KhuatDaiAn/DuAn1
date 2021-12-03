package com.example.duan1_cellhome;

import static java.time.LocalDate.now;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
        //load dữ liệu thành viên lên gridview
        thanhvienList = new ThanhvienDao(getContext()).getAll();
        adapter = new ThanhvienAdapter(getContext(),thanhvienList);
        gridViewThanhVien.setNumColumns(1);
        gridViewThanhVien.setAdapter(adapter);
        gridViewThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //xóa thành viên
                Thanhvien thanhvien= (Thanhvien) adapter.getItem(i);
                DiaLogXoaThanhVien(thanhvien.getMatv());

                return false;
            }
        });


        return view;

    }
    public void DiaLogXoaThanhVien(String ten){
        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(getContext());
        dialogXoa.setMessage("Bạn có muốn xóa thành viên"+ten+" này không");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ThanhvienDao dao=new ThanhvienDao(getContext());
                dao.delete(ten);
                thanhvienList = new ThanhvienDao(getContext()).getAll();
                adapter = new ThanhvienAdapter(getContext(),thanhvienList);
                gridViewThanhVien.setNumColumns(1);
                gridViewThanhVien.setAdapter(adapter);
                dialog.dismiss();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogXoa.show();

    }


}
