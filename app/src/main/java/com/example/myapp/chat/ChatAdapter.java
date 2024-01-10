package com.example.myapp.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Chat> mychatList;
    private Context context;

    public ChatAdapter(List<Chat> chatList, Context context) {
        this.mychatList = chatList;
        this.context = context;
    }



    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        Chat chat = mychatList.get(position);
        holder.chatImage.setImageResource(chat.getImageId());
        holder.chatName.setText(chat.getName());
        holder.chatIn.setText(chat.getIntro());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击RecyclerView项的处理逻辑，跳转到详细聊天界面
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("chat", chat);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mychatList.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView chatImage;
        TextView chatName;
        TextView chatIn;
        View chatView;
        public ViewHolder(View view) {
            super(view);
            chatView = view;
            chatImage = (ImageView) view.findViewById(R.id.chatImg);
            chatName = (TextView) view.findViewById(R.id.chatname);
            chatIn = (TextView) view.findViewById(R.id.chatIntroduce);
        }
    }
    public ChatAdapter(List< Chat > chatList){
        mychatList = chatList;
    }
}



