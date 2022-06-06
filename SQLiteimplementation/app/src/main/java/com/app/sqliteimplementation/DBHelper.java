package com.app.sqliteimplementation;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL("create Table UserDetail(name TEXT primary key, contact TEXT , dob TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int i, int i1) {
        Db.execSQL("drop Table if exists UserDetail");

    }
    /**
     *
     * @return  Insert Value in table
     */
    public Boolean insertuser(String name,String contact , String dob){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("contact",contact);
        cv.put("dob",dob);

        long result = Db.insert("UserDetail",null,cv);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }


    }
    /**
     *
     * @return  Update Values in table
     */
    public Boolean updateUserData(String name,String contact , String dob){
        SQLiteDatabase Db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("contact",contact);
        cv.put("dob",dob);

        Cursor cursor = Db.rawQuery("Select * from UserDetail where name =?", new String[]{name});

        if(cursor.getCount()>1) {
            long result = Db.update("UserDetail", cv, "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }


    }
    /**
     *
     * @return  Delete Value from table
     */
    public Boolean deleteData(String name){
        SQLiteDatabase Db = this.getWritableDatabase();


        Cursor cursor = Db.rawQuery("Select * from UserDetail where name =?", new String[]{name});

        if(cursor.getCount()>1) {
            long result = Db.delete("UserDetail", "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }


    }

    /**
     *
     * @return  Fteching all values From table
     */

    public Cursor getData() {
        SQLiteDatabase Db = this.getWritableDatabase();


        return Db.rawQuery("Select * from UserDetail", null);
    }
}
