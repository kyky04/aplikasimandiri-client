package co.id.shope.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import co.id.shope.R;
import co.id.shope.models.DataItemPengumuman;

public class PengumumanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    public interface OnItemClick {
        void onItemClick(int position);

    }

    public OnItemClick onItemClick;

    private List<DataItemPengumuman> items;
    private Context ctx;

    public PengumumanAdapter(Context context) {
        this.items = new ArrayList<>();
        ctx = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;



        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pengumuman, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder view = (ViewHolder) holder;
            final DataItemPengumuman o = items.get(position);
            view.title.setText(o.getJudul());

            view.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public void add(DataItemPengumuman dataItemRapatK3) {
        items.add(dataItemRapatK3);
        notifyItemInserted(items.size() + 1);
    }

    public void addAll(List<DataItemPengumuman> items) {
        for (DataItemPengumuman rapatK3 : items) {
            add(rapatK3);
        }
    }

    public void remove(DataItemPengumuman Memo) {
        int position = items.indexOf(Memo);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void swap(List<DataItemPengumuman> datas) {
        if (datas == null || datas.size() == 0)
            return;
        if (items != null && items.size() > 0)
            items.clear();
        items.addAll(datas);
        notifyDataSetChanged();

    }

    public OnItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public DataItemPengumuman getItem(int pos) {
        return items.get(pos);
    }
}