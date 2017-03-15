package com.shenzuo.malei.tcpsocket;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity  implements View.OnClickListener{

    private static final int MSEEAGE_RECEIVE_NEW_MSG=1;
    private static final int MSEEAGE_SOCKET_CONNECTED=2;

    private Button mSendButton;
    private TextView mMessageTextView;
    private EditText mMessageEditText;

    private PrintWriter mPrintWriter;
    private Socket mClientSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessageTextView= (TextView) findViewById(R.id.tv_content);
        mMessageEditText= (EditText) findViewById(R.id.et_content);
        mSendButton= (Button) findViewById(R.id.bt_send) ;

        mSendButton.setOnClickListener(this);

        Intent service = new Intent(this, TCPServerService.class);
        startService(service);
        new Thread(){
            @Override
            public void run() {
                connectTCPServer();
            }
        }.start();
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MSEEAGE_RECEIVE_NEW_MSG:{
                    mMessageTextView.setText(mMessageTextView.getText()+(String)msg.obj);
                    break;
                }
                case MSEEAGE_SOCKET_CONNECTED:{
                    mSendButton.setEnabled(true);
                    break;
                }
                default:
                    break;

            }
        }
    };

    private void connectTCPServer() {
        Socket socket = null;
        while(socket==null){
            try {
                socket= new Socket("localhost",8688);
                mClientSocket=socket;
                mPrintWriter= new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                mHandler.sendEmptyMessage(MSEEAGE_SOCKET_CONNECTED);
                System.out.println("connect server success");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                System.out.println("connect tcp server failed,retry....");
            }
        }
        //接收服务器端消息
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(!MainActivity.this.isFinishing()){
                String msg = br.readLine();
                System.out.println("receive:"+msg);
                if(msg!=null){
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showedMsg = "server"+time+":"+msg+"\n";
                    mHandler.obtainMessage(MSEEAGE_RECEIVE_NEW_MSG,showedMsg).sendToTarget();
                }
            }
            System.out.println("quit...");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatDateTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }

    @Override
    public void onClick(View v) {
        if(v==mSendButton)
        {
            final String msg = mMessageEditText.getText().toString().trim();
            if(!TextUtils.isEmpty(msg) && mPrintWriter !=null){
                mPrintWriter.println(msg);
                mMessageEditText.setText("");
                String time = formatDateTime(System.currentTimeMillis());
                final String showedMsg = "self"+time+";"+msg+"\n";
                mMessageTextView.setText(mMessageTextView.getText()+showedMsg);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(mClientSocket!=null){
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}
