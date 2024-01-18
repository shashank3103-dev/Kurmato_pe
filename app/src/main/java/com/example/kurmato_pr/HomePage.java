package com.example.kurmato_pr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.kurmato_pr.databinding.ActivityMainBinding;
import com.example.kurmato_pr.fragment.HomeFragment;
import com.example.kurmato_pr.fragment.ProfileFragment;
import com.example.kurmato_pr.fragment.ReportsFragment;
import com.example.kurmato_pr.fragment.SettlementsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;



public class HomePage extends AppCompatActivity   {
    ActivityMainBinding binding;

    ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private ImageView drawer;
    FrameLayout frameLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    private boolean isDrawerOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(R.layout.activity_home_page);

        bottomNavigationView =findViewById(R.id.bottomNavigationView);
        viewPager = findViewById(R.id.viewPager);
        frameLayout = findViewById(R.id.flFragment);
        drawerLayout = findViewById(R.id.drawerLayout);

        navigationView = findViewById(R.id.navigationView);
        loadHomeFragment();

        drawer = findViewById(R.id.menu_icon);

        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Drawer", "Image Clicked");
                if (isDrawerOpen) {
                    // Close the Navigation Drawer
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    // Open the Navigation Drawer
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                isDrawerOpen = true;
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                isDrawerOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }

        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                frameLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flFragment, new HomeFragment()).commit();
                    return true;
                } else if (itemId == R.id.profile) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flFragment, new ProfileFragment()).commit();
                    return true;

                }
                else if (itemId == R.id.settlements) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flFragment, new SettlementsFragment()).commit();
                    return true;
                }
                else if (itemId == R.id.reports) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flFragment, new ReportsFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }

    private void loadHomeFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment, new HomeFragment()) // Replace with your actual HomeFragment class
                .commit();
    }


}





