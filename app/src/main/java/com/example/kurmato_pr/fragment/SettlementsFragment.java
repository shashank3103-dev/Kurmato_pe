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

SliderView sliderView;
    String url1 = "https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png";
    String url2 = "https://qphs.fs.quoracdn.net/main-qimg-8e203d34a6a56345f86f1a92570557ba.webp";
    String url3 = "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png";

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
        sliderView = myfragment.findViewById(R.id.imageSlider);

        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));

        ImageSliderAdapter adapter = new ImageSliderAdapter(this, sliderDataArrayList);

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();

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
