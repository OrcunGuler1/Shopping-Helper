package com.example.orcun.shoppinghelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
    private SQLiteDatabase db;
    private DatabaseOpenHelper databaseHelper;

    public Database(Context context){
        databaseHelper = new DatabaseOpenHelper(context,"Item",null,1);
    }

    public Cursor getAllItems(){
        return db.query("items",new String[]{"id","name"},null,null,null,null,"name");
    }
    public Cursor getItem(long id){
        return db.query("items",null,"id="+id,null,null,null,null);
    }
    public void addItem(String name){
        ContentValues newItem = new ContentValues();
        newItem.put("name",name);
        open();
        db.insert("item",null,newItem);
        close();
    }
    public void deleteItem(long id){
        open();
        db.delete("items","id"+id,null);
        close();
    }


    public void open() throws SQLException {
        db = databaseHelper.getWritableDatabase();
    }
    public void close(){
        if (db != null)
            db.close();
    }

    private class DatabaseOpenHelper extends SQLiteOpenHelper {

        public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlItem = "CREATE TABLE items"+
                    "(id integer primary key autoincrement,"+
                    "name TEXT,"+
                    "price integer,"+
                    "fav integer);";
            db.execSQL(sqlItem);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
