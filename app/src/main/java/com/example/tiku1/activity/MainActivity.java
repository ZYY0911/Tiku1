package com.example.tiku1.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiku1.BuildConfig;
import com.example.tiku1.R;
import com.example.tiku1.fragment.HomeFragment;
import com.example.tiku1.fragment.Z_CLWZFragemnt;
import com.example.tiku1.fragment.Z_CXGLFragment;
import com.example.tiku1.fragment.Z_DZBCActivity;
import com.example.tiku1.fragment.Z_HJZBFragment;
import com.example.tiku1.fragment.Z_SJFCFragment;
import com.example.tiku1.fragment.Z_SSJTFragment;
import com.example.tiku1.fragment.Z_TQXXFragment;
import com.example.tiku1.fragment.Z_WDZHFragment;
import com.example.tiku1.fragment.Z_YZSZActivity;
import com.example.tiku1.fragment.Z_ZDGLFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

public class MainActivity extends SupportActivity {

    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer)
    DrawerLayout drawer;


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void FramgnetStat(String msg) {
        title.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        SupportFragment fragment = findFragment(HomeFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fragment_layout, new HomeFragment());
        }
        initClick();

    }

    private void initClick() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {
                drawer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int id = menuItem.getItemId();
                        ISupportFragment myClass = getTopFragment();
                        SupportFragment supportFragment = (SupportFragment) myClass;
                        switch (id) {
                            case R.id.wdzh_item:
                                Z_WDZHFragment fragment = findFragment(Z_WDZHFragment.class);
                                if (fragment == null) {
                                    supportFragment.startWithPopTo(new Z_WDZHFragment(), HomeFragment.class, false);
                                } else {
                                    supportFragment.start(fragment, SupportFragment.SINGLETASK);
                                }
                                break;
                            case R.id.zdgl_item:
                                Z_ZDGLFragment z_wdzhFragment = findFragment(Z_ZDGLFragment.class);
                                if (z_wdzhFragment == null) {
                                    supportFragment.startWithPopTo(new Z_ZDGLFragment(), HomeFragment.class, false);
                                } else {
                                    supportFragment.start(z_wdzhFragment, SupportFragment.SINGLETASK);
                                }
                                break;
                            case R.id.clwz_item:
                                Z_CLWZFragemnt clwzFragemnt = findFragment(Z_CLWZFragemnt.class);
                                if (clwzFragemnt == null) {
                                    supportFragment.startWithPopTo(new Z_CLWZFragemnt(), HomeFragment.class, false);
                                } else {
                                    supportFragment.start(clwzFragemnt, SupportFragment.SINGLETASK);
                                }
                                break;
                            case R.id.hjzb_item:
                                Z_HJZBFragment z_hjzbFragment = findFragment(Z_HJZBFragment.class);
                                if (z_hjzbFragment == null) {
                                    supportFragment.startWithPopTo(new Z_HJZBFragment(), HomeFragment.class, false);

                                } else {
                                    supportFragment.start(z_hjzbFragment, SupportFragment.SINGLETASK);
                                }
                                break;
                            case R.id.yzsz_item:
                                Z_YZSZActivity yzsz_item = findFragment(Z_YZSZActivity.class);
                                if (yzsz_item == null) {
                                    supportFragment.startWithPopTo(new Z_YZSZActivity(), HomeFragment.class, false);

                                } else {
                                    supportFragment.start(yzsz_item, SupportFragment.SINGLETASK);
                                }
                                break;
                            case R.id.cxgl_item:
                                Z_CXGLFragment cxglFragment = findFragment(Z_CXGLFragment.class);
                                if (cxglFragment == null) {
                                    supportFragment.startWithPopTo(new Z_CXGLFragment(), HomeFragment.class, false);

                                } else {
                                    supportFragment.start(cxglFragment, SupportFragment.SINGLETASK);
                                }
                                break;
                            case R.id.sjfx_item:
                                Z_SJFCFragment sjfcFragment = findFragment(Z_SJFCFragment.class);
                                if (sjfcFragment == null) {
                                    supportFragment.startWithPopTo(new Z_SJFCFragment(), HomeFragment.class, false);
                                } else {
                                    supportFragment.start(sjfcFragment, SupportFragment.SINGLETASK);
                                }
                                break;
                            case R.id.dzbc_item:
                                Z_DZBCActivity dzbcActivity = findFragment(Z_DZBCActivity.class);
                                if (dzbcActivity == null) {
                                    supportFragment.startWithPopTo(new Z_DZBCActivity(), HomeFragment.class, false);
                                } else {
                                    supportFragment.start(dzbcActivity, SupportFragment.SINGLETASK);
                                }
                                break;
                            case R.id.tqxx_item:
                                Z_TQXXFragment tqxxFragment = findFragment(Z_TQXXFragment.class);
                                if (tqxxFragment == null) {
                                    supportFragment.startWithPopTo(new Z_TQXXFragment(), HomeFragment.class, false);
                                } else {
                                    supportFragment.start(tqxxFragment, SupportFragment.SINGLETASK);
                                }
                                break;
                            case R.id.ssjt_item:
                                Z_SSJTFragment ssjtFragment = findFragment(Z_SSJTFragment.class);
                                if (ssjtFragment == null) {
                                    supportFragment.startWithPopTo(new Z_SSJTFragment(), HomeFragment.class, false);
                                } else {
                                    supportFragment.start(ssjtFragment, SupportFragment.SINGLETASK);
                                }
                                break;
                        }
                        drawer.closeDrawers();
                    }
                }, 300);


                return true;
            }
        });
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        drawer.openDrawer(GravityCompat.START);
    }
}

