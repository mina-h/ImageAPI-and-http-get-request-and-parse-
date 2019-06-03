package com.example.imageapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter  extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private ArrayList<ImageItem> mImageList;

    public ImageAdapter(Context context, ArrayList<ImageItem> imageList) {
        this.mContext = context;
        this.mImageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
        return new ImageViewHolder(v);
            }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        ImageItem currentItem = mImageList.get(i);

        String imageUrl = currentItem.getmImageUrl();
        String creatorName = currentItem.getmCreator();
        int likeCount = currentItem.getmLikes();

        imageViewHolder.mTextViewCreator.setText(creatorName);
        imageViewHolder.mTextViewLikes.setText("Likes: " +  likeCount);
        Picasso.get().load(imageUrl).fit().centerInside().into(imageViewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
        //as many items in adapter as there are itemsin arraylist
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView ;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;


         public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes );
        }
    }
}
