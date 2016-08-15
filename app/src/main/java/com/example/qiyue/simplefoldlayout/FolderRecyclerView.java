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
    private float item_normal_alpha;
    private float item_max_alpha;
    private float alpha_d;
    private float item_normal_font_size;
    private float item_max_font_size;
    private float font_size_d;
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
        item_normal_font_size = getResources().getDimension(R.dimen.item_normal_font_size);
        item_max_font_size = getResources().getDimension(R.dimen.item_max_font_size);
        item_normal_alpha = getResources().getFraction(R.fraction.item_normal_mask_alpha, 1, 1);
        item_max_alpha = getResources().getFraction(R.fraction.item_max_mask_alpha, 1, 1);

        font_size_d = item_max_font_size - item_normal_font_size;
        alpha_d = item_max_alpha - item_normal_alpha;
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
                        viewHolder.mark.setAlpha(viewHolder.mark.getAlpha() - dy * alpha_d / item_normal_height);
                        viewHolder.text.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                viewHolder.text.getTextSize() - dy * font_size_d / item_normal_height);
                        viewHolder.itemView.setLayoutParams(viewHolder.itemView.getLayoutParams());
                    }
                }
            }
        };

        this.addOnScrollListener(onScrollListener);
    }
}
