package com.example.myapp.dongtai;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.R;

import java.util.List;

public class List_Adapter extends RecyclerView.Adapter<List_Adapter.ViewHolder> {

    private List<List_Bean> data;
    private Context context;

    public List_Adapter(List<List_Bean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    // 创建新的视图（由布局管理器调用）
    @NonNull
    @Override
    public List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // 替换视图的内容（由布局管理器调用）
    @Override
    public void onBindViewHolder(@NonNull List_Adapter.ViewHolder holder, final int position) {
        final List_Bean listBean = data.get(position);
        holder.item_content.setText(listBean.getContent());
        holder.item_time.setText(listBean.getTime());
        Glide.with(context).load(listBean.getImgpath()).into(holder.item_img);

        // 设置长按事件监听器
        holder.item_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 移除数据项
                showDeleteConfirmationDialog(position); // 弹出确认删除对话框
                return true;
            }
        });
    }
    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("确认删除");
        builder.setMessage("确定要删除该动态吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeItem(position); // 执行删除操作
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // 移除列表项的方法
    private void removeItem(int position) {
        List_Bean deletedBean = data.get(position);
        data.remove(position);
        notifyItemRemoved(position);
        // 如果需要更新位置索引，可以调用下面的方法
        notifyItemRangeChanged(position, getItemCount());

        // 调用数据库的删除方法
        MyDBHelper dbHelper = new MyDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = MyDBHelper.MemoEntry._ID + "=?";
        String[] selectionArgs = { String.valueOf(position + 1) };  // 使用列表项在数据集中的位置加1作为唯一标识符
        db.delete(MyDBHelper.MemoEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_title, item_content, item_time;
        ImageView item_img;
        LinearLayout item_layout;

        public ViewHolder(@NonNull View view) {
            super(view);
            item_content = view.findViewById(R.id.item_content);
            item_time = view.findViewById(R.id.item_time);
            item_img = view.findViewById(R.id.item_image);
            item_layout = view.findViewById(R.id.item_layout);
        }
    }

}
