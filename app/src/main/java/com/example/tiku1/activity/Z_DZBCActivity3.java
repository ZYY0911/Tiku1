package com.example.tiku1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tiku1.AppClient;
import com.example.tiku1.R;
import com.example.tiku1.bean.DZBC;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LogIn Name zhangyingyu
 * Create by 张瀛煜 on 2020-06-11 at 20:47 ：）
 */
public class Z_DZBCActivity3 extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bus_line)
    TextView busLine;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.location_spinner)
    Spinner locationSpinner;
    @BindView(R.id.bt_next)
    Button btNext;
    private DZBC dzbc;
    private String date;
    private List<String> location;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc_layout3);
        ButterKnife.bind(this);
        AppClient.addActivity(this);
        title.setText("定制班车");
        dzbc = (DZBC) getIntent().getSerializableExtra("info");
        date = getIntent().getStringExtra("date");
        location = dzbc.getBusline();
        busLine.setText(location.get(0)+"--"+location.get(location.size()-1));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,location);
        locationSpinner.setAdapter(adapter);



    }

    @OnClick({R.id.change, R.id.bt_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.bt_next:
                Intent intent = new Intent(this, Z_DZBCActivity4.class);
                intent.putExtra("date",date);
                intent.putExtra("name" ,etName.getText());
                intent.putExtra("tel",etTel.getText());
                intent.putExtra("bus",busLine.getText());
                intent.putExtra("location",locationSpinner.getSelectedItem().toString());
                startActivity(intent);
                break;
        }
    }
}
