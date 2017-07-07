package com.androidjob.zhinengjiudian.kefang;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.androidjob.zhinengjiudian.MyFragmentPagerAdapter;
import com.androidjob.zhinengjiudian.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2016/8/15.
 */
public class LightActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
    private ViewPager lViewPager;
    private ViewPagerIndicator mIndicator;
    private List<Fragment> lTab = new ArrayList<Fragment>();
    private FragmentPagerAdapter fragmentPagerAdapter;
    private Button light_fan_hui;

    private PrintWriter writer;
    private Socket socket = null;
    static String message;
    private InputStream inputStream;
    LightZouFragment lightZouFragment = new LightZouFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_tab);
        connect();

        light_fan_hui = (Button) findViewById(R.id.light_fan_hui);
       /* lViewList=new ArrayList<View>();
        View lView1=View.inflate(this,R.layout.light_bed,null);
        View lView2=View.inflate(this,R.layout.light_zoulang,null);
        View lView3=View.inflate(this,R.layout.light_floor,null);
        View lView4=View.inflate(this,R.layout.light_top,null);
        View lView5=View.inflate(this,R.layout.light_bi,null);

        lViewList.add(lView1);
        lViewList.add(lView2);
        lViewList.add(lView3);
        lViewList.add(lView4);
        lViewList.add(lView5);*/
if (savedInstanceState==null){
    lTab.add(new LightTopFragment());
    lTab.add(new LightBedFragment());
    lTab.add(lightZouFragment);
    lTab.add(new LightFloorFragment());
    lTab.add(new LightBiFragment());
}


        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), lTab);
        lViewPager = (ViewPager) findViewById(R.id.lViewpager);
        lViewPager.setAdapter(fragmentPagerAdapter);
        lViewPager.setOnPageChangeListener(this);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.indicator);

        light_fan_hui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SocketOpen.disOpen();
                finish();
            }
        });

    }


    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //偏移量 tabWidth*positionOffset+position*tabWidth
        mIndicator.scroll(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void connect() {
        AsyncTask<Void, String, Void> read = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... arg0) {
                socket = SocketOpen.getSocket();
                writer = SocketOpen.getSocketPrintWriter();
                inputStream = SocketOpen.getSocketInputStream();
                receive();
                publishProgress("@success");
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                if (values[0].equals("@success")) {
                    Toast.makeText(LightActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                }
                super.onProgressUpdate(values);
            }
        };
        read.execute();

    }


    //接收服务器主动发来的消息
    public void receive() {
        AsyncTask<String, String, String> mes = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                String res = null;
                try {
                    byte buffer[] = new byte[512];
                    int temp = 0;
                    //从inputstream中读取客户端所发送的数据
//                    System.out.println("接收到服务器的信息是：");
                    while ((temp = inputStream.read(buffer, 0, 10)) != -1) {
                        message = new String(buffer, 0, temp);
                        res = message;
//                        System.out.println(line);
                        publishProgress(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected void onProgressUpdate(String... values) {
                String serverMessage = values[0];
                if (serverMessage.equals("10111") || serverMessage.equals("11111")) {
                    sendToLightTop();
                } else if (serverMessage.equals("20111") || serverMessage.equals("21111")) {
                    sendToLightBed();
                }else if (serverMessage.equals("LZON1")||serverMessage.equals("LZOFF1")){
                    sendToLightZou();
                }else if (serverMessage.equals("")||serverMessage.equals("")){
                    sendToLightFloor();
                }else if (serverMessage.equals("")||serverMessage.equals("")){
                    sendToLightBi();
                }

                super.onProgressUpdate(values);
            }
        };
        mes.execute();
    }

    public void sendToLightTop() {
        LightTopFragment lightTopFragment = new LightTopFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        lightTopFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.ltlayout, lightTopFragment);
        beginTransaction.commit();
    }

    public void sendToLightBed() {
        LightBedFragment lightBedFragment = new LightBedFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        lightBedFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.lbLayout, lightBedFragment);
        beginTransaction.commit();
    }

    public void sendToLightBi() {
        LightBiFragment lightBiFragment = new LightBiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        lightBiFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.lbiLayout, lightBiFragment);
        beginTransaction.commit();
    }

    public void sendToLightFloor() {
        LightFloorFragment lightFloorFragment = new LightFloorFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        lightFloorFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.lfLayout, lightFloorFragment);
        beginTransaction.commit();
    }

    public void sendToLightZou() {
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        lightZouFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();

        beginTransaction.replace(R.id.lZouLayout, lightZouFragment);
        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        beginTransaction.addToBackStack(null);

        beginTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        SocketOpen.disOpen();
        finish();
    }
}
