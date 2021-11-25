package com.example.duan1_cellhome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThongTinCaNhanFragment extends Fragment {


    public ThongTinCaNhanFragment() {
        // Required empty public constructor
    }

    public static ThongTinCaNhanFragment newInstance() {
        ThongTinCaNhanFragment fragment = new ThongTinCaNhanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_thong_tin_ca_nhan, container, false);
    }
}