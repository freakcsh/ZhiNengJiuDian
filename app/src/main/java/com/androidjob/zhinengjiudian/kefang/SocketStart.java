package com.androidjob.zhinengjiudian.kefang;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.androidjob.zhinengjiudian.me.TuiJian_Activity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/10/4 0004.
 */
public class SocketStart {
    public static BufferedWriter write;
    public static InputStream read;
    public static String res;
    public static Socket socket;

    private SocketStart() {
    }



//    public void connect() {
//
//
//        final AsyncTask<Void, String, Void> aaa = new AsyncTask<Void, String, Void>() {
//
//            @Override
//            protected Void doInBackground(Void... arg0) {
//                try {
//                    socket = SocketOpen.getSocket();
//                    write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
////                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                    read = socket.getInputStream();
//
//                    publishProgress("@success");
//                } catch (UnknownHostException e1) {
//
//                } catch (IOException e1) {
//
//                }
//
//                return null;
//            }
//
//            @Override
//            protected void onProgressUpdate(String... values) {
//                if (values[0].equals("@success")) {
//                    Toast.makeText(TuiJian_Activity.this, "连接成功", Toast.LENGTH_SHORT).show();
//
//                }
//                jj.append(values[0] + "\n");
//                super.onProgressUpdate(values);
//            }
//        };
//        read.execute();
//
//    }
//
//
//    //接收服务器主动发来的消息
//    public void getCTMes() {
//        AsyncTask<String, String, String> mes = new AsyncTask<String, String, String>() {
//            @Override
//            protected String doInBackground(String... params) {
//                try {
//
//
//                    byte buffer[] = new byte[512];
//                    int temp = 0;
//                    String res = null;
//                    //从inputstream中读取客户端所发送的数据
//                    System.out.println("接收到服务器的信息是：");
//
//                    while ((temp = inputStream.read(buffer, 0, 5)) != -1) {
//
//                        line = new String(buffer, 0, temp);
//                        System.out.println(line);
//                        publishProgress(line);
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//            }
//
//            @Override
//            protected void onProgressUpdate(String... values) {
//
//
//                super.onProgressUpdate(values);
//            }
//        };
//        mes.execute();
//    }

}
