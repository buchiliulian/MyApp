package com.example.myapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    private TextView txt_name, txt_pwd;
    private Button btn_quit;
    private TextView txt_tk, txt_11;
    private DatePicker date_Picker;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //绑定控件
        txt_name = findViewById(R.id.textView_name);
        txt_pwd = findViewById(R.id.textView_pwd);
        btn_quit = findViewById(R.id.button_quit);
        //接收传递过来的值
        String myname = getIntent().getStringExtra("name");
        int mypwd = getIntent().getIntExtra("pwd", 0);

        //把接收的数据显示出来
        txt_name.setText("用户名：" + myname);
        txt_pwd.setText("密码：" + mypwd);

        //
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转回前一页：指定跳转的源位置和目标位置
                Intent intent = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        date_Picker = findViewById(R.id.datepicker_11);
        txt_tk = findViewById(R.id.textView_ticket);
        txt_11 = findViewById(R.id.textView_1);

        // 获取时间 获取日期控件默认的日期
        int ysyear = date_Picker.getYear();
        int ysmonth = date_Picker.getMonth() + 1;
        int ysday = date_Picker.getDayOfMonth();

        // 把获取的时间显示出来
        txt_11.setText(ysyear + "年" + ysmonth + "月" + ysday + "日");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date_Picker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    txt_tk.setText(i + "年" + (i1 + 1) + "月" + i2 + "日" + "\t" + "杭州----宁波" + "\t" + "G108");
                }
            });
        }


    }
}
