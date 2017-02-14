package me.wx.demo.apidemos.View;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by ein on 2017/2/14.
 */

public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public SimpleCardView mTextView;

        public ViewHolder(SimpleCardView v) {
            super(v);
            mTextView = v;
        }
    }

    private String[] mDataset;

    public CustomViewAdapter(String[] s) {
        mDataset = s;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SimpleCardView itemView = new SimpleCardView(parent.getContext());
        itemView.setOnCardViewClickListener(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);
    }


    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
