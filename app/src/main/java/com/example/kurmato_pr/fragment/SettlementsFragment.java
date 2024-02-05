package com.example.kurmato_pr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kurmato_pr.Adapter.ImageSliderAdapter;
import com.example.kurmato_pr.Adapter.ViewPageAdapter;
import com.example.kurmato_pr.R;
import com.example.kurmato_pr.utils.SliderData;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class SettlementsFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager viewPager;
    View myfragment;
    ViewPageAdapter viewPageAdapter;


    public SettlementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.

        myfragment = inflater.inflate(R.layout.fragment_settllements, container, false);
        viewPager = myfragment.findViewById(R.id.viewPager);
        tabLayout = myfragment.findViewById(R.id.tabLayout);
        return myfragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Handle tab selection
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle tab unselection
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle tab reselection
            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        viewPageAdapter = new ViewPageAdapter(getChildFragmentManager());
        viewPageAdapter.addFragemnt(new settlement_tab(), "Settlement tab");
        viewPageAdapter.addFragemnt(new TXNS_fragment(), "TXNS");
        viewPager.setAdapter(viewPageAdapter);
    }

}
