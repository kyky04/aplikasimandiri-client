package co.id.shope.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.shope.R;
import co.id.shope.models.DataItemProduk;
import co.id.shope.models.KategoriVertical;
import co.id.shope.views.EqualSpacingItemDecoration;

/**
 * Created by malik on 2/12/17.
 */

public class KategoriVerticalAdapter extends
        RecyclerView.Adapter<KategoriVerticalAdapter.VerticalRecyclerViewHolder> {

    private final RecyclerView.RecycledViewPool viewPool;
    private Context mContext;
    private List<KategoriVertical> mArrayList = new ArrayList<>();
    ProductAdapter.OnItemClickListener onItemClickListener;

    public KategoriVerticalAdapter(Context mContext, List<KategoriVertical> mArrayList) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    OnDetail detail;

    public OnDetail getDetail() {
        return detail;
    }

    public void setDetail(OnDetail detail) {
        this.detail = detail;
    }

    public interface OnDetail{
        void onDetailClick(DataItemProduk produk);
    }

    @Override
    public VerticalRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori_vertical, parent, false);
        return new VerticalRecyclerViewHolder(view);
    }

    public void setList(List<KategoriVertical> mArrayList){
        this.mArrayList.addAll(mArrayList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(VerticalRecyclerViewHolder holder, int position) {

        final KategoriVertical current = mArrayList.get(position);

        final String strTitle = current.getTitle();

        List<DataItemProduk> singleSectionItems = current.getArrayList();

        holder.tvTitle.setText(strTitle);

        ProductAdapter itemListDataAdapter = new ProductAdapter(mContext, singleSectionItems);

        itemListDataAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                detail.onDetailClick(current.getArrayList().get(pos));
            }

            @Override
            public void onFavClick(int position) {

            }
        });

//        holder.rvHorizontal.setLayoutManager(new GridLayoutManager(mContext, 2));
        holder.rvHorizontal.addItemDecoration(new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.HORIZONTAL));
        holder.rvHorizontal.setHasFixedSize(true);
        holder.rvHorizontal.setNestedScrollingEnabled(false);
        holder.rvHorizontal.setHasFixedSize(true);
        holder.rvHorizontal.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.rvHorizontal.setAdapter(itemListDataAdapter);

        holder.rvHorizontal.setNestedScrollingEnabled(false);

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, current.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class VerticalRecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.rvHorizontal)
        RecyclerView rvHorizontal;

        @BindView(R.id.btnMore)
        Button btnMore;

        public VerticalRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

//    void openFragment(DataItemProduk produk) {
//        DetailProdukFragment produkFragment = DetailProdukFragment.newInstance(produk);
//        FragmentManager manager = ((FragmentActivity)(Fragment)mContext)).getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(android.R.id.content, produkFragment).commit();
//    }

}