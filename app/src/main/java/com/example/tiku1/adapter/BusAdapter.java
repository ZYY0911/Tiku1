package com.example.tiku1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tiku1.R;
import com.example.tiku1.bean.BusHistory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-12 at 19:21 ：）
 */
public class BusAdapter extends ArrayAdapter<BusHistory> {
    public BusAdapter(@NonNull Context context, @NonNull List<BusHistory> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bus_history_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemMsg.setText(getItem(position).getMsg());
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.item_msg)
        TextView itemMsg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
