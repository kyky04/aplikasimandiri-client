package co.id.shope.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.shope.R;


/**
 * Created by Comp on 2/11/2018.
 */

public class ProgressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;
    private int rawFiles = 0;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public ProgressAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public ProgressAdapter(Context ctx, int type) {
        this.ctx = ctx;
        this.rawFiles = type;
    }


    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progress_bar)
        ProgressBar progressBar;


        public OriginalViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            view.progressBar.setVisibility(View.VISIBLE);


        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }


}
