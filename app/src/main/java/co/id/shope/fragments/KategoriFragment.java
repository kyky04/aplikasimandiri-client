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
import co.id.shope.adapters.KategoriAdapter;
import co.id.shope.data.Contans;
import co.id.shope.models.KategoriResponse;
import co.id.shope.utils.Tools;
import co.id.shope.views.SliderIndicator;
import co.id.shope.views.SpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class KategoriFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    Unbinder unbinder;
    ProgressDialog progressDialog;
    KategoriAdapter adapter;

    private OnFragmentInteractionListener mListener;

    private SliderIndicator mIndicator;

    public KategoriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kategori, container, false);
        unbinder = ButterKnife.bind(this, view);

        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycler.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getActivity(), 8), true));
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);

        adapter = new KategoriAdapter(getActivity());
        recycler.setAdapter(adapter);
        loadData();

        adapter.setOnItemClickListener(new KategoriAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openFragment(FilterKategoriFragment.newInstance((int) adapter.getItem(position).getId()));
            }
        });

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
        AndroidNetworking.get(Contans.KATEGORI)
                .addQueryParameter("id_toko", String.valueOf(8))
                .build()
                .getAsObject(KategoriResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closerDialog();
                        if (response instanceof KategoriResponse) {
                            adapter.swap(((KategoriResponse) response).getData());
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
