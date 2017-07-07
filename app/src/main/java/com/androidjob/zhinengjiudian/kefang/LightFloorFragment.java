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
public class LightFloorFragment extends Fragment{
    private ImageView img_floor1,img_floor2;
    private SeekBar seek_floor1,seek_floor2;
    private ToggleButton tb_floor_kai_g1,tb_floor_kai_g2,tb_floor_kai_g3;
    private Button bt_floor_ldu,bt_floor_dinShi,bt_qc_floor_dinShi;


    private PrintWriter lfwrite;
    private String lfres=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View floor=inflater.inflate(R.layout.light_floor,container,false);
        //壁灯1和壁灯2初始化
        img_floor1=(ImageView)floor.findViewById(R.id.img_floor1);
        img_floor2=(ImageView)floor.findViewById(R.id.img_floor2);
        //SeekBar1和SeekBar2初始化
        seek_floor1=(SeekBar)floor.findViewById(R.id.seek_floor1);
        seek_floor2=(SeekBar)floor.findViewById(R.id.seek_floor2);
        //ToggleButton1,2,3初始化
        tb_floor_kai_g1=(ToggleButton)floor.findViewById(R.id.tb_floor_kai_g1);
        tb_floor_kai_g2=(ToggleButton)floor.findViewById(R.id.tb_floor_kai_g2);
        tb_floor_kai_g3=(ToggleButton) floor.findViewById(R.id.tb_floor_kai_g3);
        //查看亮度、定时、清除定时按钮初始化
        bt_floor_ldu=(Button)floor.findViewById(R.id.bt_floor_ldu);
        bt_floor_dinShi=(Button)floor.findViewById(R.id.bt_floor_dinShi);
        bt_qc_floor_dinShi=(Button)floor.findViewById(R.id.bt_qc_floor_dinShi);
        if (getArguments()!=null){
            lfres=getArguments().get("sss")+"";
            Toast.makeText(getActivity(),"fragment接收的数据"+lfres,Toast.LENGTH_SHORT).show();
//            if (lfres.equals("LFLOORALLON")){
//                img_floor1.setBackgroundResource(R.drawable.light_floor);
//                img_floor2.setBackgroundResource(R.drawable.light_floor);
//                tb_floor_kai_g2.setChecked(true);
//                tb_floor_kai_g1.setChecked(true);
//                tb_floor_kai_g3.setChecked(true);
//            }
//            else if (lfres.equals("LFLOORALLOFF")){
//                img_floor1.setBackgroundResource(R.drawable.light_floor_black);
//                img_floor2.setBackgroundResource(R.drawable.light_floor_black);
//                tb_floor_kai_g2.setChecked(false);
//                tb_floor_kai_g1.setChecked(false);
//                tb_floor_kai_g3.setChecked(false);
//            }
//            if (lfres.equals("LFLOORONEOFF")){
//                img_floor1.setBackgroundResource(R.drawable.light_floor_black);
//                tb_floor_kai_g1.setChecked(false);
//                Toast.makeText(getActivity(),"更新ui******************"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("LFLOORONEON")){
//                img_floor1.setBackgroundResource(R.drawable.light_floor);
//                tb_floor_kai_g1.setChecked(true);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("LFLOORTWOOFF")){
//                img_floor2.setBackgroundResource(R.drawable.light_floor_black);
//                tb_floor_kai_g2.setChecked(false);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("LFLOORTWOON")){
//                img_floor2.setBackgroundResource(R.drawable.light_floor);
//                tb_floor_kai_g2.setChecked(true);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            //seekbar1的状态信息
//            else if (lfres.equals("SEEKFLOORONE")){
//                img_floor1.setBackgroundResource(R.drawable.light_floor);
//                tb_floor_kai_g1.setChecked(true);
//                seek_floor1.setProgress(10);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("SEEKFLOORTWO")){
//                img_floor1.setBackgroundResource(R.drawable.light_floor25);
//                tb_floor_kai_g1.setChecked(true);
//                seek_floor1.setProgress(30);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("SEEKFLOORTHREE")){
//                img_floor1.setBackgroundResource(R.drawable.light_floor50);
//                tb_floor_kai_g1.setChecked(true);
//                seek_floor1.setProgress(50);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("SEEKFLOORFOUR")){
//                img_floor1.setBackgroundResource(R.drawable.light_floor75);
//                tb_floor_kai_g1.setChecked(true);
//                seek_floor1.setProgress(70);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("SEEKFLOORFIVE")){
//                img_floor1.setBackgroundResource(R.drawable.light_floor100);
//                tb_floor_kai_g1.setChecked(true);
//                seek_floor1.setProgress(90);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
////            else if (lfres.equals("SEEKFLOORZERO")){
////                img_floor1.setBackgroundResource(R.drawable.light_floor_black);
////                tb_floor_kai_g1.setChecked(true);
////                seek_floor1.setProgress(0);
////                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
////            }
//            //seekbar2的状态信息
//            else if (lfres.equals("SEEKTWOFLOORONE")){
//                img_floor1.setBackgroundResource(R.drawable.light_floor);
//                tb_floor_kai_g2.setChecked(true);
//                seek_floor2.setProgress(10);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("SEEKTWOFLOORTWO")){
//                img_floor2.setBackgroundResource(R.drawable.light_floor25);
//                tb_floor_kai_g2.setChecked(true);
//                seek_floor2.setProgress(30);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("SEEKTWOFLOORTHREE")){
//                img_floor2.setBackgroundResource(R.drawable.light_floor50);
//                tb_floor_kai_g2.setChecked(true);
//                seek_floor2.setProgress(50);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("SEEKTWOFLOORFOUR")){
//                img_floor2.setBackgroundResource(R.drawable.light_floor75);
//                tb_floor_kai_g2.setChecked(true);
//                seek_floor2.setProgress(70);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("SEEKTWOFLOORFIVE")){
//                img_floor2.setBackgroundResource(R.drawable.light_floor100);
//                tb_floor_kai_g2.setChecked(true);
//                seek_floor2.setProgress(90);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
//            else if (lfres.equals("SEEKTWOFLOORZERO")){
//                img_floor2.setBackgroundResource(R.drawable.light_floor_black);
//                tb_floor_kai_g2.setChecked(true);
//                seek_floor2.setProgress(0);
//                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
//            }
            if (lfres.equals("LFLOORONEON")){
               img_floor1.setBackgroundResource(R.drawable.light_floor_black);
                tb_floor_kai_g1.setChecked(false);
                Toast.makeText(getActivity(),"更新ui******************"+lfres,Toast.LENGTH_SHORT).show();
            }
            else if (lfres.equals("LFLOORONEOFF")){
               img_floor1.setBackgroundResource(R.drawable.light_floor25);
                tb_floor_kai_g1.setChecked(true);
                Toast.makeText(getActivity(),"更新ui"+lfres,Toast.LENGTH_SHORT).show();
            }

        }

