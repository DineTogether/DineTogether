package com.example.mtrsliit.dine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import android.database.ContentObservable;


import java.util.ArrayList;

class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "user_database1";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";


    public static final String FOODREVIEW="feedback_table";
    public static final String COLUMN_FB1="ID";
    public static final String FOODNAME="foodname";
    public static final String DESCRIPTION="description";
    public static final String RATE="rate";


    /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT, "+ KEY_EMAIL + " TEXT,"+ KEY_PHONE +" TEXT,"+KEY_PASSWORD + " TEXT  );";





    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_USERS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);



        db.execSQL("create table feedback_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, foodname TEXT,description TEXT,rate TEXT )");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");

        db.execSQL("DROP TABLE IF EXISTS "+ FOODREVIEW);

        //db.execSQL("DROP TABLE IF EXISTS FOODREVIEW");
        onCreate(db);
    }

    public long addUserDetail(String name, String email,String phone,String pwd,String cpwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating contenvt values
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE, phone);
        values.put(KEY_PASSWORD, pwd);

        // insert row in students table
        long insert = db.insert(TABLE_USER, null, values);

        return insert;
    }

    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> userModelArrayList = new ArrayList<UserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                userModel.setName(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                userModel.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                userModel.setPhone(c.getString(c.getColumnIndex(KEY_PHONE)));
                userModel.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));
                // adding to Students list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public int updateUser(int id, String name,String email,String phone,String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE, phone);
        values.put(KEY_PASSWORD, pwd);
        // update row in students table base on students.is value
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    //public long addUserDetail(String s, String s1, String s2, String s3, String s4) {
    //}





//(ID INTEGER PRIMARY KEY AUTOINCREMENT, foodname TEXT,description TEXT,rate TEXT )");

    public boolean insertData1(String foodname,String description,String rate ){


        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("FOODNAME",foodname);
        contentValues.put("DESCRIPTION",description);
        contentValues.put("RATE",rate);


        long result=db.insert(FOODREVIEW,null,contentValues);

        if(result==-1) {
            return false;
        }
        else {
            return true;
        }

        }


    public Cursor getAllReview(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + FOODREVIEW, null);
        return res;
    }


    public boolean updateReview(String id, String foodname, String description, String rate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FB1, id);
        contentValues.put(FOODNAME, foodname);
        contentValues.put(DESCRIPTION, description);
        contentValues.put(RATE, rate);
        db.update(FOODREVIEW, contentValues, "ID=?", new String[] {id});
        return true;

    }
    public Integer deleteReview(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(FOODREVIEW, "ID = ?", new String[] {id});


    }





















}
