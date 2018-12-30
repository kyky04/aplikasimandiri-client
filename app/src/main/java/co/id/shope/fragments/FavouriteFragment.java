package co.id.shope.fragments;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.adapters.ProductAdapterV;
import co.id.shope.application.MyApp;
import co.id.shope.data.Contans;
import co.id.shope.models.DataItemProduk;
import co.id.shope.models.ProdukResponse;
import co.id.shope.utils.Tools;
import co.id.shope.views.SliderIndicator;
import co.id.shope.views.SpacingItemDecoration;
import io.objectbox.Box;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FavouriteFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    Unbinder unbinder;
    ProgressDialog progressDialog;
    ProductAdapterV adapter;

    Box productBox;

    private OnFragmentInteractionListener mListener;

    private SliderIndicator mIndicator;

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        unbinder = ButterKnife.bind(this, view);

        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getActivity(), 8), true));
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);

        productBox = ((MyApp) getActivity().getApplication()).getBoxStore().boxFor(DataItemProduk.class);

        adapter = new ProductAdapterV(getActivity());
        adapter.swap(productBox.getAll());
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductAdapterV.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DetailProdukFragment detailProdukFragment = DetailProdukFragment.newInstance(adapter.getItem(position));
                openFragment(detailProdukFragment);
            }

            @Override
            public void onFavClick(int position) {

            }
        });

//        postAlamat();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }


    void loadData() {
        openDialog();
        AndroidNetworking.get(Contans.PRODUK)
                .addQueryParameter("includes","gambar,kategori")
                .build()
                .getAsObject(ProdukResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closerDialog();
                        if (response instanceof ProdukResponse) {
                            adapter.swap(((ProdukResponse) response).getData());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        closerDialog();
                    }
                });
    }


    void openDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading . . .");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    void closerDialog() {
        progressDialog.dismiss();
    }

    void openFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(android.R.id.content, fragment).addToBackStack(null).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    @Override
    public void onResume() {
        super.onResume();
        productBox = ((MyApp) getActivity().getApplication()).getBoxStore().boxFor(DataItemProduk.class);

        adapter = new ProductAdapterV(getActivity());
        adapter.swap(productBox.getAll());
        adapter.setOnItemClickListener(new ProductAdapterV.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onFavClick(int position) {

            }
        });
        recycler.setAdapter(adapter);
    }
}
