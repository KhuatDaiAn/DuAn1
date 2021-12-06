package com.example.duan1_cellhome;

import static java.time.LocalDate.now;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1_cellhome.Adapter.NhaDatAdapter;
import com.example.duan1_cellhome.Adapter.TinhThanhSpinnerAdapter;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.Model.TinhThanh;

import java.util.ArrayList;
import java.util.List;

public class XemDatFragment extends Fragment {
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
        spinnerTimKiem=view.findViewById(R.id.spTimKiemTinhThanh);
        btnTim = view.findViewById(R.id.btnTim);
        //load dữ liệu đất lên gridview
        list=new NhaDatDAO(getContext()).getDat();
        adapter=new NhaDatAdapter(getContext(),list);
        gridViewNhaDat.setNumColumns(2);
        gridViewNhaDat.setAdapter(adapter);
        //hàm thêm 64 tỉnh thành
        addTinhThanh();
        Intent intent=getActivity().getIntent();
        String username=intent.getStringExtra("tenTK");
        gridViewNhaDat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NhaDat nhaDat= (NhaDat) adapter.getItem(i);
                Intent intent = new Intent(getContext(), ChiTietNhaDatActivity.class);
                intent.putExtra("maNhaDat",nhaDat.getMaNhaDat());
                intent.putExtra("tenTK",username);
                startActivity(intent);
            }
        });
        // tìm theo kết quả tìm kiếm
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTinhThanh.equals("Tất cả")){
                    list=new NhaDatDAO(getContext()).getDat();
                    adapter=new NhaDatAdapter(getContext(),list);
                    gridViewNhaDat.setNumColumns(2);
                    gridViewNhaDat.setAdapter(adapter);
                }else{
                    list=new NhaDatDAO(getContext()).hienThiDatTheoTinhThanh(selectedTinhThanh);
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
        tinhThanhList.add(new TinhThanh("Bình Thuận"));
        tinhThanhList.add(new TinhThanh("Cà Mau"));
        tinhThanhList.add(new TinhThanh("Cao Bằng"));
        tinhThanhList.add(new TinhThanh("Cần Thơ "));
        tinhThanhList.add(new TinhThanh("Đà Nẵng"));
        tinhThanhList.add(new TinhThanh("Đắk Lắk"));
        tinhThanhList.add(new TinhThanh("Đắk Nông"));
        tinhThanhList.add(new TinhThanh("Điện Biên"));
        tinhThanhList.add(new TinhThanh("Đồng Nai"));
        tinhThanhList.add(new TinhThanh("Đồng Tháp"));
        tinhThanhList.add(new TinhThanh("Gia Lai"));
        tinhThanhList.add(new TinhThanh("Hà Giang"));
        tinhThanhList.add(new TinhThanh("Hà Nam"));
        tinhThanhList.add(new TinhThanh("Hà Nội"));
        tinhThanhList.add(new TinhThanh("Hà Tây"));
        tinhThanhList.add(new TinhThanh("Hà Tĩnh"));
        tinhThanhList.add(new TinhThanh("Hải Dương"));
        tinhThanhList.add(new TinhThanh("Hải Phòng"));
        tinhThanhList.add(new TinhThanh("Hòa Bình"));
        tinhThanhList.add(new TinhThanh("Hồ Chí Minh"));
        tinhThanhList.add(new TinhThanh("Hậu Giang"));
        tinhThanhList.add(new TinhThanh("Hưng Yên"));
        tinhThanhList.add(new TinhThanh("Khánh Hòa"));
        tinhThanhList.add(new TinhThanh("Kiên Giang"));
        tinhThanhList.add(new TinhThanh("Kon Tum"));
        tinhThanhList.add(new TinhThanh("Lai Châu"));
        tinhThanhList.add(new TinhThanh("Lào Cai"));
        tinhThanhList.add(new TinhThanh("Lạng Sơn"));
        tinhThanhList.add(new TinhThanh("Lâm Đồng"));
        tinhThanhList.add(new TinhThanh("Long An"));
        tinhThanhList.add(new TinhThanh("Nam Định"));
        tinhThanhList.add(new TinhThanh("Nghệ An"));
        tinhThanhList.add(new TinhThanh("Ninh Bình"));
        tinhThanhList.add(new TinhThanh("Ninh Thuận"));
        tinhThanhList.add(new TinhThanh("Phú Thọ"));
        tinhThanhList.add(new TinhThanh("Phú Yên"));
        tinhThanhList.add(new TinhThanh("Quảng Bình"));
        tinhThanhList.add(new TinhThanh("Quảng Nam"));
        tinhThanhList.add(new TinhThanh("Quảng Ngãi"));
        tinhThanhList.add(new TinhThanh("Quảng Ninh"));
        tinhThanhList.add(new TinhThanh("Quảng Trị"));
        tinhThanhList.add(new TinhThanh("Sóc Trăng"));
        tinhThanhList.add(new TinhThanh("Sơn La"));
        tinhThanhList.add(new TinhThanh("Tây Ninh"));
        tinhThanhList.add(new TinhThanh("Thái Bình"));
        tinhThanhList.add(new TinhThanh("Thái Nguyên"));
        tinhThanhList.add(new TinhThanh("Thanh Hóa"));
        tinhThanhList.add(new TinhThanh("Thừa Thiên – Huế"));
        tinhThanhList.add(new TinhThanh("Tiền Giang"));
        tinhThanhList.add(new TinhThanh("Trà Vinh"));
        tinhThanhList.add(new TinhThanh("Tuyên Quang"));
        tinhThanhList.add(new TinhThanh("Vĩnh Long"));
        tinhThanhList.add(new TinhThanh("Vĩnh Phúc"));
        tinhThanhList.add(new TinhThanh("Yên Bái"));
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
