package com.example.tiku1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku1.R;
import com.example.tiku1.bean.RLBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-11 at 20:16 ：）
 */
public class RLAdapter extends ArrayAdapter<RLBean> {
    public interface ClickItem {
        void myClick(int position, int bg);
    }

    private ClickItem clickItem;

    public void setClickItem(ClickItem clickItem) {
        this.clickItem = clickItem;
    }

    public RLAdapter(@NonNull Context context, @NonNull List<RLBean> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dzbc_item1, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final RLBean rlBean = getItem(position);
        holder.itemSolar.setText(rlBean.getSolar());
        holder.itemLunar.setText(rlBean.getLunar());
        if (rlBean.getBg() == 0) {
            holder.bgColor.setBackgroundResource(R.drawable.rl_0);
        } else if (rlBean.getBg() == 1) {
            holder.bgColor.setBackgroundResource(R.drawable.rl_1);
        } else {
            holder.bgColor.setBackgroundResource(R.drawable.rl_2);
        }
        holder.bgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rlBean.isEnable()) {
                    clickItem.myClick(position, rlBean.getBg());
                }
            }
        });
        return convertView;
    }


    static
    class ViewHolder {
        @BindView(R.id.item_solar)
        TextView itemSolar;
        @BindView(R.id.item_lunar)
        TextView itemLunar;
        @BindView(R.id.bg_color)
        LinearLayout bgColor;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
