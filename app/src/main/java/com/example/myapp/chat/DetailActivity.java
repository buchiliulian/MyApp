package com.example.myapp.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.example.myapp.processPwd;

public class DetailActivity extends AppCompatActivity {
    private Button btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btn_return = findViewById(R.id.btn_return);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, chatActivity.class);
                startActivity(intent);
            }
        });

        TextView textDetailTitle = findViewById(R.id.textDetailName);
        TextView textDetailMessage = findViewById(R.id.textDetailIntroduce);

        Intent intent = getIntent();
        if (intent != null) {
            Chat chat = (Chat) intent.getSerializableExtra("chat");
            if (chat != null) {
                textDetailTitle.setText(chat.getName());
                textDetailMessage.setText(chat.getIntro());
            }
        }
    }
}