package com.example.duan1_cellhome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.Thanhvien;
import com.example.duan1_cellhome.databinding.ActivityMenunguoidungBinding;
import com.google.android.material.navigation.NavigationView;


public class MenuNguoiDung extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenunguoidungBinding binding;
    private TextView tenTk, hoTen;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenunguoidungBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNguoidung.toolbar);

        DrawerLayout drawer = binding.drawerLayoutnguoidung;
        NavigationView navigationView = binding.navViewnguoidung;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_homenguoidung, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_donHangNguoiDung,R.id.nav_top10,R.id.nav_thongTinCaNhan)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nguoidung);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
                if(destination.getId() == R.id.nav_thoat){
                    //bỏ trạng thái nhớ đăng nhập
                    SharedPreferences.Editor editor=getSharedPreferences("login_status",MODE_PRIVATE).edit();
                    editor.putBoolean("isLoggedNguoiDung",false);
                    editor.apply();
                    Intent intent=new Intent(getApplicationContext(), DangNhapActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        Intent intent = getIntent();
        String ten = intent.getStringExtra("tenTK");
        Log.d("==========>", "onCreate: "+ten);
        headerView = navigationView.getHeaderView(0);
        Thanhvien thanhvien=new ThanhvienDao(getApplicationContext()).getDuLieu(ten);
        tenTk = headerView.findViewById(R.id.txtTenHeader);
        hoTen = headerView.findViewById(R.id.txtTenNguoiDungHeader);
        hoTen.setText(thanhvien.getHoTen());
        tenTk.setText(ten);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nguoidung);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}
