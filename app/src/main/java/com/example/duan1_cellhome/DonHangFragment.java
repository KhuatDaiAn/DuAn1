package com.example.duan1_cellhome;

import static java.time.LocalDate.now;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.example.duan1_cellhome.Adapter.XemDonHangAdapter;
import com.example.duan1_cellhome.DAO.DonHangDAO;
import com.example.duan1_cellhome.DAO.NhaDatDAO;
import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.DonHang;
import com.example.duan1_cellhome.Model.NhaDat;
import com.example.duan1_cellhome.Model.Thanhvien;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DonHangFragment extends Fragment {
    List<DonHang> donHangList = new ArrayList<>();
    List<DonHang> list;
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
        gridViewDonHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                DonHang donHang= (DonHang) adapter.getItem(i);
                //xóa đơn hàng
                DialogHuyDonHang(donHang.getMaDonHang());
                return false;
            }
        });
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

    // hoàn thành giao dịch
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
                //update thành trạng thái giao dịch thành công
                DonHang donHang=new DonHang(maDonHang,null,null,03103123,"null",null,"null",1231,trangThai,ngayDang);
                DonHangDAO dao=new DonHangDAO(getContext());
                dao.updateTrangThai(donHang);
                //xóa nhà đất khi giao dịch thành công
                DonHang donHang1=new DonHangDAO(getContext()).getMaDonHang(maDonHang);
                NhaDatDAO nhaDatDAO=new NhaDatDAO(getContext());
                nhaDatDAO.delete(donHang1.getMaNhaDat());
                //load lại danh sách đơn hàng
                donHangList=new DonHangDAO(getContext()).getDonHang();
                adapter=new DonHangAdapter(getContext(),donHangList);
                gridViewDonHang.setNumColumns(1);
                gridViewDonHang.setAdapter(adapter);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void DialogHuyDonHang(String maDonHang) {
        AlertDialog.Builder dialogHuy=new AlertDialog.Builder(getContext());
        dialogHuy.setMessage("Bạn có muốn hủy đơn hàng này không");
        dialogHuy.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=getActivity().getIntent();
                String username=intent.getStringExtra("tenTK");
                Thanhvien thanhvien=new ThanhvienDao(getContext()).getDuLieu(username);
                DonHangDAO donHangDAO = new DonHangDAO(getContext());
                // xóa đơn hàng
                donHangDAO.delete(maDonHang);
                list = new DonHangDAO(getContext()).getDonHangCaNhan(thanhvien.getMatv());
                adapter = new DonHangAdapter(getContext(),list);
                gridViewDonHang.setNumColumns(1);
                gridViewDonHang.setAdapter(adapter);
            }
        });
        dialogHuy.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogHuy.show();

    }
}
