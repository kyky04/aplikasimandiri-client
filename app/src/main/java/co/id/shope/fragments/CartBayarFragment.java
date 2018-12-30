package co.id.shope.fragments;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.activities.CheckOutActivity;
import co.id.shope.adapters.CartAdapter;
import co.id.shope.application.MyApp;
import co.id.shope.models.DataItemCart;
import co.id.shope.views.SliderIndicator;
import io.objectbox.Box;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CartBayarFragment extends Fragment {
    private static final String TAG = "CartBayarFragment";
    @BindView(R.id.recycler)
    RecyclerView recycler;

    Unbinder unbinder;
    ProgressDialog progressDialog;
    CartAdapter adapter;
    Box catBox;



    private OnFragmentInteractionListener mListener;

    private SliderIndicator mIndicator;

    public CartBayarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart, container, false);
        unbinder = ButterKnife.bind(this, view);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recycler.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getActivity(), 8), true));
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);

        catBox = ((MyApp) getActivity().getApplication()).getBoxStore().boxFor(DataItemCart.class);
        ((CheckOutActivity) getActivity()).setTotal(adapter.getTotal());
        adapter = new CartAdapter(getActivity(), new CartAdapter.OnItemClickListener() {
            @Override
            public void onAdd(int total) {
                Log.d(TAG, "onAdd: " + total);
                double plus = ((CheckOutActivity) getActivity()).getTotal() + total;
                ((CheckOutActivity) getActivity()).setTotal(plus);

            }

            @Override
            public void onMinus(int total) {
                double plus = ((CheckOutActivity) getActivity()).getTotal() - total;
                ((CheckOutActivity) getActivity()).setTotal(plus);

            }

            @Override
            public void onDelete(int pos) {

            }
        });
        adapter.addAll(catBox.getAll());

        recycler.setAdapter(adapter);
        ((CheckOutActivity) getActivity()).setTotal(adapter.getTotal());


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
        catBox = ((MyApp) getActivity().getApplication()).getBoxStore().boxFor(DataItemCart.class);

//        adapter = new CartAdapter(getActivity());
        adapter.swap(catBox.getAll());
        recycler.setAdapter(adapter);
    }


    public List<DataItemCart> getCart(){
        return adapter.getListItem();
    }



}
