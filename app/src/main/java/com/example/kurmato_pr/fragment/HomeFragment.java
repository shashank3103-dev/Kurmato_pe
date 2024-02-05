package com.example.kurmato_pr.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kurmato_pr.Adapter.ImageSliderAdapter;
import com.example.kurmato_pr.Adapter.ViewPageAdapter;
import com.example.kurmato_pr.AddMoney;
import com.example.kurmato_pr.R;
import com.example.kurmato_pr.SendMoney;
import com.example.kurmato_pr.utils.SliderData;
import com.example.kurmato_pr.walletTowallet;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import static com.example.kurmato_pr.databinding.ActivityHomePageBinding.inflate;


public class HomeFragment extends Fragment {
    // Urls of our images.
    String url1 = "https://upload.wikimedia.org/wikipedia/commons/a/a9/AEPS-Logo.png";
    String url2 = "https://himanidigital.com/Website/assets/img/aeps.jpg";
    String url3 = "https://blog.m2pfintech.com/wp-content/uploads/2022/07/Creative-AePS-Blog-03.jpg";
    ViewPager viewPager;
    View myfragment;
    ViewPageAdapter viewPageAdapter;
    SliderView sliderView;
    ImageView addMoney,sendMoney, wallettowallet,movieTicket;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        myfragment= inflater.inflate(R.layout.fragment_home, container, false);

        // initializing the slider view.
        SliderView sliderView = myfragment.findViewById(R.id.imageSlider);
        ImageView addmoney = myfragment.findViewById(R.id.wallet_1);
        ImageView sendMoney = myfragment.findViewById(R.id.wallet_2);
        ImageView walletTowallet = myfragment.findViewById(R.id.wallet_3);
        ImageView movieTicket = myfragment.findViewById(R.id.movieTicket);

      addmoney.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(getContext(), AddMoney.class);
              startActivity(intent);


          }
      });

      sendMoney.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(getContext(), SendMoney.class);
              startActivity(intent);
          }
      });
      walletTowallet.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(getContext(), walletTowallet.class);
              startActivity(intent);

          }
      });
      
      movieTicket.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      });

        // adding the urls inside array list
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));

        // passing this array list inside our adapter class.
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


}
