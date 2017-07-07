package com.androidjob.zhinengjiudian.kefang;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.androidjob.zhinengjiudian.R;
import com.androidjob.zhinengjiudian.SocketService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by thinkpad on 2016/8/3.
 */
public class CurtainActivity extends Activity{
    private TextView cur_tv_zt;
    private ImageView img_zt;
    private ToggleButton left_on_off,right_on_off;

    private Button curtain_fan_hui;
    Intent sc;


    private PrintWriter writer;
    private Socket socket = null;
    static String message;
    private InputStream inputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curtain);
        cur_tv_zt=(TextView)findViewById(R.id.cur_tv_zt);
        img_zt=(ImageView)findViewById(R.id.img_zt);
        left_on_off=(ToggleButton)findViewById(R.id.left_on_off);
        right_on_off=(ToggleButton)findViewById(R.id.right_on_off);
        curtain_fan_hui= (Button) findViewById(R.id.curtain_fan_hui);
    connect();

        left_on_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    String left="30111";
                    writer.write(left);
                    writer.flush();
                }else {
                    String right="60111";
                    writer.write(right);
                    writer.flush();
                }

//                if (right_on_off.isChecked()&&left_on_off.isChecked()){
//                    cur_tv_zt.setText("全开");
//                    img_zt.setBackgroundResource(R.drawable.c_all_open);
//                }
//                else if (!right_on_off.isChecked()&&!left_on_off.isChecked()){
//                    cur_tv_zt.setText("关");
//                    img_zt.setBackgroundResource(R.drawable.c_off);
//                }
//                else  if (left_on_off.isChecked()){
//                    cur_tv_zt.setText("左开");
//                    img_zt.setBackgroundResource(R.drawable.c_left_on);
//                }
//                else {
//                    cur_tv_zt.setText("右开");
//                    img_zt.setBackgroundResource(R.drawable.c_right_on);
//                }

            }
        });
        right_on_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (right_on_off.isChecked()&&left_on_off.isChecked()){
                    cur_tv_zt.setText("全开");
                    img_zt.setBackgroundResource(R.drawable.c_all_open);

                }
                else if (!right_on_off.isChecked()&&!left_on_off.isChecked()){
                    cur_tv_zt.setText("关");
                    img_zt.setBackgroundResource(R.drawable.c_off);
                }
                else  if (right_on_off.isChecked()){
                    cur_tv_zt.setText("右开");
                    img_zt.setBackgroundResource(R.drawable.c_right_on);
                }
                else {
                    cur_tv_zt.setText("左开");
                    img_zt.setBackgroundResource(R.drawable.c_left_on);
                }


            }
        });

        curtain_fan_hui.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(CurtainActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
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
                if (serverMessage.equals("30111")) {
                    cur_tv_zt.setText("全开");
                    img_zt.setBackgroundResource(R.drawable.c_all_open);
                }
                else if (serverMessage.equals("60111")){
                    cur_tv_zt.setText("关");
                    img_zt.setBackgroundResource(R.drawable.c_off);
                }
                super.onProgressUpdate(values);
            }
        };
        mes.execute();
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
