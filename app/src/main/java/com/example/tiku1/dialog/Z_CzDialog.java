package com.example.tiku1.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.tiku1.AppClient;
import com.example.tiku1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public class Z_CzDialog extends DialogFragment {
    @BindView(R.id.cb_next)
    CheckBox cbNext;
    @BindView(R.id.bt_sure)
    Button btSure;
    @BindView(R.id.bt_cancel)
    Button btCancel;
    Unbinder unbinder;
    private AppClient appClient;
    public interface SureAndCancel{
        void DialogResult( boolean is);
    }
    private SureAndCancel sureAndCancel;

    public void setSureAndCancel(SureAndCancel sureAndCancel) {
        this.sureAndCancel = sureAndCancel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cz_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appClient = (AppClient) getActivity().getApplication();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_sure, R.id.bt_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sure:
                appClient.SetShowCz(!cbNext.isChecked());
                sureAndCancel.DialogResult(false);
                this.dismiss();
                break;
            case R.id.bt_cancel:
                sureAndCancel.DialogResult(true);
                break;
        }
    }
}
