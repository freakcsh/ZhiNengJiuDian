package com.androidjob.zhinengjiudian.kefang;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.androidjob.zhinengjiudian.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by thinkpad on 2016/8/16.
 */
public class LightBedFragment extends Fragment {
    private ImageView light_bed_b;
    private Switch light_bed_sw;
    private SeekBar seek_bed;
    static String lbres = null;
    private PrintWriter lbwrite;


    private LightActivity lightActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View bed = inflater.inflate(R.layout.light_bed, container, false);
        light_bed_b = (ImageView) bed.findViewById(R.id.light_bed_b);
        light_bed_sw = (Switch) bed.findViewById(R.id.light_bed_sw);
        seek_bed = (SeekBar) bed.findViewById(R.id.seek_bed);


        if (getArguments() != null) {
            lbres = getArguments().get("message") + "";
            Toast.makeText(getActivity(), "fragment接收的数据" + lbres, Toast.LENGTH_SHORT).show();

            if (lbres.equals("21111")) {
                light_bed_b.setBackgroundResource(R.drawable.light_bed_black);
                light_bed_sw.setChecked(false);
                Toast.makeText(getActivity(), "更新ui******************" + lbres, Toast.LENGTH_SHORT).show();
            } else if (lbres.equals("20111")) {
                light_bed_b.setBackgroundResource(R.drawable.light_bed20);
                light_bed_sw.setChecked(true);
                Toast.makeText(getActivity(), "更新ui" + lbres, Toast.LENGTH_SHORT).show();
            } else if (lbres.equals("SEEKBEDONE")) {
                light_bed_b.setBackgroundResource(R.drawable.light_bed20);
                light_bed_sw.setChecked(true);
                seek_bed.setProgress(10);
                Toast.makeText(getActivity(), "更新ui" + lbres, Toast.LENGTH_SHORT).show();
            } else if (lbres.equals("SEEKBEDTWO")) {
                light_bed_b.setBackgroundResource(R.drawable.light_bed40);
                light_bed_sw.setChecked(true);
                seek_bed.setProgress(30);
                Toast.makeText(getActivity(), "更新ui" + lbres, Toast.LENGTH_SHORT).show();
            } else if (lbres.equals("SEEKBEDTHREE")) {
                light_bed_b.setBackgroundResource(R.drawable.light_bed60);
                light_bed_sw.setChecked(true);
                seek_bed.setProgress(50);
                Toast.makeText(getActivity(), "更新ui" + lbres, Toast.LENGTH_SHORT).show();
            } else if (lbres.equals("SEEKBEDFOUR")) {
                light_bed_b.setBackgroundResource(R.drawable.light_bed80);
                light_bed_sw.setChecked(true);
                seek_bed.setProgress(70);
                Toast.makeText(getActivity(), "更新ui" + lbres, Toast.LENGTH_SHORT).show();
            } else if (lbres.equals("SEEKBEDFIVE")) {
                light_bed_b.setBackgroundResource(R.drawable.light_bed100);
                light_bed_sw.setChecked(true);
                seek_bed.setProgress(90);
                Toast.makeText(getActivity(), "更新ui" + lbres, Toast.LENGTH_SHORT).show();
            }
//            else if (lbres.equals("SEEKBEDZERO")){
//                light_bed_b.setBackgroundResource(R.drawable.light_bed_black);
//                light_bed_sw.setChecked(true);
//                seek_bed.setProgress(0);
//                Toast.makeText(getActivity(),"更新ui"+lbres,Toast.LENGTH_SHORT).show();
//            }


        }
        return bed;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            lbwrite = new PrintWriter(SocketOpen.getSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        light_bed_sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    String lbk = "10111";
                    lbwrite.write(lbk);
                    lbwrite.flush();

                } else {
                    String lbg = "11111";
                    lbwrite.write(lbg);
                    lbwrite.flush();
                }


            }
        });
        seek_bed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (light_bed_sw.isChecked()) {
//                    if (0<seek_bed.getProgress()&&seek_bed.getProgress()<=20){
//                        light_bed_b.setBackgroundResource(R.drawable.light_bed20);
//                    }
//                    else if (20<seek_bed.getProgress()&&seek_bed.getProgress()<=40){
//                        light_bed_b.setBackgroundResource(R.drawable.light_bed40);
//                    }
//                    else if (40<seek_bed.getProgress()&&seek_bed.getProgress()<=60){
//                        light_bed_b.setBackgroundResource(R.drawable.light_bed60);
//                    }
//                    else if (60<seek_bed.getProgress()&&seek_bed.getProgress()<=80){
//                        light_bed_b.setBackgroundResource(R.drawable.light_bed80);
//                    }
//                    else if (80<seek_bed.getProgress()&&seek_bed.getProgress()<=100){
//                        light_bed_b.setBackgroundResource(R.drawable.light_bed100);
//                    }
                    switch (seek_bed.getProgress()) {
                        case 10:
                            String seekRes1 = "SEEKBEDONE";
                            lbwrite.write(seekRes1);
                            lbwrite.flush();
                            break;
                        case 30:
                            String seekRes2 = "SEEKBEDTWO";
                            lbwrite.write(seekRes2);
                            lbwrite.flush();
                            break;
                        case 50:
                            String seekRes3 = "SEEKBEDTHREE";
                            lbwrite.write(seekRes3);
                            lbwrite.flush();
                            break;
                        case 70:
                            String seekRes4 = "SEEKBEDFOUR";
                            lbwrite.write(seekRes4);
                            lbwrite.flush();
                            break;
                        case 90:
                            String seekRes5 = "SEEKBEDFIVE";
                            lbwrite.write(seekRes5);
                            lbwrite.flush();
                            break;
//                        case 0:
//                            String seekRes0="SEEKZERO";
//                            lbwrite.write(seekRes0);
//                            lbwrite.flush();
//                            break;
                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
