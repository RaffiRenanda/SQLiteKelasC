package com.example.sqllitekelasc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {
    public DBController(Context context) {
        super(context, "ProdiTI", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table teman (id integer primary key, nama text, telepon text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists teman");
        onCreate(db);

    }

    public void tambahData(HashMap<String,String> dataQuery){
        SQLiteDatabase basisdata = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("nama", dataQuery.get("nama"));
        nilai.put("telepon", dataQuery.get("telepon"));

        basisdata.insert("teman", null,nilai);
        basisdata.close();
    }

    public ArrayList<HashMap<String,String>> getAllTeman(){
        ArrayList<HashMap<String,String>> dataTeman;
        dataTeman = new ArrayList<HashMap<String, String>>();
        String query = "select*from teman";
        SQLiteDatabase basisdata = this.getReadableDatabase();

        Cursor cursor = basisdata.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do{
                HashMap<String,String> map = new HashMap<>();
                map.put("id",cursor.getString(0));

                map.put("id", cursor.getString(0));
                map.put("nama", cursor.getString(1));
                map.put("telepon", cursor.getString(2));
                dataTeman.add(map);
            } while (cursor.moveToNext());
        }
        basisdata.close();
        return dataTeman;
    }
}
