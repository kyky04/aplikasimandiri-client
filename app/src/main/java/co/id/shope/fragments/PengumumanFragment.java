package co.id.shope.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import co.id.shope.adapters.PengumumanAdapter;
import co.id.shope.data.Contans;
import co.id.shope.data.Session;
import co.id.shope.models.PengumumanResponse;
import co.id.shope.utils.Toko;
import co.id.shope.views.SliderIndicator;
import io.objectbox.Box;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class PengumumanFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    Unbinder unbinder;
    ProgressDialog progressDialog;
    PengumumanAdapter adapter;
    Session session;

    Box productBox;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private OnFragmentInteractionListener mListener;

    private SliderIndicator mIndicator;

    public PengumumanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengumuman, container, false);
        unbinder = ButterKnife.bind(this, view);
        session = new Session(getActivity());
        toolbar.setTitle(Toko.get(getActivity()).getNama_toko());
        toolbar.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new PengumumanAdapter(getActivity());
        loadData();
        recycler.setAdapter(adapter);

        adapter.setOnItemClick(new PengumumanAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                openFragment(DetailPengumumanFragment.newInstance(adapter.getItem(position)));
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
        AndroidNetworking.get(Contans.NEWS)
                .addQueryParameter("id_toko", String.valueOf(session.getUser().getData().getIdToko()))
                .build()
                .getAsObject(PengumumanResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        closerDialog();
                        if (response instanceof PengumumanResponse) {
                            adapter.swap(((PengumumanResponse) response).getData());
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
