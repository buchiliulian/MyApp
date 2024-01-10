package com.example.myapp.dongtai;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublishActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE1="com.kmgplyb.chatapplication.MESSAGE1";
    private EditText etPostText;
    private Button btnAddPhoto;
    private ImageView ivSelectedPhoto;
    private Button btnPost;
    private Button btnReturn;
    private List<String> dynamicList = new ArrayList<>(); // 将dynamicList声明为全局变量
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private List<List_Bean> data;
    private MyDBHelper dbHelper;
    private String disp_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        etPostText = findViewById(R.id.etPostText);
        btnAddPhoto = findViewById(R.id.btnAddPhoto);
        ivSelectedPhoto = findViewById(R.id.ivSelectedPhoto);
        btnPost = findViewById(R.id.btnPost);
        btnReturn = findViewById(R.id.btnReturn);
        dbHelper = new MyDBHelper(this);




        // 点击"发表动态"按钮的事件监听
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 获取动态文本内容
                String postText = etPostText.getText().toString();
                if (postText.isEmpty()) {
                    Toast.makeText(PublishActivity.this, "请输入动态内容", Toast.LENGTH_SHORT).show();
                } else {
                    // 将动态信息保存到数据库
                    dbHelper.insertMemo(postText, disp_path,new Date().toString());
                    Toast.makeText(PublishActivity.this, "动态发表成功", Toast.LENGTH_SHORT).show();
//                    finish();
                    Intent intent = new Intent(PublishActivity.this, DongTai.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 清除MainActivity之上的所有Activity
                    startActivity(intent);
                }
            }
        });

        // 点击"返回"按钮的事件监听
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PublishActivity.this, DongTai.class);
                startActivity(intent);
            }
        });


        // 点击"添加图片"按钮的事件监听
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开图库选择图片
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // 获取选中的图片URI
            Uri selectedImageUri = data.getData();
            // 将选中的图片显示在ImageView中
            ivSelectedPhoto.setImageURI(selectedImageUri);
            ivSelectedPhoto.setVisibility(View.VISIBLE);
            disp_path = getRealPathFromURI(selectedImageUri); // 获取图片在设备中的真实路径
        }
    }

    // 根据图片URI获取真实路径
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(columnIndex);
        cursor.close();
        return path;
    }
}