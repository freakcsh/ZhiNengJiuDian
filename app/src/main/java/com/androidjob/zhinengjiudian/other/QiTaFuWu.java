package com.androidjob.zhinengjiudian.other;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjob.zhinengjiudian.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by thinkpad on 2016/8/3.
 */
public class QiTaFuWu extends Activity {
    private Button fw_jx, fw_jc, fw_ds, fw_fj;

    Socket socket = null;
    BufferedWriter writer = null;
    BufferedReader reader = null;
    String res=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuwu);
        fw_jx = (Button) findViewById(R.id.fw_jx);
        fw_jc = (Button) findViewById(R.id.fw_jc);
        fw_ds = (Button) findViewById(R.id.fw_ds);
        fw_fj = (Button) findViewById(R.id.fw_fj);


        resMes();



        //发送消息
        fw_jc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jx="fwjx";
                try {
                    writer.write(jx.toString());
                    writer.flush();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        fw_jx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jx="jx";
                try {
                    writer.write(jx.toString());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
    public void resMes(){
        //连接服务器-------------------------------------------------
        AsyncTask<Void, String, Void> read = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    socket = new Socket("172.26.246.1", 2012);
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    publishProgress("@success");
                    getMes();

                } catch (IOException e) {
                    Toast.makeText(QiTaFuWu.this, "无法建立连接", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
              // try {


                    /*String line;
                    while ((line = reader.readLine()) != null) {
                        publishProgress(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                if (values[0].equals("@success")) {
                    Toast.makeText(QiTaFuWu.this, "连接成功!", Toast.LENGTH_SHORT).show();

                }
               //text.append(res+"\n");
                super.onProgressUpdate(values);
            }
        };
        read.execute();
    }



//接收服务器主动发来的消息
public void getMes(){
    AsyncTask<String, String, String> mes=new AsyncTask<String, String, String>() {
        @Override
        protected String doInBackground(String... params) {
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    res=line;
                    Log.d("cai", res);
                    publishProgress(res);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if (values.equals("lbTrue")){
                Toast.makeText(QiTaFuWu.this, "接收成功!", Toast.LENGTH_SHORT).show();
            }

            super.onProgressUpdate(values);
        }
    };
    mes.execute();
}
}




