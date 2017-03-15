package com.shenzuo.malei.tcpsocket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by MaLei on 2017/3/14 0014.
 * Email:ml1995@mail.ustc.edu.cn
 */

public class TCPServerService extends Service {
    private boolean mIsServiceDestoryed=false;//服务是否销毁
    private String[] mDefinedMessages= new String[]{"你好啊，哈哈！","请问你叫什么名字？","今天天气真不错","我可以和几个人聊天哦！"};


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        new Thread(new TCpServer()).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class TCpServer implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                //监听本地8688端口
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                System.out.println("establish tcp server failed,port:8688");
                e.printStackTrace();
                return;
            }
            while(!mIsServiceDestoryed){
                try {
                    final Socket client = serverSocket.accept();
                    System.out.println("accept");
                    new Thread()
                    {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void responseClient(Socket client)throws IOException {
        //用于接收客户端信息
        BufferedReader in =new BufferedReader(new InputStreamReader(client.getInputStream()));
        //用于向客户端发送信息
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到聊天室");
        while(!mIsServiceDestoryed){
            String str = in.readLine();
            System.out.println("msg from client:"+str);
            if(str==null){
                //客服端断开连接
                break;
            }
            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            out.println(msg);
            System.out.println("send:"+msg);
        }
        System.out.println("client quit.");
        //关闭流
        client.close();
    }
}
