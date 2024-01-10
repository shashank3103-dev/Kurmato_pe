package com.example.kurmato_pr.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kurmato_pr.Adapter.ImageAdapter;
import com.example.kurmato_pr.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);

        ImageAdapter ImageAdapter = new ImageAdapter();
        //Setting adapter of recyclerview by object listadapter
        recyclerView.setAdapter(ImageAdapter);
        //Creating a layoutmanager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //Setting a layout manager
        recyclerView.setLayoutManager(layoutManager);   
        return view;
    }

}