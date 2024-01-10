package com.example.myapp.dongtai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapp.PersonalInformation;
import com.example.myapp.R;
import com.example.myapp.chat.chatActivity;

import java.util.ArrayList;
import java.util.List;

public class DongTai extends AppCompatActivity {

    private int num=1;
    private TextView tvFriendName;
    private RecyclerView recyclerView;
    private com.example.myapp.dongtai.List_Bean List_Bean;
    private Button btnAddPost,btnhome,btnchat;
    private List<com.example.myapp.dongtai.List_Bean> data = new ArrayList<>();
    private List_Adapter postAdapter = new List_Adapter(data, this);
    private MyDBHelper DThelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_tai);

        btnchat = findViewById(R.id.btn_tochat);
        btnhome = findViewById(R.id.btn_tohome1);
        tvFriendName = findViewById(R.id.tvFriendName);
        recyclerView = findViewById(R.id.listView);
        btnAddPost = findViewById(R.id.btnAddPost);
        DThelper = new MyDBHelper(DongTai.this);
        db = DThelper.getWritableDatabase();
        recyDisplay();
        // 设置好友昵称
        tvFriendName.setText("好友动态");
        PublishActivity publishActivity = new PublishActivity();


        // 点击列表项时的事件监听

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_UP) {
                    View childView = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(childView);
                    if (position != RecyclerView.NO_POSITION) {
                        // 处理点击动态的逻辑
                        List_Bean selectedBean = data.get(position);

                        Intent intent = new Intent(DongTai.this, DynamicDetailActivity.class);
                        intent.putExtra("selectedDynamic", selectedBean.getContent()); // 传递选中的动态内容
                        startActivity(intent);
                        return true; // 返回true表示事件被拦截
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        // 点击发布动态按钮的事件监听
        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到发布页面
                Intent intent = new Intent(DongTai.this, PublishActivity.class);
                startActivity(intent);
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到发布页面
                Intent intent = new Intent(DongTai.this, PersonalInformation.class);
                startActivity(intent);
            }
        });

        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到发布页面
                Intent intent = new Intent(DongTai.this, chatActivity.class);
                startActivity(intent);
            }
        });
    }
    private void recyDisplay(){
        List<List_Bean> arr = new ArrayList();
        Cursor cursor = db.rawQuery("select * from PostDetail", null);
        while (cursor.moveToNext()){
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String imgpath = cursor.getString(cursor.getColumnIndex("imagePath"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            List_Bean listBean = new List_Bean(content, imgpath, time);
            data.add(listBean);
        }
        cursor.close();
        StaggeredGridLayoutManager st = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(st);
        recyclerView.setAdapter(postAdapter);
    }

}