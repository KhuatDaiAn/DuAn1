package com.example.duan1_cellhome;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.duan1_cellhome.Adapter.DonHangAdapter;
import com.example.duan1_cellhome.Adapter.XemDonHangAdapter;
import com.example.duan1_cellhome.DAO.DonHangDAO;
import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.Model.Thanhvien;

import java.util.ArrayList;
import java.util.List;

public class DonHangNguoiDungFragment extends Fragment {
    List<DonHang> donHangList = new ArrayList<>();
    GridView gridViewDonHangNguoiDung;
    XemDonHangAdapter adapter;

    public DonHangNguoiDungFragment() {
        // Required empty public constructor
    }

    public static DonHangNguoiDungFragment newInstance() {
        DonHangNguoiDungFragment fragment = new DonHangNguoiDungFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_list_don_hang_nguoi_dung, container, false);
        //lấy dữ liệu từ intent
        Intent intent=getActivity().getIntent();
        String username=intent.getStringExtra("tenTK");
        //hàm lấy dữ liệu của 1 thành viên
        Thanhvien thanhvien=new ThanhvienDao(getContext()).getDuLieu(username);
        gridViewDonHangNguoiDung=view.findViewById(R.id.gvDonHangNguoiDung);
        //load đơn hàng của thành viên đó lên gridview
        donHangList=new DonHangDAO(getContext()).getDonHangCaNhan(thanhvien.getMatv());
        adapter=new XemDonHangAdapter(getContext(),donHangList);
        gridViewDonHangNguoiDung.setNumColumns(1);
        gridViewDonHangNguoiDung.setAdapter(adapter);
        gridViewDonHangNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
}