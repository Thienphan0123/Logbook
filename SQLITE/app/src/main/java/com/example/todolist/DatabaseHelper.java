
package com.example.todolist;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public void resetDatabase() {
        getWritableDatabase().execSQL("DELETE FROM " + DATABASE_NAME);
        onCreate(getWritableDatabase());
    }
    private static final String DATABASE_NAME = "todo_list";
    private static final String ID_COLUMN_NAME = "task_id";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String DEADLINE_COLUMN_NAME = "deadline";
    private static final String DURATION_COLUMN_NAME = "duration";
    private static final String DESCRIPTION_COLUMN_NAME = "description";
    private SQLiteDatabase database;

    private static final String DATABASE_CREATE_QUERY = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT, " +
                    "%s DATE, " +
                    "%s INTEGER, " +
                    "%s TEXT)",
            DATABASE_NAME, ID_COLUMN_NAME, NAME_COLUMN_NAME, DEADLINE_COLUMN_NAME, DURATION_COLUMN_NAME, DESCRIPTION_COLUMN_NAME);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        Log.w(this.getClass().getName(), DATABASE_NAME + " database upgrade to version "
                + newVersion + "old data lost");
        onCreate(db);
    }

    public long insertDetails(String name, String deadline, int duration, String description) {
        ContentValues rowValues = new ContentValues();
        rowValues.put(NAME_COLUMN_NAME, name);
        rowValues.put(DEADLINE_COLUMN_NAME, deadline);
        rowValues.put(DURATION_COLUMN_NAME, duration);
        rowValues.put(DESCRIPTION_COLUMN_NAME, description);
        return database.insertOrThrow(DATABASE_NAME, null, rowValues);
    }

    public String getDetails() {
        Cursor results = database.query("todo_list",
                new String[]{"task_id", "name", "deadline", "duration", "description"},
                null, null, null, null, "name");
        String resultText = "";
        results.moveToFirst();
        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);
            String deadline = results.getString(2);
            int duration = results.getInt(3);
            String description = results.getString(4);
            resultText += id + " " + name + " " + deadline + " " + duration + " " + description + "\n";
        } while (results.moveToNext());
        return resultText;
    }
}
