package com.example.keenanchan.itunestopmusic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keenanchan.itunestopmusic.R;
import com.example.keenanchan.itunestopmusic.model.Example;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private Example example;
    private Context context;

    public CustomAdapter(Context context, Example example) {
        this.context = context;
        this.example = example;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        TextView txtTitle;
        private ImageView coverImage;

        public CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtTitle = mView.findViewById(R.id.title);
            coverImage = mView.findViewById(R.id.coverImage);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(example.getFeed().getResults().get(position).getArtworkUrl100())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);
        holder.txtTitle.setText(example.getFeed().getResults().get(position).getArtistName());
    }

    @Override
    public int getItemCount() {
        return example.getFeed().getResults().size();
    }
}
