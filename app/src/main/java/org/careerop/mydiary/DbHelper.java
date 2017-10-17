package org.careerop.mydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, Config.DB_NAME, null, Config.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create table
        db.execSQL(Config.TABLE_SQL);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        

    }

    public long insertNote(NotePad notePad) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        //Store student data to database
        cv.put(Config.TITLE, notePad.getTitle());
        cv.put(Config.BODY, notePad.getBody());
        cv.put(Config.DATE, notePad.getDate());

        long inserted = database.insert(Config.TABLE_NAME, "", cv);
        database.close();
        return inserted;
    }

    public Cursor getAllNotes() {

        SQLiteDatabase database = this.getWritableDatabase();
        //Get all notes data
        Cursor result = database.rawQuery(Config.SELECT_SQL, null);

        return result;
    }


    public boolean updateNote(String id, String title, String body, String date) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(Config.TITLE, title);
        cv.put(Config.BODY, body);
        cv.put(Config.DATE, date);

        database.update(Config.TABLE_NAME, cv, Config.ID + "=?", new String[]{id});

        return true;

    }


    public boolean deleteNote(String id) {

        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(Config.TABLE_NAME, Config.ID + "=?", new String[]{id});

        return true;
    }


}
