package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class QQActivity extends AppCompatActivity {

    private Button btn_qq_cancle;
    ImageButton btn_qq_go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqactivity);

        btn_qq_cancle = findViewById(R.id.btn_cancle);
        btn_qq_go = findViewById(R.id.qq_login);

        btn_qq_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QQActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_qq_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QQActivity.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}