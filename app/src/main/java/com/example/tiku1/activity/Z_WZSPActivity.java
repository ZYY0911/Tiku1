package com.example.tiku1.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

import com.example.tiku1.R;

/**
 * Create by 张瀛煜 on 2020-06-05 ：）
 */
public class Z_WZSPActivity extends AppCompatActivity {
    private int index;
    private MyVideoView videoView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoView = new MyVideoView(this);
        setContentView(videoView);


    }

    class MyVideoView extends VideoView{

        public MyVideoView(Context context) {
            super(context);
            initView();
        }

        public MyVideoView(Context context, AttributeSet attrs) {
            super(context, attrs);
            initView();
        }

        public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView();
        }


        private void initView() {
            index = getIntent().getIntExtra("Bh",0);
            switch (index){
                case 0:
                    this.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.car1));
                    break;
                case 1:
                    this.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.car2));
                    break;
                case 2:
                    this.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.xiaoshipin));
                    break;
                case 3:
                    this.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.car4));
                    break;
            }

            this.start();
            this.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                 finish();
                }
            });
        }


    }

}
