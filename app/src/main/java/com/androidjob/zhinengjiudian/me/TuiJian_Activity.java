package com.androidjob.zhinengjiudian.me;

import android.app.Activity;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjob.zhinengjiudian.R;
import com.androidjob.zhinengjiudian.kefang.SocketOpen;
import com.androidjob.zhinengjiudian.kefang.SocketStart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by thinkpad on 2016/8/8.
 */
public class TuiJian_Activity extends Activity {
    private PrintWriter writer;
    private Socket socket = null;
    private String message;
    private InputStream inputStream;

    private Button mine_package_but;
    private TextView jj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wo_tuijian);
        mine_package_but = (Button) findViewById(R.id.mine_package_but);
        jj = (TextView) findViewById(R.id.jj);

        connect();
        mine_package_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    writer.write("10111");
                    writer.flush();


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
                    Toast.makeText(TuiJian_Activity.this, "连接成功", Toast.LENGTH_SHORT).show();
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
                    while ((temp = inputStream.read(buffer, 0, 5)) != -1) {
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
                Toast.makeText(TuiJian_Activity.this, values[0], Toast.LENGTH_SHORT).show();
                jj.append(values[0] + "\n");
                super.onProgressUpdate(values);
            }
        };
        mes.execute();
    }

    @Override
    public void onBackPressed() {
    SocketOpen.disOpen();
        finish();
    }
}
