package com.shenzuo.malei.codebymalei;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_remPassword;
    private Button btn_login;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        et_username= (EditText) findViewById(R.id.et_username);
        et_password= (EditText) findViewById(R.id.et_password);
        cb_remPassword= (CheckBox) findViewById(R.id.cb_rem);
        btn_login= (Button) findViewById(R.id.btn_login);
        //设置用户点击事件
        btn_login.setOnClickListener(this);
        //设置回显
        Map<String, String> userInfo_android = UserInfoUtil.getUserIndo_android(mContext);
        if(userInfo_android!=null)
        {
            String username=userInfo_android.get("username");
            String password=userInfo_android.get("password");
            et_password.setText(password);
            et_username.setText(username);
            cb_remPassword.setChecked(true);
            System.out.println("--------------------------------------------------我不爲空");
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            default:
                break;
        }

    }

    private void login() {
        //获取用户名密码
        String username = et_username.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        boolean isRem = cb_remPassword.isChecked();
        //判断用户名密码是否为空
        if(username.equals("")||password.equals("")) {
            Toast.makeText(mContext, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
       //判断是否记住密码
        if(isRem){
            boolean result =UserInfoUtil.savUserInfo_android(mContext,username,password);
            if(result){
                Toast.makeText(mContext, "用户名密码保存成功", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(mContext, "用户名密码保存失败", Toast.LENGTH_SHORT).show();

        }
    }
}
