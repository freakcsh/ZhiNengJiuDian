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
public class WCLightFragment extends Fragment {
    private ImageView img_wc_light;
    private ToggleButton tb_wc_light_on_off;

    private PrintWriter writer;
    static String wcLRes = WCActivity.message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View wcLight = inflater.inflate(R.layout.wc, container, false);
        img_wc_light = (ImageView) wcLight.findViewById(R.id.img_wc_light);
        tb_wc_light_on_off = (ToggleButton) wcLight.findViewById(R.id.tb_wc_light_on_off);

        if (getArguments() != null) {
            wcLRes = getArguments().get("message") + "";
            if (wcLRes.equals("WCLIGHTOFF")) {
                img_wc_light.setBackgroundResource(R.drawable.wc_light_black);
                tb_wc_light_on_off.setChecked(false);
            } else if (wcLRes.equals("WCLIGHTON")) {
                img_wc_light.setBackgroundResource(R.drawable.wc_light_k);
                tb_wc_light_on_off.setChecked(true);
            }
        }
        return wcLight;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        writer = SocketOpen.getSocketPrintWriter();
        tb_wc_light_on_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (tb_wc_light_on_off.isChecked()) {
                    String wcLightOn = "WCLIGHTON";
                    writer.write(wcLightOn);
                    writer.flush();
                } else {
                    String wcLightOff = "WCLIGHTOFF";
                    writer.write(wcLightOff);
                    writer.flush();
                }
            }
        });
    }
}
