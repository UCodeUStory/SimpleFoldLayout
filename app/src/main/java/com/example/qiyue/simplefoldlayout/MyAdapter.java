package com.example.qiyue.simplefoldlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by dongjunkun on 2015/12/1.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private boolean isFirst = true;
    private List<Integer> walls;

    private int item_max_height;

    public MyAdapter(Context context, List<Integer> walls) {
        this.walls = walls;
        item_max_height = (int) context.getResources().getDimension(R.dimen.item_max_height);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isFirst && position == 0) {
            isFirst = false;
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, item_max_height));
        } else {
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, item_max_height));
        }
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(holder.imageView.getContext()).load(walls.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return walls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
