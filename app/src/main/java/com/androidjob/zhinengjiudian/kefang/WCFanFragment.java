package com.androidjob.zhinengjiudian.kefang;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.androidjob.zhinengjiudian.R;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class WCFanFragment extends Fragment {
    private ImageView img_wc_fan;
    private ToggleButton tb_wc_on_off;


    private PrintWriter writer;
    static String wcFanRes = WCActivity.message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fan = inflater.inflate(R.layout.wc_fan, container, false);
        img_wc_fan = (ImageView) fan.findViewById(R.id.img_wc_fan);
        tb_wc_on_off = (ToggleButton) fan.findViewById(R.id.tb_wc_on_off);

        if (getArguments() != null) {
            wcFanRes = getArguments().get("message") + "";
            if (wcFanRes.equals("21111")) {
                img_wc_fan.setBackgroundResource(R.drawable.wc_fan_g);
                tb_wc_on_off.setChecked(false);
            } else if (wcFanRes.equals("20111")) {
                img_wc_fan.setBackgroundResource(R.drawable.wc_fan_k);
                tb_wc_on_off.setChecked(true);
            }
        }
        return fan;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        writer = SocketOpen.getSocketPrintWriter();
        tb_wc_on_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (tb_wc_on_off.isChecked()) {
                    String fanResOn = "20111";
                    writer.write(fanResOn);
                    writer.flush();
                } else {
                    String fanResOff = "21111";
                    writer.write(fanResOff);
                    writer.flush();
                }
            }
        });
    }
}
