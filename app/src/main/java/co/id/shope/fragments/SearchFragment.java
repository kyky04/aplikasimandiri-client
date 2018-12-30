package co.id.shope.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
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
import co.id.shope.adapters.EmptyAdapter;
import co.id.shope.adapters.ProductAdapterV;
import co.id.shope.data.Contans;
import co.id.shope.data.Session;
import co.id.shope.models.ProdukResponse;
import co.id.shope.utils.Toko;
import co.id.shope.utils.Tools;
import co.id.shope.views.SliderIndicator;
import co.id.shope.views.SpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SearchFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    Unbinder unbinder;
    ProgressDialog progressDialog;
    ProductAdapterV adapter;
    EmptyAdapter emptyAdapter;
    @BindView(R.id.searchview)
    SearchView searchview;
    Session session;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private OnFragmentInteractionListener mListener;

    private SliderIndicator mIndicator;


    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance(int id_kategori) {

        Bundle args = new Bundle();
        args.putInt("id_kategori", id_kategori);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());
        toolbar.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));
        toolbar.setTitle(Toko.get(getActivity()).getNama_toko());

        adapter = new ProductAdapterV(getActivity());
        emptyAdapter = new EmptyAdapter(getActivity());
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

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchview.onActionViewExpanded();

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


    void loadData(String query) {
        openDialog();
        AndroidNetworking.get(Contans.PRODUK)
                .addQueryParameter("includes", "gambar,kategori")
                .addQueryParameter("id_toko", String.valueOf(session.getUser().getData().getIdToko()))
                .addQueryParameter("nama", "*" + query + "*")
                .build()
                .getAsObject(ProdukResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closerDialog();
                        if (response instanceof ProdukResponse) {
                            if (((ProdukResponse) response).isStatus()) {
                                if (((ProdukResponse) response).getData().size() > 0) {
                                    adapter.swap(((ProdukResponse) response).getData());
                                    recycler.setAdapter(adapter);
                                    recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                                    recycler.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getActivity(), 8), true));
                                    recycler.setHasFixedSize(true);
                                    recycler.setNestedScrollingEnabled(false);

                                } else {
                                    recycler.setAdapter(emptyAdapter);
                                    recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                                }
                            }
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

}
