package com.apps.wolvy.raspsecurenew;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ImageView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    public Uri videoUri;
    private Context mContext;
    private List<Upload> mUploads;
    MediaController mediaController;

    public VideoAdapter(Context context, List<Upload> uploads){
        mContext = context;
        mUploads = uploads;

    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_video_adapter, parent, false);
        return new VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        videoUri = Uri.parse(uploadCurrent.getImageUrl());
        mediaController = new MediaController(mContext);
        holder.videoView.setMediaController(mediaController);
        mediaController.setAnchorView(holder.videoView);
        holder.videoView.setVideoURI(videoUri);
        holder.videoView.requestFocus();
        holder.videoView.start();
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        public VideoView videoView;

        public VideoViewHolder(View itemView){
            super(itemView);
            videoView = itemView.findViewById(R.id.video);

        }
    }

}
