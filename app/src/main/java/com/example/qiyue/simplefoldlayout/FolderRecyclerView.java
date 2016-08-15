package com.example.qiyue.simplefoldlayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;


/**
 * Created by qiyue on 2016/8/14.
 */
public class FolderRecyclerView extends RecyclerView {
    private RecyclerView.OnScrollListener onScrollListener;
    private int item_normal_height;
    private int item_max_height;
    public FolderRecyclerView(Context context) {
        super(context);
        init();
    }

    public FolderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FolderRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        item_max_height = (int) getResources().getDimension(R.dimen.item_max_height);
        item_normal_height = (int) getResources().getDimension(R.dimen.item_normal_height);

        onScrollListener = new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                RecyclerView.ViewHolder firstViewHolder = recyclerView
                        .findViewHolderForLayoutPosition(linearLayoutManager.findFirstVisibleItemPosition());
                if (firstViewHolder != null &&
                        firstViewHolder instanceof MyAdapter.ViewHolder &&
                        (linearLayoutManager.findFirstVisibleItemPosition() == 0)) {
                    MyAdapter.ViewHolder viewHolder = (MyAdapter.ViewHolder) firstViewHolder;
                    if (viewHolder.itemView.getLayoutParams().height - dy <= item_max_height
                            && viewHolder.itemView.getLayoutParams().height - dy >= item_normal_height) {
                        viewHolder.itemView.getLayoutParams().height = viewHolder.itemView.getLayoutParams().height - dy;
                        viewHolder.itemView.setLayoutParams(viewHolder.itemView.getLayoutParams());
                    }
                }
            }
        };

        this.addOnScrollListener(onScrollListener);
    }
}
