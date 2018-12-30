package co.id.shope.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import co.id.shope.fragments.SliderFragment;

public class SliderAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "SliderAdapter";

    List<Fragment> mFrags = new ArrayList<>();

    public SliderAdapter(FragmentManager fm, List<Fragment> frags) {
        super(fm);
        mFrags = frags;
    }

    @Override
    public Fragment getItem(int position) {
        int index = position % mFrags.size();
        return SliderFragment.newInstance(mFrags.get(index).getArguments().getString("params"),mFrags.get(index).getArguments().getString("desc"));
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

}
