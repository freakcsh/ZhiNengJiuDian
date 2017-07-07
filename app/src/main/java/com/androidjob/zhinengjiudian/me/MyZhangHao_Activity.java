package com.androidjob.zhinengjiudian.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidjob.zhinengjiudian.R;

/**
 * Created by thinkpad on 2016/8/8.
 */
public class MyZhangHao_Activity extends Activity{
    private Button gengHuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wo_zhanghao);
        gengHuan=(Button)findViewById(R.id.gengHuan);
        gengHuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent igenhuan=new Intent();
                igenhuan.setClass(MyZhangHao_Activity.this,GengHuan_Activity.class);
                startActivity(igenhuan);
            }
        });
    }

}
