package com.androidjob.zhinengjiudian.kefang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Administrator on 2016/10/3 0003.
 */
public class SocketOpen {
    private static Socket s = null;
    private static PrintWriter os = null;
    private static InputStream rd = null;
    private static boolean isConnected;

    private SocketOpen() {
    }

    public static synchronized Socket getSocket() {
        if (s == null || s.isClosed()) {
            try {
                s = new Socket("192.168.43.43", 5500);
                isConnected = s.isConnected();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return s;
    }

    public static synchronized PrintWriter getSocketPrintWriter() {

        if (s != null || isConnected) {
            try {
                os = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return os;
    }


    public static synchronized InputStream getSocketInputStream() {
        if (s != null || isConnected) {
            try {

                rd = s.getInputStream();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return rd;
    }

    public static synchronized void disOpen() {
        if (s != null ) {
            try {
                s.close();
                os.close();
                s.close();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}