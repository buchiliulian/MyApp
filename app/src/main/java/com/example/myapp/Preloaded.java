package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Preloaded extends AppCompatActivity {
    private static final int SPLASH_DELAY = 5000; // 延迟时间（以毫秒为单位）
    private ImageView splashImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloaded);

        splashImageView = findViewById(R.id.loadingImageView);

        // 创建Handler对象
        Handler handler = new Handler();


        // 加载GIF图片
        Glide.with(this).load(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(splashImageView);


        // 延迟跳转到下一页面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 创建下一页面的Intent
                Intent intent = new Intent(Preloaded.this, MainActivity.class);
                startActivity(intent);
                finish(); // 结束当前活动，防止用户返回到该页面
            }
        }, SPLASH_DELAY);



    }
}