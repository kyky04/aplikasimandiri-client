package co.id.shope.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.shope.R;
import co.id.shope.data.Contans;
import co.id.shope.models.ProdukOrder;
import co.id.shope.models.ProdukOrder;
import co.id.shope.utils.CommonUtil;


/**
 * Created by Comp on 2/11/2018.
 */

public class CartReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ProdukOrder> listItem;

    private Context ctx;
    OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onAdd(int total);

        void onMinus(int totak);

        void onDelete(int pos);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public CartReviewAdapter(Context ctx) {
        this.ctx = ctx;
        listItem = new ArrayList<>();
    }

    public CartReviewAdapter(Context ctx, List<ProdukOrder> listItem) {
        this.listItem = listItem;
        this.ctx = ctx;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_jumlah)
        TextView tvJumlah;
        @BindView(R.id.tv_harga)
        TextView tvHarga;



        int quantity;

        public OriginalViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_review, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            final OriginalViewHolder viewHolder = (OriginalViewHolder) holder;
            final ProdukOrder item = listItem.get(position);
            Glide.with(ctx).load(Contans.WEB_URL_STORAGE + item.getGambar()).into(viewHolder.image);
            viewHolder.tvHarga.setText(CommonUtil.getFormattedPriceIndonesia(Double.valueOf(item.getHarga())));
            viewHolder.tvJudul.setText(item.getNama());
            viewHolder.tvJumlah.setText(item.getQuantity()+"");
//            viewHolder.tvKategori.setText(item.getKategori().getNama());
//            view.lytParent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.onItemClick(position);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void add(ProdukOrder item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void remove(ProdukOrder item) {
        listItem.remove(item);
        notifyDataSetChanged();
    }

    public void addAll(List<ProdukOrder> listItem) {
        for (ProdukOrder item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public void swap(List<ProdukOrder> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public ProdukOrder getItem(int pos) {
        return listItem.get(pos);
    }

    public void setFilter(List<ProdukOrder> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public List<ProdukOrder> getListItem() {
        return listItem;
    }


}
