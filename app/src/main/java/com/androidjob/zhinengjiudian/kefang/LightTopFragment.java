package com.androidjob.zhinengjiudian.kefang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.androidjob.zhinengjiudian.R;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by thinkpad on 2016/8/16.
 */
public class LightTopFragment extends Fragment {
    private ImageView light_top_hei;
    private Switch light_top_kg;
    boolean k;
    boolean g = false;
    private PrintWriter writer;
    static String lTRes = LightActivity.message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View top = inflater.inflate(R.layout.light_top, container, false);
        light_top_hei = (ImageView) top.findViewById(R.id.light_top_hei);
        light_top_kg = (Switch) top.findViewById(R.id.light_top_kg);
        k = light_top_kg.isChecked();

        if (getArguments() != null) {
            lTRes = getArguments().get("message") + "";
            if (lTRes.equals("11111")) {
                light_top_hei.setBackgroundResource(R.drawable.light_top_black);
                light_top_kg.setChecked(false);
            } else if (lTRes.equals("10111")) {
                light_top_hei.setBackgroundResource(R.drawable.light_top);
                light_top_kg.setChecked(true);
            }
        }
        return top;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        writer = SocketOpen.getSocketPrintWriter();
        light_top_kg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    String aa = "10111";
                    writer.write(aa);
                    writer.flush();

                } else {
                    String bb = "11111";
                    writer.write(bb);
                    writer.flush();
                }
            }
        });
    }

}
