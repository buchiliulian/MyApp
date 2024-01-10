package com.example.myapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.chat.chatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalInformation extends AppCompatActivity implements View.OnClickListener {

    private List<Map<String, Object>> data;//一个集合

    //查看头像
    private RelativeLayout picture;
    private ImageView imgview1, imgview2;
    //名称
    private RelativeLayout username;
    private TextView view2;
    //学号
    private RelativeLayout student_id;
    private TextView view3;
    //修改手机号
    private RelativeLayout phone;
    private TextView view4;
    //选择性别
    private RelativeLayout sex;
    private TextView view5;
    //生日
    private RelativeLayout birthday;
    private TextView view6;
    //学院
    private RelativeLayout college;
    private Spinner spinner;
    //状态
    private RelativeLayout holiday;
    private Spinner spinner2;
    //爱好
    private RelativeLayout hobby;
    private TextView view9;
    private Button btn_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information);

        initView();
    }

    public void initView() {
        //找到对应控件
        btn_out = findViewById(R.id.logeout);
        picture = findViewById(R.id.picture);
        view6 = findViewById(R.id.view6);
        imgview1 = findViewById(R.id.imageView1);
        imgview2 = findViewById(R.id.ImReturn);

        username = findViewById(R.id.username);
        view2 = findViewById(R.id.view2);

        student_id = findViewById(R.id.student_id);
        view3 = findViewById(R.id.view3);

        phone = findViewById(R.id.phone);
        view4 = findViewById(R.id.view4);

        sex = findViewById(R.id.sex);
        view5 = findViewById(R.id.view5);

        birthday = findViewById(R.id.birthday);
        view6 = findViewById(R.id.view6);

        college = findViewById(R.id.college);
        spinner = findViewById(R.id.spinner_college);

        holiday = findViewById(R.id.holiday);
        spinner2 = findViewById(R.id.spinner_holiday);

        hobby = findViewById(R.id.hobby);
        view9 = findViewById(R.id.view9);
        //设置事件监听
        picture.setOnClickListener(this);
        username.setOnClickListener(this);
        student_id.setOnClickListener(this);
        phone.setOnClickListener(this);
        sex.setOnClickListener(this);
        birthday.setOnClickListener(this);
        college.setOnClickListener(this);
        holiday.setOnClickListener(this);
        hobby.setOnClickListener(this);
        //学院选择
        String arr[] = new String[]{"安徽", "澳门", "北京", "重庆", "福建", "甘肃", "广东", "广西", "贵州", "海南", "河北", "河南", "黑龙江", "湖北", "湖南", "吉林", "江苏", "江西", "辽宁", "内蒙古", "宁夏", "青海", "山东", "山西", "陕西", "上海", "四川", "台湾", "天津", "西藏", "香港", "新疆", "云南", "浙江"};

        ArrayAdapter a = new ArrayAdapter(this, R.layout.textsize, arr);

        spinner.setAdapter(a);
        //状态选择
        data = new ArrayList<>();

        SimpleAdapter adapter = new SimpleAdapter(this, getDat(), R.layout.item_spinner,
                new String[]{"image", "text"}, new int[]{R.id.img, R.id.tvv});

        spinner2.setAdapter(adapter);
        imgview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 打开图库选择图片
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivity(intent);
            }
        });
        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalInformation.this, MainActivity.class);
                startActivity(intent);
            }
        });
        imgview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalInformation.this, chatActivity.class);
                startActivity(intent);
            }
        });

    }

    private List<Map<String, Object>> getDat() {
        Map<String, Object> map = new HashMap<>();
        map.put("image", R.drawable.dujia);
        map.put("text", "度假中");
        data.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("image", R.drawable.gongzuo);
        map1.put("text", "工作中");
        data.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("image", R.drawable.moyu);
        map2.put("text", "摸鱼中");
        data.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("image", R.drawable.shuijiao);
        map3.put("text", "睡觉中");
        data.add(map3);
        return data;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.picture) {
            text1();
        } else if (id == R.id.username) {
            text2();
        } else if (id == R.id.student_id) {
            text3();
        } else if (id == R.id.phone) {
            text4();
        } else if (id == R.id.sex) {
            text5();
        } else if (id == R.id.birthday) {
            text6();
        } else if (id == R.id.hobby) {
            text9();
        }
    }

    //查看头像
    public void text1() {
        //获取内容
        View layout = View.inflate(this, R.layout.dialog_picture, null);
        //创建对象
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        //修改标题
        b.setTitle("查看头像");
        //赋值内容设置窗口
        b.setView(layout);
        //修改
        b.setPositiveButton("确定", null);
        b.setNegativeButton("取消", null);
        b.show();
    }

    //修改名称
    public void text2() {
        //获取内容
        View layout = View.inflate(this, R.layout.dialog_username, null);
        //创建对象
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        //修改标题
        b.setTitle("修改昵称");
        //赋值内容设置窗口
        b.setView(layout);
        //修改
        b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText update = layout.findViewById(R.id.view2);

                String newName = update.getText().toString().trim();
                //设置数据
                view2.setText(newName);
            }
        });
        b.setNegativeButton("取消", null);

        b.show();
    }

    //修改学号
    public void text3() {
        //获取内容
        View layout = View.inflate(this, R.layout.dialog_student_id, null);
        //创建对象
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        //修改标题
        b.setTitle("修改聊天号");
        //赋值内容设置窗口
        b.setView(layout);
        //修改
        b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText update = layout.findViewById(R.id.view3);

                String newId = update.getText().toString().trim();
                //设置数据
                view3.setText(newId);
            }
        });
        b.setNegativeButton("取消", null);

        b.show();
    }

    //修改手机号
    public void text4() {
        //获取内容
        View layout = View.inflate(this, R.layout.dialog_phone, null);
        //创建对象
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        //修改标题
        b.setTitle("修改手机号");
        //赋值内容设置窗口
        b.setView(layout);
        //修改
        b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText update = layout.findViewById(R.id.view4);

                String newPhone = update.getText().toString().trim();
                //设置数据
                view4.setText(newPhone);
            }
        });
        b.setNegativeButton("取消", null);
        b.show();
    }

    //性别选择
    public void text5() {
        String arr[] = {"男", "女"};
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("选择性别");
        b.setSingleChoiceItems(arr, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                view5.setText(arr[which]);
                dialog.dismiss();
            }
        });
        b.show();
    }

    //爱好
    public void text9() {
        ArrayList<Integer> list = new ArrayList();
//        String arr[]={"唱","跳","rap","篮球"};
        String arr[] = {"运动", "睡觉", "躺平", "美食", "游戏", "汽车"};
        boolean fff[] = {false, false, false, false, false, false};
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("标签选择");
        b.setMultiChoiceItems(arr, fff, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    list.add(which);
                } else {
                    list.remove(which);
                }
            }
        });
        b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = "";
                for (int i = 0; i < list.size(); i++) {
                    str += arr[list.get(i)] + " ";
                }
                view9.setText(str);
            }
        });
        b.setNegativeButton("取消", null);
        b.show();
    }

    //生日选择
    public void text6() {
        //获取当前日期
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        int currentDay = c.get(Calendar.DAY_OF_MONTH);

        List<String> yearList = new ArrayList<>();
        List<String> monthList = new ArrayList<>();
        List<String> dayList = new ArrayList<>();

        // 构建年份列表
        for (int i = 1900; i <= currentYear; i++) {
            yearList.add(String.valueOf(i));
        }

        // 构建月份列表
        for (int i = 1; i <= 12; i++) {
            monthList.add(String.valueOf(i));
        }

        // 构建日期列表
        for (int i = 1; i <= 31; i++) {
            dayList.add(String.valueOf(i));
        }

        // 将年份、月份、日期列表转换成数组
        String[] years = yearList.toArray(new String[0]);
        String[] months = monthList.toArray(new String[0]);
        String[] days = dayList.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_birthday, null);
        Spinner yearSpinner = view.findViewById(R.id.year_spinner);
        Spinner monthSpinner = view.findViewById(R.id.month_spinner);
        Spinner daySpinner = view.findViewById(R.id.day_spinner);

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        // 设置初始选中的日期
        yearSpinner.setSelection(currentYear - 1900);
        monthSpinner.setSelection(currentMonth);
        daySpinner.setSelection(currentDay - 1);

        builder.setTitle("选择生日");
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String year = yearSpinner.getSelectedItem().toString();
                String month = monthSpinner.getSelectedItem().toString();
                String day = daySpinner.getSelectedItem().toString();
                view6.setTextSize(25);
                view6.setText(year + "." + month + "." + day);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

}
