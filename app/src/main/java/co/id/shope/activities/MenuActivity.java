package co.id.shope.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.onesignal.OneSignal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.shope.R;
import co.id.shope.data.Session;
import co.id.shope.fragments.DetailOrderFragment;
import co.id.shope.fragments.DetailProfileFragment;
import co.id.shope.fragments.DetailTokoFragment;
import co.id.shope.fragments.HomeFragment;
import co.id.shope.fragments.OrderFragment;
import co.id.shope.fragments.PengumumanFragment;
import co.id.shope.fragments.SearchFragment;
import co.id.shope.models.LoginResponse;
import co.id.shope.models.toko.DataItemToko;
import co.id.shope.models.toko.ShowToko;
import co.id.shope.utils.CommonUtil;
import co.id.shope.utils.DialogUtil;
import co.id.shope.utils.Toko;

import static co.id.shope.data.Contans.TOKEN;
import static co.id.shope.data.Contans.TOKO;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";
    private ActionBar actionBar;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.frame_content_new_product)
    FrameLayout frameContentNewProduct;
    @BindView(R.id.nested_content)
    NestedScrollView nestedContent;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.parent_view)
    CoordinatorLayout parentView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.action_search)
    ImageButton actionSearch;
    @BindView(R.id.search_bar)
    CardView searchBar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);
        session = new Session(this);

        initToolbar();
        initDrawerMenu();
        initComponent();
        initFragment();

        token();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(Toko.get(this).getNama_toko());
        appbarLayout.setBackgroundColor(Color.parseColor(Toko.get(this).getWarna_aplikasi()));
        fab.setBackgroundColor(Color.parseColor(Toko.get(this).getWarna_aplikasi()));
    }

    private void initComponent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFragment();
            }
        });
    }

    private void initDrawerMenu() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                onItemSelected(item.getItemId());
                //drawer.closeDrawers();
                return true;
            }
        });
        navView.setItemIconTintList(getResources().getColorStateList(R.color.nav_state_list));

        View header = navView.getHeaderView(0);
        RelativeLayout layout = header.findViewById(R.id.header);
        layout.setBackgroundColor(Color.parseColor(Toko.get(this).getWarna_aplikasi()));

    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // init fragment slider new product
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.frame_content_new_product, homeFragment);

        fragmentTransaction.commit();
    }

    private void refreshFragment() {
        swipeRefreshLayout.setRefreshing(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initFragment();
            }
        }, 500);
    }

    private void swipeProgress(final boolean show) {
        if (!show) {
            swipeRefreshLayout.setRefreshing(show);
            return;
        }
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(show);
            }
        });
    }

    public boolean onItemSelected(int id) {
        Intent i;
        switch (id) {
            //sub menu
            case R.id.nav_cart:
                i = new Intent(this, KeranjangActivity.class);
                startActivity(i);
                break;
            case R.id.nav_wish:
                break;
            case R.id.nav_history:
                OrderFragment orderFragment = new OrderFragment();
                openFragment(orderFragment);
                break;
            case R.id.nav_chat:
                i = new Intent(this, ChatActivity.class);
                startActivity(i);
                break;
            case R.id.nav_news:
                PengumumanFragment newsFragment = new PengumumanFragment();
                openFragment(newsFragment);
                break;
            case R.id.nav_notif:
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_instruction:

                break;
            case R.id.nav_logout:
                session.logoutUser();
                finish();
                break;
            case R.id.nav_about:
                openFragment(new DetailTokoFragment());
                break;
            case R.id.nav_account:
                openFragment(new DetailProfileFragment());
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        FragmentManager fm = getSupportFragmentManager();
//        Log.d(TAG, "onBackPressed: "+fm.getBackStackEntryCount());
//        if(fm.getBackStackEntryCount() != 0){
//            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            } else {
//                doExitApp();
//            }
//        }
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


    @OnClick({R.id.fab, R.id.action_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent i = new Intent(this, KeranjangActivity.class);
                startActivity(i);
                break;
            case R.id.action_search:
                openFragment(new SearchFragment());
                break;
        }
    }

    private void tokenize(String userId) {
//        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN", "tokenize: " + userId);
        ANRequest.PostRequestBuilder post = new ANRequest.PostRequestBuilder(TOKEN);
        post.addBodyParameter("email", session.getUser().getData().getEmail());
        post.addBodyParameter("android_token", userId);
        post.build().getAsObject(LoginResponse.class, new ParsedRequestListener() {
            @Override
            public void onResponse(Object response) {
                if (response instanceof LoginResponse) {
                    if (((LoginResponse) response).isStatus()) {
                        Log.d("TOKEN", "onResponse: " + "Token di kirim");
                    } else {
                        Log.d("TOKEN", "onResponse: " + "Token gagal di kirim");
                    }
                }
            }

            @Override
            public void onError(ANError anError) {
                CommonUtil.showToast(MenuActivity.this, "Kesalahan Server");
            }
        });
    }

    private void token(){
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                Log.d("debug", "User:" + userId);
                if (registrationId != null) {
                    Log.d("debug", "registrationId:" + registrationId);
                }
                tokenize(userId);

            }
        });
    }

    void openFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(android.R.id.content, fragment).addToBackStack(null).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }


}
