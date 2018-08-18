package com.example.shradha.app_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Databasehelper extends SQLiteOpenHelper{
public static final String db_name="students.db";
    public static final String table_name="students";
    public static final String col1="id";
    public static final String col2="name";
    public static final String col3="surname";
    public static final String col4="marks";

    public Databasehelper(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table "+table_name+"(id integer PRIMARY KEY Autoincrement,name Text,surname Text,marks Text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
db.execSQL("Drop table if Exists "+table_name);
    }

    public boolean insertBata(String name,String surname,String marks){
        try {


            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col2, name);
            cv.put(col3, surname);
            cv.put(col4, marks);
            long result = db.insert(table_name, null, cv);
            if (result == -1) {
                return false;
            }
        }catch (SQLException e){
            Log.d("exception",e.toString());
          //  Toast.makeText(getApplicationContext(),e,Toast.LENGTH_LONG).show();
        }
        return true;
    }

    public ArrayList<String>  viewd(){
        ArrayList<String> a=new ArrayList<String>();
        try {


            SQLiteDatabase db=this.getReadableDatabase();
            Cursor resultSet = db.rawQuery("Select * from " + table_name, null);
                resultSet.moveToFirst();

              //  a.add(resultSet.getString(0));
                a.add(resultSet.getString(1));
                a.add(resultSet.getString(2));
                return a;

        }catch (SQLException e){
            a.add(e.toString());
            return a;
        }
    }
}

