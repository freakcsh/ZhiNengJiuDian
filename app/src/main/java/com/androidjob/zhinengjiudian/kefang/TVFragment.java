package com.androidjob.zhinengjiudian.kefang;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.androidjob.zhinengjiudian.R;

import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class TVFragment extends Fragment{
    private ImageButton tv_on,tv_sm;
    private Button pinDao1,pinDao2,pinDao3,pinDao4,pinDao5,pinDao6,pinDao7,pinDao8,pinDao9,pinDao0,tv_hk,tv_sx;
    private Button yl_jia,yl_xia,pd_jia,pd_xia;
    static String tvRes=DianQiActivity.message;
    private PrintWriter writer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View tv=inflater.inflate(R.layout.tv,container,false);
        tv_on=(ImageButton)tv.findViewById(R.id.tv_on);
        tv_sm=(ImageButton)tv.findViewById(R.id.tv_sm);
        pinDao1=(Button)tv.findViewById(R.id.pinDao1);
        pinDao2=(Button)tv.findViewById(R.id.pinDao2);
        pinDao3=(Button)tv.findViewById(R.id.pinDao3);
        pinDao4=(Button)tv.findViewById(R.id.pinDao4);
        pinDao5=(Button)tv.findViewById(R.id.pinDao5);
        pinDao6=(Button)tv.findViewById(R.id.pinDao6);
        pinDao7=(Button)tv.findViewById(R.id.pinDao7);
        pinDao8=(Button)tv.findViewById(R.id.pinDao8);
        pinDao9=(Button)tv.findViewById(R.id.pinDao9);
        pinDao0=(Button)tv.findViewById(R.id.pinDao0);
        tv_hk=(Button)tv.findViewById(R.id.tv_hk);
        tv_sx=(Button)tv.findViewById(R.id.tv_sx);
        yl_jia=(Button)tv.findViewById(R.id.yl_jia);
        yl_xia=(Button)tv.findViewById(R.id.yl_xia);
        pd_jia=(Button)tv.findViewById(R.id.pd_jia);
        pd_xia=(Button)tv.findViewById(R.id.pd_xia);


        if (getArguments() != null) {
            tvRes = getArguments().get("message") + "";
            if (tvRes.equals("70111")) {
              Toast.makeText(getActivity(),"电视开",Toast.LENGTH_LONG).show();
            } else if (tvRes.equals("71111")) {
                Toast.makeText(getActivity(),"电视关",Toast.LENGTH_LONG).show();
            }
        }
        return tv;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        writer=SocketOpen.getSocketPrintWriter();
        //开关按钮
        tv_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tvOn="70111";
                writer.write(tvOn);
                writer.flush();
            }
        });
        //睡眠按钮
        tv_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tvOff="71111";
                writer.write(tvOff);
                writer.flush();
            }
        });
        //音量加
        yl_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"音量加",Toast.LENGTH_SHORT).show();
            }
        });
        //音量减
        yl_xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"音量减",Toast.LENGTH_SHORT).show();
            }
        });
        //频道加
        pd_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道加",Toast.LENGTH_SHORT).show();
            }
        });
        //频道减
        pd_xia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道减",Toast.LENGTH_SHORT).show();
            }
        });
        //频道按钮
        pinDao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道1",Toast.LENGTH_SHORT).show();
            }
        });
        pinDao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道2",Toast.LENGTH_SHORT).show();
            }
        });
        pinDao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道3",Toast.LENGTH_SHORT).show();
            }
        });
        pinDao4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道4",Toast.LENGTH_SHORT).show();
            }
        });
        pinDao5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道5",Toast.LENGTH_SHORT).show();
            }
        });
        pinDao6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道6",Toast.LENGTH_SHORT).show();
            }
        });
        pinDao7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道7",Toast.LENGTH_SHORT).show();
            }
        });
        pinDao8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道8",Toast.LENGTH_SHORT).show();
            }
        });
        pinDao9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道9",Toast.LENGTH_SHORT).show();
            }
        });
        pinDao0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"频道0",Toast.LENGTH_SHORT).show();
            }
        });
        tv_hk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"回看",Toast.LENGTH_SHORT).show();
            }
        });
        tv_sx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"刷新",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
