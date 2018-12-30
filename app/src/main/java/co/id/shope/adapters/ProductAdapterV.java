package co.id.shope.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.shope.R;
import co.id.shope.data.Contans;
import co.id.shope.models.DataItemProduk;
import co.id.shope.utils.CommonUtil;


/**
 * Created by Comp on 2/11/2018.
 */

public class ProductAdapterV extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DataItemProduk> listItem;

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(int position);

        void onFavClick(int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public ProductAdapterV(Context ctx) {
        this.ctx = ctx;
        listItem = new ArrayList<>();
    }

    public ProductAdapterV(Context ctx, List<DataItemProduk> listItem) {
        this.listItem = listItem;
        this.ctx = ctx;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_product)
        ImageView imgProduct;
        @BindView(R.id.tv_product)
        TextView tvProduct;
        @BindView(R.id.tv_harga)
        TextView tvHarga;
        @BindView(R.id.lyt_parent)
        LinearLayout lytParent;

        public OriginalViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_v, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            final DataItemProduk item = listItem.get(position);
            Glide.with(ctx).load(Contans.WEB_URL_STORAGE + item.getGambarUtama()).into(view.imgProduct);
            view.tvHarga.setText(CommonUtil.getFormattedPriceIndonesia(Double.valueOf(item.getHarga())));
            view.tvProduct.setText(item.getNama());
            view.lytParent.setOnClickListener(new View.OnClickListener() {
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

    public void add(DataItemProduk item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void addAll(List<DataItemProduk> listItem) {
        for (DataItemProduk item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public void swap(List<DataItemProduk> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public DataItemProduk getItem(int pos) {
        return listItem.get(pos);
    }

    public void setFilter(List<DataItemProduk> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public List<DataItemProduk> getListItem() {
        return listItem;
    }


}
