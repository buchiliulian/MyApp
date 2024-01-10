package com.example.myapp.dongtai;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class MyDBHelper extends SQLiteOpenHelper {
    private static String DBNAME = "DTdata.db";
    private static int VERSION = 1;

    public MyDBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MEMO_TABLE = "CREATE TABLE " + MemoEntry.TABLE_NAME + " (" +
                MemoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                MemoEntry.COLUMN_CONTENT + " STRING NOT NULL, " +
                MemoEntry.COLUMN_IMAGE_PATH + " STRING DEFAULT '', " + // 将DEFAULT值设置为''
                MemoEntry.COLUMN_TIME + " STRING);";
        db.execSQL(SQL_CREATE_MEMO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 执行数据库升级操作，如果有需要的话
    }

    public void insertMemo(String content, String imagePath, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MemoEntry.COLUMN_CONTENT, content);
        values.put(MemoEntry.COLUMN_IMAGE_PATH, imagePath);
        values.put(MemoEntry.COLUMN_TIME, time);
        db.insert(MemoEntry.TABLE_NAME, null, values);
    }

    public static class MemoEntry implements BaseColumns {
        public static final String TABLE_NAME = "PostDetail";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_IMAGE_PATH = "imagePath";
        public static final String COLUMN_TIME = "time";
    }
}