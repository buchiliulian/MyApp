package com.example.myapp.dongtai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapp.R;


public class DynamicDetailActivity extends AppCompatActivity {

    private TextView tvDynamicContent;
    private ImageView ivDynamicImage;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_detail);


        tvDynamicContent = findViewById(R.id.tvDynamicContent);
        ivDynamicImage = findViewById(R.id.ivDynamicImage);
        btnReturn = findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 返回到MainActivity
                finish();
            }
        });

        Intent intent = getIntent();
        String selectedContent = intent.getStringExtra("selectedDynamic");
        List_Bean selectedBean = new List_Bean(selectedContent, "", ""); // 这里将图片路径和时间设置为空字符串，因为在MainActivity中并没有传递这些信息
        tvDynamicContent.setText(selectedBean.getContent());

        // TODO: 更新ImageView的内容，可以从动态数据中获取图片路径，然后使用Glide等库来加载图片
        String imageUrl = "图片的URL地址";
        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholder) // 设置占位图，可选
                        .error(R.drawable.error)) // 设置加载错误时显示的图片，可选
                .transition(DrawableTransitionOptions.withCrossFade()) // 设置渐变过渡效果，可选
                .into(ivDynamicImage);
    }
}