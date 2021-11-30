package com.example.duan1_cellhome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.duan1_cellhome.DAO.ThanhvienDao;
import com.example.duan1_cellhome.Model.Thanhvien;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1_cellhome.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
//test
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TextView tenTk, hoTen;
    private View headerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_thanhvien,R.id.nav_donHang,R.id.nav_top10,R.id.nav_thongkedoanhthu)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
                if(destination.getId() == R.id.nav_thoat){
                    //bỏ trạng thái nhớ đăng nhập
                    SharedPreferences.Editor editor=getSharedPreferences("login_status",MODE_PRIVATE).edit();
                    editor.putBoolean("isLoggedAdmin",false);
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}