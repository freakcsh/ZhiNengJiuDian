package com.androidjob.zhinengjiudian.escape;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidjob.zhinengjiudian.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by thinkpad on 2016/8/3.
 */
public class Escape extends Activity{
    private Button bt;
    Socket sockete=null;
    BufferedWriter writer=null;
    BufferedReader reader=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escape);
        bt=(Button)findViewById(R.id.bt);

        //连接服务器-------------------------------------------------
        AsyncTask<Void,String,Void> read=new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    sockete = new Socket("10.101.116.222", 2012);
                    writer=new BufferedWriter(new OutputStreamWriter(sockete.getOutputStream()));
                    reader=new BufferedReader(new InputStreamReader(sockete.getInputStream()));
                    publishProgress("@success");
                } catch (IOException e) {
                    Toast.makeText(Escape.this, "无法建立连接", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                try {
                    String line;
                    while ((line = reader.readLine())!=null){
                        publishProgress(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                if (values[0].equals("@success")) {
                    Toast.makeText(Escape.this, "连接成功!", Toast.LENGTH_SHORT).show();

                }

                super.onProgressUpdate(values);
            }
        };
        read.execute();

        //发送消息
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jx="fw";
                try {
                    writer.write(jx);
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
