package com.example.tiku1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tiku1.R;
import com.example.tiku1.adapter.CZJLAdapter;
import com.example.tiku1.bean.CZSql;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public class Z_ZDGLFragment extends BaseFragment {
    @BindView(R.id.sp_px)
    Spinner spPx;
    @BindView(R.id.bt_query)
    Button btQuery;
    @BindView(R.id.my_listView)
    ListView myListView;
    Unbinder unbinder;
    @BindView(R.id.empty_text)
    TextView emptyText;
    private List<CZSql> czSqlList;
    private CZJLAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.zdgl_layout;
    }



    @Override
    protected void initData(Bundle savedInstanceState) {
        czSqlList = new ArrayList<>();
        czSqlList = LitePal.findAll(CZSql.class);
        adapter = new CZJLAdapter(_mActivity, R.layout.czjl_item, czSqlList);
        myListView.setAdapter(adapter);
        myListView.setEmptyView(emptyText);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    protected String setMyTitle() {
        return"账单管理";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    @OnClick(R.id.bt_query)
    public void onViewClicked() {
        switch (spPx.getSelectedItem().toString()) {
            case "时间升序":
                Collections.sort(czSqlList, new Comparator<CZSql>() {
                    @Override
                    public int compare(CZSql o1, CZSql o2) {
                        Date date = null, date1 = null;

                        try {
                            date = format.parse(o1.getSj());
                            date1 = format.parse(o2.getSj());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return date.compareTo(date1);
                    }
                });

                break;
            case "时间降序":
                Collections.sort(czSqlList, new Comparator<CZSql>() {
                    @Override
                    public int compare(CZSql o1, CZSql o2) {
                        Date date = null, date1 = null;
                        try {
                            date = format.parse(o1.getSj());
                            date1 = format.parse(o2.getSj());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return date1.compareTo(date);
                    }
                });
                break;

        }
        adapter.notifyDataSetChanged();
    }
}
