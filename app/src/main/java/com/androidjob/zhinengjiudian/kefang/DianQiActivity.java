package com.androidjob.zhinengjiudian.kefang;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidjob.zhinengjiudian.MyFragmentPagerAdapter;
import com.androidjob.zhinengjiudian.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class DianQiActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
    private ViewPager gJuViewPager;
    private ViewPagerIndicator gJuIndicator;
    private List<Fragment> gTab = new ArrayList<Fragment>();
    private FragmentPagerAdapter gFragmentPagerAdapter;
    private Button gj_fan_hui;


    private PrintWriter writer;
    private Socket socket = null;
    static String message;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gongjutab);
        connect();

        gj_fan_hui = (Button) findViewById(R.id.gj_fan_hui);
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

        gTab.add(new TVFragment());
        gTab.add(new AirFragment());


        gFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), gTab);
        gJuViewPager = (ViewPager) findViewById(R.id.gViewpager);
        gJuViewPager.setAdapter(gFragmentPagerAdapter);
        gJuViewPager.setOnPageChangeListener(this);
        gJuIndicator = (ViewPagerIndicator) findViewById(R.id.gIndicator);

        gj_fan_hui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }


    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //偏移量 tabWidth*positionOffset+position*tabWidth
        gJuIndicator.scroll(position, positionOffset);
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
                    Toast.makeText(DianQiActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
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
                if (serverMessage.equals("70111") || serverMessage.equals("71111")) {
                    sendToTv();
                } else if (serverMessage.equals("20111") || serverMessage.equals("21111")) {
                    sendToAir();
                }

                super.onProgressUpdate(values);
            }
        };
        mes.execute();
    }

    public void sendToTv() {
        TVFragment tvFragment = new TVFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        tvFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.tvLayout, tvFragment);
        beginTransaction.commit();
    }

    public void sendToAir() {
        AirFragment airFragment = new AirFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        airFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.airlayout, airFragment);
        beginTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }
}
