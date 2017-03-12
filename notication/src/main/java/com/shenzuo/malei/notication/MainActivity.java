package com.shenzuo.malei.notication;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Button send = (Button) findViewById(R.id.bt_send);
         Button cancel = (Button) findViewById(R.id.bt_cancel);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        send.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_send:
                send();
                break;
            case R.id.bt_cancel:
                cancel();
                break;
            default:
                break;
        }
    }

    private void cancel() {
        nm.cancel(10);
    }

    private void send() {
        Notification notification = new Notification.Builder(this).setContentTitle("新通知")
                .setContentText("麻磊好啊")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .build();
        nm.notify(10,notification);

    }
}
