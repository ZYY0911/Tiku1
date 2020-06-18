package com.example.tiku1.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.tiku1.R;

/**
 * Create by 张瀛煜 on 2020-06-05 ：）
 */
public class Z_WZTPActivity extends AppCompatActivity {
    private int [] imagePhoto ={R.mipmap.weizhang1,R.mipmap.weizhang2,R.mipmap.weizhang3,R.mipmap.weizhang4
            ,R.mipmap.weizhang01,R.mipmap.weizhang02,R.mipmap.weizhang03,R.mipmap.weizhang04};

    private int index;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyImageView(this));
        index = getIntent().getIntExtra("Bh",0);

    }

    class MyImageView extends android.support.v7.widget.AppCompatImageView{

        public MyImageView(Context context) {
            super(context);
            initView(context);
        }

        public MyImageView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            initView(context);
        }

        public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView(context);
        }

        private void initView(Context context) {
            setImageResource(imagePhoto[index]);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return super.onTouchEvent(event);
        }
    }
}
