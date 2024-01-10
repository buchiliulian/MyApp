package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class processPwd extends AppCompatActivity {

    private EditText user_name,new_pwd1,new_pwd2,user_phone;
    private Button btn_1,btn_2,btn_3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_pwd);

        user_name = findViewById(R.id.editText_username);
        user_phone = findViewById(R.id.editText_phone);
        new_pwd1 = findViewById(R.id.editText_newdpwd1);
        new_pwd2 = findViewById(R.id.editText_newdpwd2);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.buttonSendVerificationCode);

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = user_phone.getText().toString();
                // 在这里编写发送验证码短信的逻辑，例如调用后端API发送短信
                // 可以使用第三方短信服务商的API或者自己的短信服务

                // 简单示例：显示Toast提示验证码已发送
                Toast.makeText(processPwd.this, "验证码已发送到 " + phoneNumber, Toast.LENGTH_SHORT).show();
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //按钮单击事件
                String strname = user_name.getText().toString();
                String Newpwd1 = new_pwd1.getText().toString();
                String Newpwd2 = new_pwd2.getText().toString();


                if (!Newpwd1.equals(Newpwd2)){
                    Toast toast = Toast.makeText(processPwd.this, "两次密码输入不一致，密码设置不成功！", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else {
                    //实现修改用户密码
                }
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(processPwd.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}