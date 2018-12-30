package co.id.shope;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.shope.activities.CheckOutActivity;
import co.id.shope.adapters.ViewPagerAdapter;
import co.id.shope.fragments.FavouriteFragment;
import co.id.shope.fragments.FilterKategoriFragment;
import co.id.shope.fragments.HomeFragment;
import co.id.shope.fragments.KategoriFragment;
import co.id.shope.fragments.PengumumanFragment;
import co.id.shope.views.NoSwipeableViewPager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.tab_menu)
    TabLayout tabMenu;
    @BindView(R.id.nested)
    LinearLayout nested;
    @BindView(R.id.view_pager)
    NoSwipeableViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_search)
    EditText etSearch;

    Fragment fragment;
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new HomeFragment());
        mPagerAdapter.addFragment(new KategoriFragment());
        mPagerAdapter.addFragment(new FavouriteFragment());
        mPagerAdapter.addFragment(new PengumumanFragment());
//        mPagerAdapter.addFragment(new HomeFragment());

        viewPager.setAdapter(mPagerAdapter);

        tabMenu.addTab(tabMenu.newTab().setIcon(R.drawable.ic_home_black_24dp));
        tabMenu.addTab(tabMenu.newTab().setIcon(R.drawable.ic_grid));
        tabMenu.addTab(tabMenu.newTab().setIcon(R.drawable.ic_favorite_black));
        tabMenu.addTab(tabMenu.newTab().setIcon(R.drawable.ic_event_note));
//        tabMenu.addTab(tabMenu.newTab().setIcon(R.drawable.ic_person));

        tabMenu.getTabAt(0).select();
//        tabMenu.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = ((ViewPagerAdapter) viewPager.getAdapter()).getFragment(position);
                if (position == 2 && fragment != null) {
                    fragment.onResume();
                } else if (position == 3 && fragment != null) {
                    fragment.onResume();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    void openFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(android.R.id.content, fragment).addToBackStack(null).commit();
    }

    @OnClick(R.id.et_search)
    public void onViewClicked() {
        fragment = new FilterKategoriFragment();
        openFragment(fragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_id_cart:
                startActivity(new Intent(MainActivity.this, CheckOutActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
