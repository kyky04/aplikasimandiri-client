package co.id.shope.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.shope.R;
import co.id.shope.models.DataItemBank;


/**
 * Created by Comp on 2/11/2018.
 */

public class BankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DataItemBank> listItem;



    private Context ctx;
    OnItemClickListener mOnItemClickListener;

    @OnClick(R.id.lyt_parent)
    public void onViewClicked() {
    }

    public interface OnItemClickListener {
        void onClick(DataItemBank alamat);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public BankAdapter(Context ctx) {
        this.ctx = ctx;
        listItem = new ArrayList<>();
    }

    public BankAdapter(Context ctx, List<DataItemBank> listItem) {
        this.listItem = listItem;
        this.ctx = ctx;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.no_rek)
        TextView noRek;
        @BindView(R.id.atas_nama)
        TextView atasNama;
        @BindView(R.id.nama_bank)
        TextView namaBank;
        @BindView(R.id.lyt_parent)
        LinearLayout lytParent;
        @BindView(R.id.cb_selected)
        CheckBox cbSelected;


        public OriginalViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            final OriginalViewHolder viewHolder = (OriginalViewHolder) holder;
            final DataItemBank item = listItem.get(position);

            viewHolder.noRek.setText(item.getNoRekening().replace("", " ").trim());
            viewHolder.atasNama.setText(item.getAtasNama());
            viewHolder.namaBank.setText(item.getNamaBank());

            if (listItem.get(position).isSelected()) {
                viewHolder.cbSelected.setChecked(true);
            } else {
                viewHolder.cbSelected.setChecked(false);
            }

            viewHolder.cbSelected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int k = 0; k < listItem.size(); k++) {
                        if (k == position) {
                            listItem.get(k).setSelected(true);
                        } else {
                            listItem.get(k).setSelected(false);
                        }
                    }
                    notifyDataSetChanged();

                }
            });
            viewHolder.cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked == true) {
                        mOnItemClickListener.onClick(listItem.get(position));
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void add(DataItemBank item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }

    public void addAll(List<DataItemBank> listItem) {
        for (DataItemBank item : listItem) {
            add(item);
        }
    }

    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }

    public void swap(List<DataItemBank> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();

    }

    public DataItemBank getItem(int pos) {
        return listItem.get(pos);
    }

    public void setFilter(List<DataItemBank> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }

    public List<DataItemBank> getListItem() {
        return listItem;
    }


}
