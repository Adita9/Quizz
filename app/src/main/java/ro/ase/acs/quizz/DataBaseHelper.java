package ro.ase.acs.quizz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import ro.ase.acs.quizz.activity.Navigation.User;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "DATABASE";

    private static String TABLE_NAME = "Username";
    private static String USERSNAME = "Username";
    private static String EMAIL = "Email";
    private static String POINTS = "Points";
    private static String ADDRESS = "Address";

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(" + EMAIL + " TEXT PRIMARY KEY, " + USERSNAME + " TEXT, " +
                POINTS + "TEXT," + ADDRESS
                + "TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERSNAME, user.getUsername());
        contentValues.put(EMAIL, user.getEmail());
        contentValues.put(POINTS, user.getPoints());
        contentValues.put(ADDRESS, "");

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }


}
