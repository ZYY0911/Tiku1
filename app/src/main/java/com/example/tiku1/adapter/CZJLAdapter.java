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
import com.example.tiku1.bean.CZSql;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by 张瀛煜 on 2020-06-04 ：）
 */
public class CZJLAdapter extends ArrayAdapter<CZSql> {
    private int layout;

    public CZJLAdapter(@NonNull Context context, int resource, @NonNull List<CZSql> objects) {
        super(context, resource, objects);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.czjl_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CZSql czSql = getItem(position);
        holder.tvCh.setText(czSql.getCp());
        holder.tvCzje.setText(czSql.getJe());
        holder.tvCzr.setText(czSql.getName());
        holder.tvXh.setText(position+1+"");
        holder.tvSj.setText(czSql.getSj());
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_xh)
        TextView tvXh;
        @BindView(R.id.tv_ch)
        TextView tvCh;
        @BindView(R.id.tv_czje)
        TextView tvCzje;
        @BindView(R.id.tv_czr)
        TextView tvCzr;
        @BindView(R.id.tv_sj)
        TextView tvSj;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
