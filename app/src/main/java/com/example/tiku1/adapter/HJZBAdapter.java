package com.example.tiku1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tiku1.R;
import com.example.tiku1.bean.YZ;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-06-05 ：）
 */
public class HJZBAdapter extends ArrayAdapter<YZ> {
    private int layout;
    private int max,now;
    private YZ yz;
    private List<YZ> yzs;
    public HJZBAdapter(@NonNull Context context, int resource, @NonNull List<YZ> objects,YZ yz) {
        super(context, resource, objects);
        layout = resource;
        this.yz = yz;
        yzs = objects;
    }


    @Override
    public int getCount() {
        return 6;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        YZ nowYa = yzs.get(yzs.size()-1);
        String str = "";
        switch (position){
            case 0:
                str = "温度";
                max = yz.getTemperature();
                now = nowYa.getTemperature();
                break;
            case 1:
                str = "湿度";
                max = yz.getHumidity();
                now = nowYa.getHumidity();
                break;
            case 2:
                str = "光照";
                max = yz.getIllumination();
                now = nowYa.getIllumination();
                break;
            case 3:
                str = "CO2";
                max = yz.getCo2();
                now = nowYa.getCo2();
                break;
            case 4:
                str = "PM2.5";
                max = yz.getPm25();
                now = nowYa.getPm25();
                break;
            case 5:
                str = "道路状态";
                max = yz.getPath();
                now = nowYa.getPath();
                break;
        }
        holder.tvTitle.setText(str);
        holder.tvNum.setText(now+"");
        if (now>max){
            holder.bgColor.setBackgroundColor(Color.RED);
        }else{
            holder.bgColor.setBackgroundColor(Color.GREEN);
        }
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.bg_color)
        RelativeLayout bgColor;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
