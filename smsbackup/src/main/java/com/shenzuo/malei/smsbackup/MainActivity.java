package com.shenzuo.malei.smsbackup;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private Button bt_Button;
    private final static int REQUEST_CODE_ASK_READ_SMS=0x123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        bt_Button= (Button) findViewById(R.id.bt_backup);
        bt_Button.setOnClickListener(this);

        if(Build.VERSION.SDK_INT >= 23){//判断当前系统的版本
            int checkREAD_SMSPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);//获取系统是否被授予该种权限
            if(checkREAD_SMSPermission != PackageManager.PERMISSION_GRANTED){//如果没有被授予
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},REQUEST_CODE_ASK_READ_SMS);
                return;//请求获取该种权限
            }else{
                //backup();//定义好的获取权限后的处理的事件
            }
        }else {
            backup();
        }
      /*  @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            switch (requestCode){
                case REQUEST_CODE_ASK_READ_SMS:
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        backup();
                    }else{
                        finish();
                    }
                    break;
                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_backup:
                backup();
                break;
            default:
                break;
        }

    }

    private void backup() {
        try {
            System.out.println("进入backup");
        //1获取XmlSerializer实例
        XmlSerializer xmlSerializer = Xml.newSerializer();
        //2.设置序列化参数
        //File file = new File(Environment.getExternalStorageDirectory().getPath(), "smsbackup.xml");
         File file = new File("data/data/com.shenzuo.malei.smsbackup","smsbackup.xml");
        FileOutputStream fos = new FileOutputStream(file);
        //3.设置xml编码方式
        xmlSerializer.setOutput(fos,"utf-8");
            //4.写xml开始头
            xmlSerializer.startDocument("utf-8",true);
            //5.写xml根节点
            xmlSerializer.startTag(null,"smss");
            //[6]由于短信的数据库已经通过内容提供者暴露出来 所以我们直接通过内容解析者查询
            Uri uri=Uri.parse("content://sms/");
            Cursor cursor = getContentResolver().query(uri, new String[]{"address", "date", "body"}, null, null, null);
            System.out.println("------------------进去了-");
            if(cursor.equals(null))System.out.println("------------------null-");
            //7.遍历返回结果
            while (cursor.moveToNext())
            {
                String address = cursor.getString(0);
                String date = cursor.getString(1);
                String body = cursor.getString(2);

                //8.写sms节点
                xmlSerializer.startTag(null,"sms");
                //9.写address节点
                xmlSerializer.startTag(null,"address");
                xmlSerializer.text(address);
                xmlSerializer.endTag(null,"address");
                //9.写data节点
                xmlSerializer.startTag(null,"date");
                xmlSerializer.text(date);
                xmlSerializer.endTag(null,"date");
                //9.写address节点
                xmlSerializer.startTag(null,"body");
                xmlSerializer.text(body);
                xmlSerializer.endTag(null,"body");
                xmlSerializer.endTag(null,"sms");
            }
            xmlSerializer.endTag(null,"smss");
            xmlSerializer.endDocument();
            fos.close();
            Toast.makeText(mContext, "一键备份成功", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
