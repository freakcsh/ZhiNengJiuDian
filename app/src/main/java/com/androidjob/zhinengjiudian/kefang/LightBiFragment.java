package com.androidjob.zhinengjiudian.kefang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class LightBiFragment extends Fragment{

    private ImageView img_bi1,img_bi2;
    private SeekBar seek_bi1,seek_bi2;
    private ToggleButton tb_bi_kai_g1,tb_bi_kai_g2,tb_bi_kai_g3;
    private Button bt_bi_ldu,bt_dinShi,bt_qc_dinShi;



    private PrintWriter lbiwrite;
    private String lbires=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View bi=inflater.inflate(R.layout.light_bi,container,false);
        return bi;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //壁灯1和壁灯2初始化
        img_bi1=(ImageView)getView().findViewById(R.id.img_bi1);
        img_bi2=(ImageView)getView().findViewById(R.id.img_bi2);
        //SeekBar1和SeekBar2初始化
        seek_bi1=(SeekBar)getView().findViewById(R.id.seek_bi1);
        seek_bi2=(SeekBar)getView().findViewById(R.id.seek_bi2);
        //ToggleButton1,2,3初始化
        tb_bi_kai_g1=(ToggleButton)getView().findViewById(R.id.tb_bi_kai_g1);
        tb_bi_kai_g2=(ToggleButton)getView().findViewById(R.id.tb_bi_kai_g2);
        tb_bi_kai_g3=(ToggleButton)getView().findViewById(R.id.tb_bi_kai_g3);
        //查看亮度、定时、清除定时按钮初始化
        bt_bi_ldu=(Button)getView().findViewById(R.id.bt_bi_ldu);
        bt_dinShi=(Button)getView().findViewById(R.id.bt_dinShi);
        bt_qc_dinShi=(Button)getView().findViewById(R.id.bt_qc_dinShi);


        try {
            lbiwrite=new PrintWriter(SocketOpen.getSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //设置监听事件
        bt_bi_ldu.setOnClickListener(lightBiOnClick);
        bt_dinShi.setOnClickListener(lightBiOnClick);
        bt_qc_dinShi.setOnClickListener(lightBiOnClick);
        //进度条监听事件
        seek_bi1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(tb_bi_kai_g3.isChecked()){
                    tb_bi_kai_g1.setChecked(true);
                    if(0<i&&i<=20){
                        img_bi1.setBackgroundResource(R.drawable.light_bi20);
                    }
                    else if (20<i&&i<=40){
                        img_bi1.setBackgroundResource(R.drawable.light_bi40);
                    }
                    else if (40<i&&i<=60){
                        img_bi1.setBackgroundResource(R.drawable.light_bi60);
                    }
                    else if (60<i&&i<=80){
                        img_bi1.setBackgroundResource(R.drawable.light_bi80);
                    }
                    else if (80<i&&i<=100){
                        img_bi1.setBackgroundResource(R.drawable.light_bi100);
                    }
                }
                else {
                    img_bi1.setBackgroundResource(R.drawable.light_bi_black);
                    seek_bi1.setProgress(0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seek_bi2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(tb_bi_kai_g3.isChecked()){
                    tb_bi_kai_g2.setChecked(true);
                    if(0<i&&i<=20){
                        img_bi2.setBackgroundResource(R.drawable.light_bi20);
                    }
                    else if (20<i&&i<=40){
                        img_bi2.setBackgroundResource(R.drawable.light_bi40);
                    }
                    else if (40<i&&i<=60){
                        img_bi2.setBackgroundResource(R.drawable.light_bi60);
                    }
                    else if (60<i&&i<=80){
                        img_bi2.setBackgroundResource(R.drawable.light_bi80);
                    }
                    else if (80<i&&i<=100){
                        img_bi2.setBackgroundResource(R.drawable.light_bi100);
                    }
                }
                else {
                    img_bi2.setBackgroundResource(R.drawable.light_bi_black);
                    seek_bi2.setProgress(0);
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
        tb_bi_kai_g1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                img_bi1.setBackgroundResource(b?R.drawable.light_bi40:R.drawable.light_bi_black);
            }
        });
        tb_bi_kai_g2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                img_bi2.setBackgroundResource(b?R.drawable.light_bi40:R.drawable.light_bi_black);
            }
        });
        tb_bi_kai_g3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b==true) {
                    tb_bi_kai_g1.setChecked(true);
                    tb_bi_kai_g2.setChecked(true);
                    img_bi1.setBackgroundResource(R.drawable.light_bi40);
                    img_bi2.setBackgroundResource(R.drawable.light_bi40);
                }
                else
                {
                    if(tb_bi_kai_g1.isChecked()&&tb_bi_kai_g2.isChecked()){
                        tb_bi_kai_g1.setChecked(false);
                        tb_bi_kai_g2.setChecked(false);
                    }

                    img_bi1.setBackgroundResource(R.drawable.light_bi_black);
                    img_bi2.setBackgroundResource(R.drawable.light_bi_black);

                }
            }
        });

    }
    public View.OnClickListener lightBiOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bt_bi_ldu:
                    Toast.makeText(getActivity(),"查看亮度",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bt_dinShi:
                    Toast.makeText(getActivity(),"定时",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bt_qc_dinShi:
                    Toast.makeText(getActivity(),"清除定时",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
}