        return floor;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            lfwrite=new PrintWriter(SocketOpen.getSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        //设置监听事件
        bt_floor_ldu.setOnClickListener(lightFloorOnClick);
        bt_floor_dinShi.setOnClickListener(lightFloorOnClick);
        bt_qc_floor_dinShi.setOnClickListener(lightFloorOnClick);

        //进度条监听事件
        seek_floor1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                if(tb_floor_kai_g3.isChecked()){
//                    tb_floor_kai_g1.setChecked(true);
//                    if(0<i&&i<=20){
//                        img_floor1.setBackgroundResource(R.drawable.light_floor);
//                    }
//                    else if (20<i&&i<=40){
//                        img_floor1.setBackgroundResource(R.drawable.light_floor25);
//                    }
//                    else if (40<i&&i<=60){
//                        img_floor1.setBackgroundResource(R.drawable.light_floor50);
//                    }
//                    else if (60<i&&i<=80){
//                        img_floor1.setBackgroundResource(R.drawable.light_floor75);
//                    }
//                    else if (80<i&&i<=100){
//                        img_floor1.setBackgroundResource(R.drawable.light_floor100);
//                    }
//                }
//                else {
//                    img_floor1.setBackgroundResource(R.drawable.light_floor_black);
//                    seek_floor1.setProgress(0);
//                }


                if (tb_floor_kai_g1.isChecked()){
                    switch (seek_floor1.getProgress()){
                        case 10:
                            String seekRes1="SEEKFLOORONE";
                            lfwrite.write(seekRes1);
                            lfwrite.flush();
                            break;
                        case 30:
                            String seekRes2="SEEKFLOORTWO";
                            lfwrite.write(seekRes2);
                            lfwrite.flush();
                            break;
                        case 50:
                            String seekRes3="SEEKFLOORTHREE";
                            lfwrite.write(seekRes3);
                            lfwrite.flush();
                            break;
                        case 70:
                            String seekRes4="SEEKFLOORFOUR";
                            lfwrite.write(seekRes4);
                            lfwrite.flush();
                            break;
                        case 90:
                            String seekRes5="SEEKFLOORFIVE";
                            lfwrite.write(seekRes5);
                            lfwrite.flush();
                            break;
//                        case 0:
//                            String seekRes0="SEEKFLOORZERO";
//                            lfwrite.write(seekRes0);
//                            lfwrite.flush();
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
        seek_floor2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                if(tb_floor_kai_g3.isChecked()){
//                    tb_floor_kai_g2.setChecked(true);
//                    if(0<i&&i<=20){
//                        img_floor2.setBackgroundResource(R.drawable.light_floor);
//                    }
//                    else if (20<i&&i<=40){
//                        img_floor2.setBackgroundResource(R.drawable.light_floor25);
//                    }
//                    else if (40<i&&i<=60){
//                        img_floor2.setBackgroundResource(R.drawable.light_floor50);
//                    }
//                    else if (60<i&&i<=80){
//                        img_floor2.setBackgroundResource(R.drawable.light_floor75);
//                    }
//                    else if (80<i&&i<=100){
//                        img_floor2.setBackgroundResource(R.drawable.light_floor100);
//                    }
//                }
//                else {
//                    img_floor2.setBackgroundResource(R.drawable.light_floor_black);
//                    seek_floor2.setProgress(0);
//                }



                if (tb_floor_kai_g2.isChecked()){
                    switch (seek_floor2.getProgress()){
                        case 10:
                            String seekRes1="SEEKTWOFLOORONE";
                            lfwrite.write(seekRes1);
                            lfwrite.flush();
                            break;
                        case 30:
                            String seekRes2="SEEKTWOFLOORTWO";
                            lfwrite.write(seekRes2);
                            lfwrite.flush();
                            break;
                        case 50:
                            String seekRes3="SEEKTWOFLOORTHREE";
                            lfwrite.write(seekRes3);
                            lfwrite.flush();
                            break;
                        case 70:
                            String seekRes4="SEEKTWOFLOORFOUR";
                            lfwrite.write(seekRes4);
                            lfwrite.flush();
                            break;
                        case 90:
                            String seekRes5="SEEKTWOFLOORFIVE";
                            lfwrite.write(seekRes5);
                            lfwrite.flush();
                            break;
//                        case 0:
//                            String seekRes0="SEEKTWOFLOORZERO";
//                            lfwrite.write(seekRes0);
//                            lfwrite.flush();
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

        //ToggleButton按钮监听事件
        tb_floor_kai_g1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
             if (b){
                    String tbRes1on="LFLOORONEON";
                    lfwrite.write(tbRes1on);
                    lfwrite.flush();
                }else {
                    String tbRes2off="LFLOORONEOFF";
                    lfwrite.write(tbRes2off);
                    lfwrite.flush();
                }


//                img_floor1.setBackgroundResource(b?R.drawable.light_floor50:R.drawable.light_floor_black);
            }
        });
        tb_floor_kai_g2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (b){
                    String tbRes2on="LFLOORTWOON";
                    lfwrite.write(tbRes2on);
                    lfwrite.flush();
                }else {
                    String tbRes2off="LFLOORTWOOFF";
                    lfwrite.write(tbRes2off);
                    lfwrite.flush();
                }

//                img_floor2.setBackgroundResource(b?R.drawable.light_floor50:R.drawable.light_floor_black);
            }
        });
        tb_floor_kai_g3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    String seekRes3="LFLOORALLON";
                    lfwrite.write(seekRes3);
                    lfwrite.flush();
                }
                else
                {
                    String seekRes3="LFLOORALLOFF";
                    lfwrite.write(seekRes3);
                    lfwrite.flush();

                }
            }
        });


    }
    public View.OnClickListener lightFloorOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bt_floor_ldu:
                    Toast.makeText(getActivity(),"查看亮度",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bt_floor_dinShi:
                    Toast.makeText(getActivity(),"定时",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bt_qc_floor_dinShi:
                    Toast.makeText(getActivity(),"清除定时",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
}
