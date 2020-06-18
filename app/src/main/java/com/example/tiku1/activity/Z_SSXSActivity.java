package com.example.tiku1.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku1.AppClient;
import com.example.tiku1.R;
import com.example.tiku1.fragment.SSXS_Fragment1;
import com.example.tiku1.fragment.SSXS_Fragment2;
import com.example.tiku1.fragment.SSXS_Fragment3;
import com.example.tiku1.fragment.SSXS_Fragment4;
import com.example.tiku1.fragment.SSXS_Fragment5;
import com.example.tiku1.fragment.SSXS_Fragment6;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 张瀛煜 on 2020-06-05 ：）
 */
public class Z_SSXSActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.my_layout)
    LinearLayout myLayout;
    private List<Fragment> fragments;
    private AppClient appClient;
    private int index = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ssxs_layout);
        ButterKnife.bind(this);
        title.setText("实时显示");
        index = getIntent().getIntExtra("index",0);
        appClient = (AppClient) getApplication();
        fragments = new ArrayList<>();
        fragments.add(new SSXS_Fragment1(appClient));
        fragments.add(new SSXS_Fragment2(appClient));
        fragments.add(new SSXS_Fragment3(appClient));
        fragments.add(new SSXS_Fragment4(appClient));
        fragments.add(new SSXS_Fragment5(appClient));
        fragments.add(new SSXS_Fragment6(appClient));
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < myLayout.getChildCount(); j++) {
                    ImageView imageView = (ImageView) myLayout.getChildAt(j);
                    if (j == i) {
                        imageView.setImageResource(R.drawable.ic_page_selevt);
                    } else {
                        imageView.setImageResource(R.drawable.ic_page_noselevt);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(index);
        for (int i = 0; i < fragments.size(); i++) {
            ImageView imageView = new ImageView(this);
            if (i==index){
                imageView.setImageResource(R.drawable.ic_page_selevt);
            }else{
                imageView.setImageResource(R.drawable.ic_page_noselevt);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50,50));
            myLayout.addView(imageView);

        }
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }


    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
