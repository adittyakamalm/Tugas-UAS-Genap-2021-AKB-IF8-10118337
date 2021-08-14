package com.example.a10118337_uas_akb.view;
// Adittya Kamal Mahardin
// IF8
// 10118337
import android.content.Intent;
import android.os.Bundle;

import com.example.a10118337_uas_akb.R;
import com.example.a10118337_uas_akb.view.adapter.ViewPagerAdapter;
import com.example.a10118337_uas_akb.view.fragment.AboutFragment;
import com.example.a10118337_uas_akb.view.fragment.HomeFragment;
import com.example.a10118337_uas_akb.view.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.example.a10118337_uas_akb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    HomeFragment fragHome;
    ProfileFragment fragProfile;
    AboutFragment fragAbout;
    MapsActivity fragMaps;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);
        setupViewPager(viewPager);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_profile:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_about:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }

        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                menuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragHome = new HomeFragment();
        fragProfile = new ProfileFragment();
        fragAbout = new AboutFragment();

        adapter.addFragment(fragHome);
        adapter.addFragment(fragProfile);
        adapter.addFragment(fragAbout);

        viewPager.setAdapter(adapter);
    }

}