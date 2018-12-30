package co.id.shope.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SliderAdapter";

    List<Fragment> mFrags = new ArrayList<>();
    HashMap<Integer,String> mFragmentTags;
    FragmentManager fragmentManager;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
        mFragmentTags = new HashMap<Integer,String>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFrags.get(position);
    }

    @Override
    public int getCount() {
        return mFrags.size();
    }

    public void addFragment(Fragment fragment){
        mFrags.add(fragment);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            // record the fragment tag here.
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        Log.d(TAG, "getFragment: "+tag);
        if (tag == null)
            return null;
        return fragmentManager.findFragmentByTag(tag);
    }

}
