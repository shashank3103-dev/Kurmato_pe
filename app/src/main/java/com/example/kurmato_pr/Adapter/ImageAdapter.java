package com.example.kurmato_pr.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.kurmato_pr.R;
import com.example.kurmato_pr.utils.recyclerview_list;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ListViewHolder> {

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_profile, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder viewHolder, int i) {
        viewHolder.bindView(i);
    }

    @Override
    public int getItemCount() {
        return recyclerview_list.text.length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        private ImageView mImageView;

        public ListViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.textView);
            mImageView = view.findViewById(R.id.imageView);

            if (mTextView == null) {
                Log.e("ImageAdapter", "mTextView is null");
            }

            if (mImageView == null) {
                Log.e("ImageAdapter", "mImageView is null");
            }

            view.setOnClickListener(this);
        }

        public void bindView(int position) {
            if (position >= 0 && position < getItemCount()) {
                if (mTextView != null) {
                    mTextView.setText(recyclerview_list.text[position]);
                    mImageView.setImageResource(recyclerview_list.images[position]);
                } else {
                    Log.e("ImageAdapter", "mTextView is null at position: " + position);
                }

                if (mImageView != null) {
                    mImageView.setImageResource(recyclerview_list.images[position]);
                } else {
                    Log.e("ImageAdapter", "mImageView is null at position: " + position);
                }
            } else {
                Log.e("ImageAdapter", "Invalid position: " + position);
            }
        }

        @Override
        public void onClick(View view) {
            Log.d("Some Tag", "Clicked: " + view.toString());
        }
    }
}
