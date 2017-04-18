package org.careerop.mydiary;

/**
 * Created by Juyel_Rana on 12/9/2016.
 */
public class Config {
    //Database name
    public static final String DB_NAME = "mydiary";

    //Database version
    public static final int VERSION = 1;

    //Database table name
    public static final String TABLE_NAME = "notepad";

    // all the column name
    public static final String ID = "id";
    public static final String TITLE="title";
    public static final String BODY = "body";
    public static final String DATE = "date";

    public static final String TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT, " + BODY + " TEXT, " + DATE + " TEXT)";
    public static final String SELECT_SQL = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + ID + " DESC";
}
