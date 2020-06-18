package com.example.tiku1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiku1.activity.MainActivity;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public abstract class BaseFragment extends SupportFragment {
    private MainActivity mActivity;





    public MainActivity getmActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(getActivity())) {
            EventBus.getDefault().register(getActivity());
        }
        initData(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().postSticky(setMyTitle());
    }

    protected abstract String setMyTitle();

    @Override
    public void onDestroy() {
        super.onDestroy();
//        ISupportFragment topFragment = getPreFragment();
//        if (topFragment.getClass()==HomeFragment.class) {
            EventBus.getDefault().postSticky("主页面");
//        }

        EventBus.getDefault().unregister(getActivity());
    }

    protected abstract int getLayoutId();


    protected abstract void initData(Bundle savedInstanceState);

}
