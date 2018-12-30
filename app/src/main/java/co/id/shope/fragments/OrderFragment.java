package co.id.shope.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import co.id.shope.adapters.OrderAdapter;
import co.id.shope.data.Session;
import co.id.shope.models.order.DataItemOrder;
import co.id.shope.models.order.OrderResponse;
import co.id.shope.utils.Toko;

import static co.id.shope.data.Contans.ORDER;
import static co.id.shope.data.Contans.ORDER_RELATION;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Unbinder unbinder;

    OrderAdapter adapter;

    Session session;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        session = new Session(getActivity());

        toolbar.setTitle(Toko.get(getActivity()).getNama_toko());
        toolbar.setBackgroundColor(Color.parseColor(Toko.get(getActivity()).getWarna_aplikasi()));
        adapter = new OrderAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        getData();

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.setRefreshing(false);
                getData();
            }
        });

        adapter.setOnItemClickListener(new OrderAdapter.OnItemClick() {
            @Override
            public void onItemClick(int pos) {
                showData(adapter.getItem(pos));
            }

            @Override
            public void onKonirmasi(int pos) {
                konfirmasi(adapter.getItem(pos));
            }

            @Override
            public void onDeleteClick(int pos) {

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void getData() {
        refresh.setRefreshing(true);
        AndroidNetworking.get(ORDER)
                .addQueryParameter("id_toko", String.valueOf(session.getUser().getData().getIdToko()))
                .addQueryParameter("id_user", String.valueOf(session.getUser().getData().getId()))
                .addQueryParameter("includes", ORDER_RELATION)
                .build()
                .getAsObject(OrderResponse.class, new ParsedRequestListener() {
                    @Override
                    public void onResponse(Object response) {
                        refresh.setRefreshing(false);
                        if (response instanceof OrderResponse) {
                            if (((OrderResponse) response).getData().size() > 0) {
                                adapter.swap(((OrderResponse) response).getData());
                                recyclerView.setAdapter(adapter);
                            } else {
                                recyclerView.setAdapter(new EmptyAdapter(getActivity()));
                            }
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ERROR RESPONSE", anError.getErrorDetail());
                        refresh.setRefreshing(false);

                    }
                });
    }


    private void showData(DataItemOrder item) {
        FragmentManager fragmentManager = getFragmentManager();
        DetailOrderFragment newFragment = DetailOrderFragment.newInstance(item);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
        newFragment.setOnCallbackResult(new DetailOrderFragment.CallbackResult() {
            @Override
            public void sendResult() {

            }
        });
    }

    private void konfirmasi(DataItemOrder item) {
        FragmentManager fragmentManager = getFragmentManager();
        KonfirmasiFragment newFragment = KonfirmasiFragment.newInstance(item);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
        newFragment.setOnCallbackResult(new KonfirmasiFragment.CallbackResult() {
            @Override
            public void sendResult() {
                getData();
            }
        });
    }

}
