package com.example.duan1_cellhome.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1_cellhome.DonHangFragment;
import com.example.duan1_cellhome.DonHangGiaoDichThanhCongActivityFragment;


public class ViewDonHang_GiaoDichAdapter extends FragmentStateAdapter {
    public ViewDonHang_GiaoDichAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new DonHangFragment();
            case 1:
                return new DonHangGiaoDichThanhCongActivityFragment();

            default:
                return new DonHangFragment();
        }


        //return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
