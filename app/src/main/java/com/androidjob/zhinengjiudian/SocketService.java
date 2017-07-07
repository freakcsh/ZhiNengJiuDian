package com.androidjob.zhinengjiudian;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketService extends Service {


   public static Socket socket = null;
   public static BufferedWriter writer = null;
   public static BufferedReader reader = null;
  public static String res=null;



    public SocketService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);


    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (socket == null||socket.isClosed()) {
            try {
                socket = new Socket("192.168.191.1", 2012);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
