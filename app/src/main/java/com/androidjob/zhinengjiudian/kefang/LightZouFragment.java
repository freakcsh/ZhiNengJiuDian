package com.androidjob.zhinengjiudian.kefang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.androidjob.zhinengjiudian.R;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by thinkpad on 2016/8/16.
 */
public class LightZouFragment extends Fragment{
    private ImageView img_z1,img_z2;
    private SeekBar seek_z1,seek_z2;
    private ToggleButton tb_z_kai_g1,tb_z_kai_g2,tb_z_kai_g3;
    private Button bt_z_ldu,btz_dinShi,btz_qc_dinShi;


    private PrintWriter lzwrite;
    private String lzres=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View zou=inflater.inflate(R.layout.light_zoulang,container,false);
        //壁灯1和壁灯2初始化
        img_z1=(ImageView)zou.findViewById(R.id.img_z1);
        img_z2=(ImageView)zou.findViewById(R.id.img_z2);
        //SeekBar1和SeekBar2初始化
        seek_z1=(SeekBar)zou.findViewById(R.id.seek_z1);
        seek_z2=(SeekBar)zou.findViewById(R.id.seek_z2);
        //ToggleButton1,2,3初始化
        tb_z_kai_g1=(ToggleButton)zou.findViewById(R.id.tb_z_kai_g1);
        tb_z_kai_g2=(ToggleButton)zou.findViewById(R.id.tb_z_kai_g2);
        tb_z_kai_g3=(ToggleButton)zou.findViewById(R.id.tb_z_kai_g3);
        //查看亮度、定时、清除定时按钮初始化
        bt_z_ldu=(Button)zou.findViewById(R.id.bt_z_ldu);
        btz_dinShi=(Button)zou.findViewById(R.id.btz_dinShi);
        btz_qc_dinShi=(Button)zou.findViewById(R.id.btz_qc_dinShi);

        if (getArguments()!=null){
            lzres=getArguments().get("message")+"";
//if (savedInstanceState!=null){
//    FragmentManager fragmentManager = getFragmentManager();
//    FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
//
//    beginTransaction.replace(R.id.lZouLayout, new LightZouFragment());
//    beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//    beginTransaction.addToBackStack(null);
//
//    beginTransaction.commit();
//}
            Toast.makeText(getActivity(),"走廊灯接收的消息："+lzres,Toast.LENGTH_SHORT).show();
            if (lzres.equals("LZON1")){
                img_z1.setBackgroundResource(R.drawable.light_z20);
                    tb_z_kai_g1.setChecked(true);
                    Toast.makeText(getActivity(),"走廊灯更新开灯ui："+lzres,Toast.LENGTH_SHORT).show();
            }else if (lzres.equals("LZOFF1")){
                    img_z1.setBackgroundResource(R.drawable.light_z_black);
                    tb_z_kai_g1.setChecked(false);
                    Toast.makeText(getActivity(),"走廊灯更新关灯ui："+lzres,Toast.LENGTH_SHORT).show();
            }

        }
        return zou;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            lzwrite=SocketOpen.getSocketPrintWriter();
        //设置监听事件
        bt_z_ldu.setOnClickListener(lightZOnClick);
        btz_dinShi.setOnClickListener(lightZOnClick);
        btz_qc_dinShi.setOnClickListener(lightZOnClick);
        //进度条监听事件
        seek_z1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(tb_z_kai_g3.isChecked()){
                    tb_z_kai_g1.setChecked(true);
                    if(0<i&&i<=20){
                        img_z1.setBackgroundResource(R.drawable.light_z20);
                    }
                    else if (20<i&&i<=40){
                        img_z1.setBackgroundResource(R.drawable.light_z40);
                    }
                    else if (40<i&&i<=60){
                        img_z1.setBackgroundResource(R.drawable.light_z60);
                    }
                    else if (60<i&&i<=80){
                        img_z1.setBackgroundResource(R.drawable.light_z80);
                    }
                    else if (80<i&&i<=100){
                        img_z1.setBackgroundResource(R.drawable.light_z100);
                    }
                }
                else {
                    img_z1.setBackgroundResource(R.drawable.light_z_black);
                    seek_z1.setProgress(0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seek_z2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(tb_z_kai_g3.isChecked()){
                    tb_z_kai_g2.setChecked(true);
                    if(0<i&&i<=20){
                        img_z2.setBackgroundResource(R.drawable.light_z20);
                    }
                    else if (20<i&&i<=40){
                        img_z2.setBackgroundResource(R.drawable.light_z40);
                    }
                    else if (40<i&&i<=60){
                        img_z2.setBackgroundResource(R.drawable.light_z60);
                    }
                    else if (60<i&&i<=80){
                        img_z2.setBackgroundResource(R.drawable.light_z80);
                    }
                    else if (80<i&&i<=100){
                        img_z2.setBackgroundResource(R.drawable.light_z100);
                    }
                }
                else {
                    img_z2.setBackgroundResource(R.drawable.light_z_black);
                    seek_z2.setProgress(0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //ToggleButton按钮监听事件
        tb_z_kai_g1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    String lz="LZON1";
                    lzwrite.write(lz);
                    lzwrite.flush();
                }
                else {
                    String lzz="LZOFF1";
                    lzwrite.write(lzz);
                    lzwrite.flush();
                }
//                img_z1.setBackgroundResource(b?R.drawable.light_z40:R.drawable.light_z_black);
            }
        });
        tb_z_kai_g2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                img_z2.setBackgroundResource(b?R.drawable.light_z40:R.drawable.light_z_black);
            }
        });
        tb_z_kai_g3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b==true) {
                    tb_z_kai_g1.setChecked(true);
                    tb_z_kai_g2.setChecked(true);
                    img_z1.setBackgroundResource(R.drawable.light_z40);
                    img_z2.setBackgroundResource(R.drawable.light_z40);
                }
                else
                {
                    if(tb_z_kai_g1.isChecked()&&tb_z_kai_g2.isChecked()){
                        tb_z_kai_g1.setChecked(false);
                        tb_z_kai_g2.setChecked(false);
                    }

                    img_z1.setBackgroundResource(R.drawable.light_z_black);
                    img_z2.setBackgroundResource(R.drawable.light_z_black);

                }
            }
        });

    }
    public View.OnClickListener lightZOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bt_z_ldu:
                    Toast.makeText(getActivity(),"查看亮度",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btz_dinShi:
                    Toast.makeText(getActivity(),"定时",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btz_qc_dinShi:
                    Toast.makeText(getActivity(),"清除定时",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

}
