package sg.edu.np.mad.madpractical5;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERNAME = "_username";
    public static final String COLUMN_DESCRIPTION = "_description";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FOLLOWED = "_followed";
    public DatabaseHandler(Context context, String name,
                           SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT," + COLUMN_FOLLOWED + " BOOLEAN" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void initWithUsers() {
        // Initialise Database with 20 Random User Data
        Random rand = new Random();
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < 20; i++) {
            int randNum = rand.nextInt(999999);
            String name = "Name" + randNum;
            String description = "Description" + randNum;
            Boolean followed = randNum % 2 == 0;

            this.addUser(new User(name, description, i, followed));
        }
    }
    public void updateUser(String name, Boolean followed){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FOLLOWED, followed);

        db.update(TABLE_USERS, contentValues, COLUMN_USERNAME + " = ?", new String[]{name});
    }
    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    public ArrayList<User> getUsers() {
        SQLiteDatabase db = this.getWritableDatabase();

        String getQuery = "SELECT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(getQuery, null);
        ArrayList<User> users = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                int followed = cursor.getInt(3);

                users.add(new User(name, description, id, followed > 0));
                cursor.moveToNext();
            }
        }
        return users;
    }
}

