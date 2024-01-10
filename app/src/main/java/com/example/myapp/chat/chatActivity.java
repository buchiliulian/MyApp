package com.example.myapp.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.PersonalInformation;
import com.example.myapp.R;
import com.example.myapp.dongtai.DongTai;

import java.util.ArrayList;
import java.util.List;

public class chatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List< Chat >chatList = new ArrayList< Chat >();
    private Button btn_dongtai, btn_zhuye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        btn_dongtai = findViewById(R.id.btn_activity);
        btn_zhuye = findViewById(R.id.btn_tohome);

        btn_dongtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatActivity.this, DongTai.class);
                startActivity(intent);
            }
        });

        btn_zhuye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatActivity.this, PersonalInformation.class);
                startActivity(intent);
            }
        });

        initView();
        initChats();
        ChatAdapter adapter = new ChatAdapter(chatList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }
    private void initView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }
    private void initChats(){
        for (int i = 0; i < 3; i++){
            Chat chat1 = new Chat("刘jf",R.drawable.avatar,"我想找女朋友");
            chatList.add(chat1);
            Chat chat2 = new Chat("Peace",R.drawable.avatar,"Hello");
            chatList.add(chat2);
            Chat chat3 = new Chat("XiaoDan",R.drawable.avatar,"好久不见");
            chatList.add(chat3);
            Chat chat4 = new Chat("Lucy",R.drawable.avatar,"你好");
            chatList.add(chat4);
            Chat chat5 = new Chat("David",R.drawable.avatar,"Hi");
            chatList.add(chat5);
            Chat chat6 = new Chat("Andy",R.drawable.avatar,"GO");
            chatList.add(chat6);
            Chat chat7 = new Chat("Edward",R.drawable.avatar,"GO");
            chatList.add(chat7);
            Chat chat8 = new Chat("Alan",R.drawable.avatar,"GO");
            chatList.add(chat8);
        }
    }
}