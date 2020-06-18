package com.example.tiku1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.example.tiku1.R;
import com.example.tiku1.bean.SJFX;
import com.example.tiku1.net.VolleyLo;
import com.example.tiku1.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-10 at 17:21 ：）
 */
public class Z_SJFCFragment extends BaseFragment {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.layout)
    LinearLayout layout;
    Unbinder unbinder;
    private List<Fragment> fragments;
    private List<SJFX> sjfxes;

    @Override
    protected String setMyTitle() {
        return "数据分析";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.sjfx_layout;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        fragments = new ArrayList<>();
        setVolley();
    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("get_peccancy")
                .setJsonObject("UserName", "user1")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        sjfxes = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<SJFX>>() {
                        }.getType());
                        setList1();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }
    private List<SJFX> yes,no;
    private Map<String,Integer> map;
    private void setList1() {
        yes = new ArrayList<>();
        no = new ArrayList<>();
        for (int i = 0; i <sjfxes.size(); i++) {
            SJFX sjfx = sjfxes.get(i);
            if (sjfx.getPaddr().length()==0){
                no.add(sjfx);
            }else{
                yes.add(sjfx);
            }
        }
        setList2();
    }

    private void setList2() {
        map = new HashMap<>();
        for (int i = 0; i < yes.size(); i++) {
            String id = yes.get(i).getCarnumber();
            Integer  count = map.get(id);
            map.put(id,count==null?0:count+1);
        }
        setList3();
        setViewPager();
    }
    private  int a,b,c;
    private void setList3() {
        for (Integer count :map.values()){
            if (    count<=2){
                a++;
            }else if (count>=3&&count<=5){
                b++;
            }else {
                c++;
            }
        }
    }

    private void setViewPager() {
        fragments.add(new SJFX_Framgnet1(map,yes.size()));
        fragments.add(new SJFX_Framgnet2(a,b,c));
        viewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j = 0; j < layout.getChildCount(); j++) {
                    ImageView imageView = (ImageView) layout.getChildAt(j);
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
        for (int i = 0; i < fragments.size(); i++) {
            ImageView imageView = new ImageView(_mActivity);
            if (i == 0) {
                imageView.setImageResource(R.drawable.ic_page_selevt);
            } else {
                imageView.setImageResource(R.drawable.ic_page_noselevt);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(60, 50));
            layout.addView(imageView);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
