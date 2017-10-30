package database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import entities.Users;

/**
 * Created by harshitshah on 18/03/16.
 */
public class PredictMarketdb {

    private static final String DB_NAME = "PredictMarket.sqlite";
    private static final int DB_VERSION = 2;

    private static final String TABLE_USERS = "users";
    // private static final String TABLE_AUTHORS = "authors";
    private static final String COL_ID = "id";

    /*private static final String COL_TITLE = "title";
    private static final String COL_PRICE = "price";
    private static final String COL_PAGES = "pages";*/

    private static final String COL_NAME = "name";
    private static final String COL_PASSWORD = "password";
    private static final String COL_EMAIL = "email";

    private static final String CREATE_TABLE_USERS = "create table " + TABLE_USERS +
            "(" + COL_ID + " integer primary key autoincrement," +
            COL_NAME + " text not null," +
            COL_PASSWORD + " text not null," +
            COL_EMAIL + " text not null);";

    private SQLiteDatabase db;

    public PredictMarketdb(Context context){

        PredictMarketDbHelper helper=new PredictMarketDbHelper(context);
        db=helper.getWritableDatabase();

    }
    public ArrayList<Users> getUsers(){
        ArrayList<Users> users = new ArrayList<Users>();
        String orderBy = COL_NAME + " ASC";
        Cursor cursor=db.query(TABLE_USERS, null, null, null, null, null, orderBy);

        //set the column indexes
        int idColIndex = cursor.getColumnIndex(COL_ID);
        int nameColIndex = cursor.getColumnIndex(COL_NAME);
        int passwordColIndex = cursor.getColumnIndex(COL_PASSWORD);
        int emailColIndex = cursor.getColumnIndex(COL_EMAIL);

        while(cursor.moveToNext()){
            users.add(new Users( cursor.getString(nameColIndex), cursor.getString(passwordColIndex),
                    cursor.getString(emailColIndex),cursor.getLong(idColIndex)));

        }

        //close the cursor
        cursor.close();

        return users;

    }

    class PredictMarketDbHelper extends SQLiteOpenHelper {

        public PredictMarketDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_USERS);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
