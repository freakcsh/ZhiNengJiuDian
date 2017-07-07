package com.androidjob.zhinengjiudian.kefang;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjob.zhinengjiudian.R;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class AirFragment extends Fragment {
    private ImageButton air_on,air_sm;
    private TextView air_ws,air_ws_fh;
    private TextView air_sw,air_sd;
    private TextView air_zl,air_zy_bf,air_fs,air_ds,air_kg;
    private Button bt_zl,bt_zr,bt_cs,bt_sf,bt_bf,bt_fs,bt_ds,bt_dg;

    private PrintWriter airWriter=null;
    private static String airRes=null;
    private boolean onBT=true;
    private boolean smZT=true;
    private boolean bfZT=true;
    private boolean fsZT=true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View air=inflater.inflate(R.layout.air,container,false);
        air_on=(ImageButton)air.findViewById(R.id.air_on);
        air_sm=(ImageButton)air.findViewById(R.id.air_sm);

        air_ws=(TextView)air.findViewById(R.id.air_ws);
        air_ws_fh=(TextView)air.findViewById(R.id.air_ws_fh);
        air_sw=(TextView)air.findViewById(R.id.air_sw);
        air_sd=(TextView)air.findViewById(R.id.air_sd);
        air_zl=(TextView)air.findViewById(R.id.air_zl);
        air_zy_bf=(TextView)air.findViewById(R.id.air_zy_bf);
        air_fs=(TextView)air.findViewById(R.id.air_fs);
        air_ds=(TextView)air.findViewById(R.id.air_ds);
        air_kg=(TextView)air.findViewById(R.id.air_kg);

        bt_zl=(Button)air.findViewById(R.id.bt_zl);
        bt_zr=(Button)air.findViewById(R.id.bt_zr);
        bt_cs=(Button)air.findViewById(R.id.bt_cs);
        bt_sf=(Button)air.findViewById(R.id.bt_sf);
        bt_bf=(Button)air.findViewById(R.id.bt_bf);
        bt_fs=(Button)air.findViewById(R.id.bt_fs);
        bt_ds=(Button)air.findViewById(R.id.bt_ds);
        bt_dg=(Button)air.findViewById(R.id.bt_dg);


        if (getArguments()!=null){
            airRes=getArguments().get("message")+"";
            Toast.makeText(getActivity(),"fragment接收的数据"+airRes,Toast.LENGTH_SHORT).show();
            if (airRes.equals("AIRON")){
                onBT=false;
                Toast.makeText(getActivity(),"更新ui******************"+airRes,Toast.LENGTH_SHORT).show();
            }
            else if (airRes.equals("AIROFF")){
                onBT=true;
                Toast.makeText(getActivity(),"更新ui"+airRes,Toast.LENGTH_SHORT).show();
            }


            else if (airRes.equals("SMON")){
                smZT=false;
            }
            else if (airRes.equals("SMOFF")){
                smZT=true;
            }

            else if (airRes.equals("ZHILENG")){
                air_zl.setText("制冷");
                Toast.makeText(getActivity(),"更新ui"+airRes,Toast.LENGTH_SHORT).show();
            }
            else if (airRes.equals("ZHIRE")){
                air_zl.setText("制热");
                Toast.makeText(getActivity(),"更新ui"+airRes,Toast.LENGTH_SHORT).show();
            }
            else if (airRes.equals("CHUSHI")){
                air_zl.setText("除湿");
                Toast.makeText(getActivity(),"更新ui"+airRes,Toast.LENGTH_SHORT).show();
            }
            else if (airRes.equals("SONGFENG")){
                air_zl.setText("送风");
            }
            else if (airRes.equals("LRBAIFENG")){
                air_zy_bf.setText("左右摆风");

            }
            else if (airRes.equals("SXBAIFENG")){
                air_zy_bf.setText("上下摆风");
                Toast.makeText(getActivity(),"更新ui"+airRes,Toast.LENGTH_SHORT).show();
            }
            else if (airRes.equals("SMALL")){
                air_fs.setText("弱");
                Toast.makeText(getActivity(),"更新ui"+airRes,Toast.LENGTH_SHORT).show();
            }
            else if (airRes.equals("BIG")){
                air_fs.setText("强");
                Toast.makeText(getActivity(),"更新ui"+airRes,Toast.LENGTH_SHORT).show();
            }

        }
        return air;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            airWriter=new PrintWriter(SocketOpen.getSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //开关按钮监听
        air_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBT){
                    String on="AIRON";
                    airWriter.write(on);
                    airWriter.flush();
                }
                else {
                    String off="AIROFF";
                    airWriter.write(off);
                    airWriter.flush();
                }

//                air_kg.setText("开");
            }
        });
        //睡眠按钮
        air_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (smZT){
                String smOn="SMON";
                airWriter.write(smOn);
                airWriter.flush();
            }
                else {
                String smOff="SMOFF";
                airWriter.write(smOff);
                airWriter.flush();
            }
//                air_kg.setText("睡眠");
            }
        });
        //制冷功能
        bt_zl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zl="ZHILENG";
                airWriter.write(zl);
                airWriter.flush();
//                air_zl.setText("制冷");
            }
        });
        //制热功能
        bt_zr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zr="ZHIRE";
                airWriter.write(zr);
                airWriter.flush();
//                air_zl.setText("制热");
            }
        });
        //除湿功能
        bt_cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cs="CHUSHI";
                airWriter.write(cs);
                airWriter.flush();
//                air_zl.setText("除湿");
            }
        });
        //送风功能
        bt_sf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sf="SONGFENG";
                airWriter.write(sf);
                airWriter.flush();
//                air_zl.setText("送风");
            }
        });
        //摆风功能
        bt_bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bfZT){
                    String lr="LRBAIFENG";
                    airWriter.write(lr);
                    airWriter.flush();
                }
                else {
                    String sx="SXBAIFENG";
                    airWriter.write(sx);
                    airWriter.flush();

                }
//                air_zy_bf.setText("左右摆风");
            }
        });
        //风速功能
        bt_fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (fsZT){
                   String x="SMALL";
                   airWriter.write(x);
                   airWriter.flush();
               }
                else {
                   String b="BIG";
                   airWriter.write(b);
                   airWriter.flush();
               }
//                air_fs.setText("弱");
            }
        });
        //定时功能
        bt_ds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                air_ds.setText("3.5");

            }
        });
        //灯光功能
        bt_dg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
