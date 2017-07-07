package com.androidjob.zhinengjiudian.keshihuamenkong;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidjob.zhinengjiudian.R;

/**
 * Created by thinkpad on 2016/8/3.
 */
public class DoorActivity extends Activity{
    private Button mk_fan_hui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.door);
        mk_fan_hui= (Button) findViewById(R.id.mk_fan_hui);

        mk_fan_hui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
