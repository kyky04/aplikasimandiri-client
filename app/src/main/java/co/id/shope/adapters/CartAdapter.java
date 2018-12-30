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
import co.id.shope.models.DataItemCart;
import co.id.shope.utils.CommonUtil;


/**
 * Created by Comp on 2/11/2018.
 */

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DataItemCart> listItem;

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

    public CartAdapter(Context ctx, OnItemClickListener mItemClickListener) {
        this.ctx = ctx;
        listItem = new ArrayList<>();
        this.mOnItemClickListener = mItemClickListener;
    }

    public CartAdapter(Context ctx, List<DataItemCart> listItem) {
        this.listItem = listItem;
        this.ctx = ctx;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_harga)
        TextView tvHarga;
        @BindView(R.id.tv_kategori)
        TextView tvKategori;
        @BindView(R.id.btn_minus)
        ImageButton btnRemove;
        @BindView(R.id.tv_jumlah)
        TextView tvJumlah;
        @BindView(R.id.btn_add)
        ImageButton btnAdd;
        @BindView(R.id.delete)
        ImageView delete;


        int quantity;

        public OriginalViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            final OriginalViewHolder viewHolder = (OriginalViewHolder) holder;
            final DataItemCart item = listItem.get(position);
            Glide.with(ctx).load(Contans.WEB_URL_STORAGE + item.getGambarUtama()).into(viewHolder.image);
            viewHolder.tvHarga.setText(CommonUtil.getFormattedPriceIndonesia(Double.valueOf(item.getHarga())));
            viewHolder.tvJudul.setText(item.getNama());

            ((OriginalViewHolder) holder).quantity = item.getAmount();
            item.setTotal((int) (viewHolder.quantity * Double.valueOf(item.getHarga())));

            viewHolder.tvJumlah.setText(((OriginalViewHolder) holder).quantity + "");
            viewHolder.tvKategori.setText("STOK : " + item.getStok());
//            viewHolder.tvKategori.setText(item.getKategori().getNama());
//            view.lytParent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.onItemClick(position);
//                }
//            });

            viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (viewHolder.quantity < Integer.parseInt(item.getStok())) {

                        viewHolder.quantity = viewHolder.quantity + 1;
                        item.setAmount(viewHolder.quantity);
                        item.setTotal((int) (viewHolder.quantity * Double.valueOf(item.getHarga())));
                        Log.d("Onclik", "onClick: " + item.getTotal());
                        mOnItemClickListener.onAdd(item.getTotal());

                        ((OriginalViewHolder) holder).tvJumlah.setText("" + viewHolder.quantity);

                    }

                    notifyDataSetChanged();

                }
            });


            viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (viewHolder.quantity > 0 && viewHolder.quantity <= Integer.parseInt(item.getStok())) {

                        viewHolder.quantity = viewHolder.quantity - 1;
                        item.setAmount(viewHolder.quantity);
                        item.setTotal((int) (viewHolder.quantity * Double.valueOf(item.getHarga())));
                        Log.d("Onclik", "onClick: " + item.getTotal());
                        mOnItemClickListener.onMinus(item.getTotal());

                        ((OriginalViewHolder) holder).tvJumlah.setText("" + viewHolder.quantity);

                    }

                    notifyDataSetChanged();

                }
            });

            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onDelete(position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void add(DataItemCart item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void remove(DataItemCart item) {
        listItem.remove(item);
        notifyDataSetChanged();
    }

    public void addAll(List<DataItemCart> listItem) {
        for (DataItemCart item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public void swap(List<DataItemCart> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public DataItemCart getItem(int pos) {
        return listItem.get(pos);
    }

    public void setFilter(List<DataItemCart> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public List<DataItemCart> getListItem() {
        return listItem;
    }

    public double getTotal() {

        double total = 0.0;
        for (int i = 0; i < listItem.size(); i++) {
            total = total + listItem.get(i).getTotal();
        }
        return total;
    }

}
