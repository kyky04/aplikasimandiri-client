package co.id.shope.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.shope.R;
import co.id.shope.data.Contans;
import co.id.shope.models.DataItemKategori;
import co.id.shope.utils.Toko;


/**
 * Created by Comp on 2/11/2018.
 */

public class KategoriHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DataItemKategori> listItem;


    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public KategoriHomeAdapter(Context ctx) {
        this.ctx = ctx;
        listItem = new ArrayList<>();
    }

    public KategoriHomeAdapter(Context ctx, List<DataItemKategori> listItem) {
        this.listItem = listItem;
        this.ctx = ctx;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_kategori)
        ImageView imgKategori;
        @BindView(R.id.tv_kategori)
        TextView tvKategori;
        @BindView(R.id.lay_item_click)
        RelativeLayout layItemClick;



        public OriginalViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori_home, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            DataItemKategori item = listItem.get(position);
            Glide.with(ctx).load(Contans.WEB_URL_STORAGE + item.getGambar()).into(view.imgKategori);
            view.tvKategori.setText(item.getNama());
//            view.tvKategori.setBackgroundColor(Color.parseColor(Toko.get(ctx).getWarna_aplikasi()));

            view.layItemClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }


    public void add(DataItemKategori item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void addAll(List<DataItemKategori> listItem) {
        for (DataItemKategori item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public void swap(List<DataItemKategori> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public DataItemKategori getItem(int pos) {
        return listItem.get(pos);
    }

    public void setFilter(List<DataItemKategori> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public List<DataItemKategori> getListItem() {
        return listItem;
    }


}
