package co.id.shope.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import co.id.shope.models.DataItemProduk;
import co.id.shope.utils.Tools;

import static co.id.shope.data.Contans.WEB_URL_STORAGE;

public class DetailOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemEdit(int position);

        void onItemDelete(int position);
    }

    public OnItemClickListener mOnItemClickListener;

    private List<DataItemProduk> items;
    private Context ctx;

    public DetailOrderAdapter(Context context) {
        this.items = new ArrayList<>();
        ctx = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.price_strike)
        TextView priceStrike;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.more)
        ImageButton more;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            more.setVisibility(View.GONE);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_product, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder view = (ViewHolder) holder;
            final DataItemProduk o = items.get(position);
            if (o.getHargaDiskon() > 0) {
                view.price.setText(Tools.getFormattedPriceIndonesia((double) o.getHargaDiskon()));
                view.priceStrike.setText(Tools.getFormattedPriceIndonesia((double) o.getHargaDiskon()));
                view.priceStrike.setPaintFlags(view.priceStrike.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                view.priceStrike.setVisibility(View.VISIBLE);
            } else {
                view.price.setText(Tools.getFormattedPriceIndonesia(Double.valueOf(o.getHarga())));
                view.priceStrike.setVisibility(View.GONE);
            }
            view.name.setText(o.getNama());

            Glide.with(ctx).load(WEB_URL_STORAGE + o.getGambarUtama()).into(view.image);

//            view.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.onItemClick(position);
//                }
//            });
//            view.more.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onMoreButtonClick(v, position);
//                }
//            });


        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void onMoreButtonClick(final View view, final int pos) {
        PopupMenu popupMenu = new PopupMenu(ctx, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_id_edit:
                        mOnItemClickListener.onItemEdit(pos);
                        break;
                    case R.id.menu_id_delete:
                        mOnItemClickListener.onItemDelete(pos);
                        break;
                }
                return true;
            }
        });
        popupMenu.inflate(R.menu.menu_more);
        popupMenu.show();
    }

    public void add(DataItemProduk item) {
        items.add(item);
        notifyItemInserted(items.size() + 1);
    }

    public void addAll(List<DataItemProduk> items) {
        for (DataItemProduk item : items) {
            add(item);
        }
    }

    public void remove(DataItemProduk item) {
        int position = items.indexOf(item);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void swap(List<DataItemProduk> datas) {
        if (datas == null || datas.size() == 0)
            return;
        if (items != null && items.size() > 0)
            items.clear();
        items.addAll(datas);
        notifyDataSetChanged();

    }

    public OnItemClickListener getOnItemClick() {
        return mOnItemClickListener;
    }

    public void setOnItemClick(OnItemClickListener onItemClick) {
        this.mOnItemClickListener = onItemClick;
    }

    public DataItemProduk getItem(int pos) {
        return items.get(pos);
    }
}