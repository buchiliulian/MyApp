package com.example.myapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.chat.chatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText edit_name, edit_pwd;
    private Button btn_login, btn_cancle,btn_forgetPwd;
    private TextView txt_display;
    private ImageButton btn_qq,btn_weixin,btn_weibo;
    private User defaultUser;
    private UserService userService;
    private User currentUser;
    CheckBox check_reme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userService = new UserService();
        defaultUser = new User("peace", "123");
        userService.addUser(defaultUser);

        //绑定控件
        edit_name = findViewById(R.id.editText_inputname);
        edit_pwd = findViewById(R.id.editText_inputpwd);
        btn_login = findViewById(R.id.button_login);
        btn_cancle = findViewById(R.id.button_cancle);
        btn_forgetPwd = findViewById(R.id.btn_forgetPwd);
        // SharedPreferencences
        check_reme = findViewById(R.id.checkBox_reme);


        btn_forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, processPwd.class);
                startActivity(intent);
            }
        });

        //Button控件的使用
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //按钮单击事件
                String strname = edit_name.getText().toString();//获取输入的用户名
                String strpwd = edit_pwd.getText().toString();//获取输入的密码

                currentUser =  userService.checkUser(strname, strpwd);
                if (currentUser != null){
                    SharedPreferences.Editor editor = getSharedPreferences("myfile",0).edit();
                    editor.putString("name",strname);
                    editor.putString("pwd",strpwd);
                    editor.putBoolean("st",check_reme.isChecked());
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, chatActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast toast = Toast.makeText(MainActivity.this, "用户或密码输入错误，请重新输入！", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        });
        // 选中”记住密码“，下一次启动自动获取用户名和密码
        String myname = getSharedPreferences("myfile",0).getString("name","");
        String mypwd = getSharedPreferences("myfile",0).getString("pwd","");
        Boolean myst = getSharedPreferences("myfile",0).getBoolean("st",false);
        if (myst == true){
            edit_name.setText(myname);
            edit_pwd.setText(mypwd);
            check_reme.setChecked(true);
        }else {
            edit_name.setText("");
            edit_pwd.setText("");
            check_reme.setChecked(false);
        }


        //"取消"按钮单击事件
//        btn_cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                txt_display.setVisibility(View.GONE);//让文本控件变得不可见
//                edit_name.setText("");
//                edit_pwd.setText("");
//                Toast.makeText(MainActivity.this, "刘俊锋别冲了！", Toast.LENGTH_LONG).show();
//            }
//        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        btn_qq = findViewById(R.id.btn_qq);
        btn_weixin = findViewById(R.id.btn_weixin);
        btn_weibo = findViewById(R.id.btn_weibo);

        btn_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到qq授权界面
                Intent intent = new Intent(MainActivity.this, QQActivity.class);
                startActivity(intent);
            }
        });

        btn_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到微信授权界面
                Intent intent = new Intent(MainActivity.this, WeixinActivity.class);
                startActivity(intent);
            }
        });

        btn_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到微博授权界面
                Intent intent = new Intent(MainActivity.this, WeiBoActivity.class);
                startActivity(intent);
            }
        });





    }
    // 添加菜单项
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.chat_menu,menu);
//        return true;
//    }
    // 给菜单项添加响应事件

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.add:
//                Toast.makeText(MainActivity.this,"lllll",Toast.LENGTH_LONG).show();
//                break;
//            case R.id.addGroup:
//                Toast.makeText(MainActivity.this,"lllll",Toast.LENGTH_LONG).show();
//                break;
//            case R.id.sweep:
//                Toast.makeText(MainActivity.this,"lllll",Toast.LENGTH_LONG).show();
//                finish();
//                break;
//            default:
//        }
//        return true;
//
//    }
}

