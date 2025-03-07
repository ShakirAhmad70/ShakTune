package com.shakspotify.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class UserDetailsDB extends SQLiteOpenHelper {
    private static final String DETAILS_DB = "user_details_db";
    private static final int version = 1;
    private static final String USER_DETAILS_TABLE = "user_details_table";
    private static final String USER_FOLLOWING_LIST_TABLE = "user_following_list_table";
    private static final String USER_FOLLOWERS_LIST_TABLE = "user_followers_list_table";
    private static final String USER_DOB_TABLE = "user_dob_table";
    private final Context context;

    public UserDetailsDB(Context context) {
        super(context, DETAILS_DB, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + USER_DETAILS_TABLE + " (" +
                        "userName TEXT(20) PRIMARY KEY NOT NULL, " +
                        "phoneNo TEXT NOT NULL UNIQUE, " +
                        "password TEXT NOT NULL, " +
                        "email TEXT UNIQUE DEFAULT NULL, " +
                        "profilePic BLOB DEFAULT NULL, " +
                        "noOfFollowings INTEGER DEFAULT 0, " +
                        "noOfFollowers INTEGER DEFAULT 0, " +
                        "followingList TEXT DEFAULT NULL, " +
                        "followerList TEXT DEFAULT NULL" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + USER_FOLLOWING_LIST_TABLE + " (" +
                        "userName TEXT NOT NULL," +
                        "followingUserName TEXT NOT NULL,"+
                        "PRIMARY KEY (userName, followingUserName)," +
                        "FOREIGN KEY (userName) REFERENCES user_details_table(userName)" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + USER_FOLLOWERS_LIST_TABLE + " (" +
                        "userName TEXT NOT NULL," +
                        "followerUserName TEXT NOT NULL,"+
                        "PRIMARY KEY (userName, followerUserName)," +
                        "FOREIGN KEY (userName) REFERENCES user_details_table(userName)" +
                        ")"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + USER_DOB_TABLE + " (" +
                        "userName TEXT NOT NULL," +
                        "year INTEGER NOT NULL," +
                        "month INTEGER NOT NULL," +
                        "day INTEGER NOT NULL," +
                        "PRIMARY KEY (userName, year, month, day)," +
                        "FOREIGN KEY (userName) REFERENCES user_details_table(userName)" +
                        ")"
        );

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_DETAILS_TABLE);
        onCreate(db);
    }

    public void insertUserData(String userName, String phoneNo, String password, String email, byte[] profilePic) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("userName", userName);
            values.put("phoneNo", phoneNo);
            values.put("password", password);
            values.put("email", email);
            values.put("profilePic", profilePic);
            values.put("followingList", listToJson(null));
            values.put("followerList", listToJson(null));
            db.insert(USER_DETAILS_TABLE, null, values);
            db.close();
        } catch (Exception e) {
            Log.e("DB_ERROR", "Error inserting user data", e);
            Toast.makeText(context, "Error inserting user data", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateUserData(String userName, String phoneNo, String password, String email, byte[] profilePic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phoneNo", phoneNo);
        values.put("password", password);
        values.put("email", email);
        values.put("profilePic", profilePic);
        db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
    }

    public boolean updateUserName(String userName, String newUserName) {
        // If the new username is the same as the current one, nothing to update.
        if (userName.equals(newUserName)) {
            return true;
        }
        // Check if new username already exists.
        if (isUserNameExists(newUserName)) {
            Log.e("DB_ERROR", "Username " + newUserName + " already exists.");
            Toast.makeText(context, "Username " + newUserName + " already exists.", Toast.LENGTH_SHORT).show();
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName", newUserName);
        int rows = db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
        return rows > 0;
    }

    public void updatePhoneNo(String userName, String newPhoneNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phoneNo", newPhoneNo);
        db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
    }

    public void updateEmail(String userName, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", newEmail);
        db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
    }

    public void updatePassword(String userName, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", newPassword);
        db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
    }

    public void updateProfilePic(String userName, byte[] profilePic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("profilePic", profilePic);
        db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
    }

    public void updateFollowingList(String userName, List<String> followingList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("followingList", listToJson(followingList));
        db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
    }

    public void updateFollowerList(String userName, List<String> followerList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("followerList", listToJson(followerList));
        db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
    }

    public void insertDOB(int day, int month, int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("year", year);
        values.put("month", month);
        values.put("day", day);
        db.insert(USER_DOB_TABLE, null, values);
        db.close();
    }

    public void updateDOB(String userName, int day, int month, int year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("year", year);
        values.put("month", month);
        values.put("day", day);
        db.update(USER_DOB_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
    }

    public void deleteDOB(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_DOB_TABLE, "userName = ?", new String[]{userName});
        db.close();
    }

    public Cursor getDOB(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_DOB_TABLE + " WHERE userName = ?", new String[]{userName});
    }

    public void deleteUserData(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_DETAILS_TABLE, "userName = ?", new String[]{userName});
        db.close();
    }

    public Cursor getUserData(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_DETAILS_TABLE + " WHERE userName = ?", new String[]{userName});
    }

    public String getPassword(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT password FROM " + USER_DETAILS_TABLE + " WHERE userName = ?", new String[]{userName});
        String password = null;
        if (cursor.moveToFirst()) {
            password = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return password;
    }

    public String getEmail(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email FROM " + USER_DETAILS_TABLE + " WHERE userName = ?", new String[]{userName});
        String email = null;
        if (cursor.moveToFirst()) {
            email = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return email;
    }

    public String getPhoneNo(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT phoneNo FROM " + USER_DETAILS_TABLE + " WHERE userName = ?", new String[]{userName});
        String phoneNo = null;
        if (cursor.moveToFirst()) {
            phoneNo = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return phoneNo;
    }

    public byte[] getProfilePic(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT profilePic FROM " + USER_DETAILS_TABLE + " WHERE userName = ?", new String[]{userName});
        byte[] profilePic = null;
        if (cursor.moveToFirst()) {
            profilePic = cursor.getBlob(0);
        }
        cursor.close();
        db.close();
        return profilePic;
    }

    public int getNoOfFollowings(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT noOfFollowings FROM " + USER_DETAILS_TABLE + " WHERE userName = ?", new String[]{userName});
        int noOfFollowings = 0;
        if (cursor.moveToFirst()) {
            noOfFollowings = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return noOfFollowings;
    }

    public int getNoOfFollowers(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT noOfFollowers FROM " + USER_DETAILS_TABLE + " WHERE userName = ?", new String[]{userName});
        int noOfFollowers = 0;
        if (cursor.moveToFirst()) {
            noOfFollowers = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return noOfFollowers;
    }

    public List<String> getFollowingList(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT followingList FROM " + USER_DETAILS_TABLE + " WHERE userName = ?", new String[]{userName});
        List<String> followingList = null;
        if (cursor.moveToFirst()) {
            followingList = jsonToList(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return followingList;
    }

    public List<String> getFollowerList(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT followerList FROM " + USER_DETAILS_TABLE + " WHERE userName = ?", new String[]{userName});
        List<String> followerList = null;
        if (cursor.moveToFirst()) {
            followerList = jsonToList(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return followerList;
    }

    public boolean incrementNoOfFollowings(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("noOfFollowings", getNoOfFollowings(userName) + 1);
        int rows = db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
        return rows > 0;
    }

    public boolean incrementNoOfFollowers(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("noOfFollowers", getNoOfFollowers(userName) + 1);
        int rows = db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
        return rows > 0;
    }

    public boolean decrementNoOfFollowings(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("noOfFollowings", getNoOfFollowings(userName) - 1);
        int rows = db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
        return rows > 0;
    }

    public boolean decrementNoOfFollowers(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("noOfFollowers", getNoOfFollowers(userName) - 1);
        int rows = db.update(USER_DETAILS_TABLE, values, "userName = ?", new String[]{userName});
        db.close();
        return rows > 0;
    }

    public boolean isUserNameExists(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT 1 FROM " + USER_DETAILS_TABLE + " WHERE userName = ?", new String[]{userName});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Helper method: Convert a List<String> to a JSON string using Gson.
    private String listToJson(List<String> list) {
        if (list == null) {
            return null;
        }
        return new Gson().toJson(list);
    }

    // Optional helper method: Convert a JSON string back to a List<String>.
    public List<String> jsonToList(String json) {
        if (json == null) {
            return null;
        }
        return new Gson().fromJson(json, new TypeToken<List<String>>(){}.getType());
    }
}
