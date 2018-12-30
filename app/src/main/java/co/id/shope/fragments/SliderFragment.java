package co.id.shope.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.shope.R;
import co.id.shope.data.Contans;

public class SliderFragment extends Fragment {
    private static final String TAG = "SliderFragment";
    private static final String ARG_PARAM1 = "params";
    private static final String ARG_PARAM2 = "desc";
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_slider)
    TextView tvSlider;
    Unbinder unbinder;

    private String imageUrls;
    int imageDrawable;

    public SliderFragment() {
    }

    public static SliderFragment newInstance(String params, String desc) {
        SliderFragment fragment = new SliderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, params);
        args.putString(ARG_PARAM2, desc);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        ImageView img = (ImageView) view.findViewById(R.id.img);

        String url = getArguments().getString(ARG_PARAM1);
        String desc = getArguments().getString(ARG_PARAM2);
        Log.d(TAG, "onCreateView: "+desc);
        Glide.with(getActivity()).load(Contans.WEB_URL_STORAGE + url).into(img);
        tvSlider.setText(desc);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
