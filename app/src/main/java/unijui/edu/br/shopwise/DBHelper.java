package unijui.edu.br.shopwise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;

public class DBHelper {

    public final class FeedReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedReaderContract() {}

        /* Inner class that defines the table contents */
        public class ListTable implements BaseColumns {
            public static final String TABLE_NAME = "list";
            public static final String COLUMN_NAME = "nome";
        }

        public class ItemTable implements BaseColumns {
            public static final String TABLE_NAME = "item";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_QUANTITY = "quantity";
            public static final String COLUMN_LIST_ID = "list_id";
        }
    }

    private static final String SQL_CREATE_LIST_TABLE =
            "CREATE TABLE " + FeedReaderContract.ListTable.TABLE_NAME + " (" +
                    FeedReaderContract.ListTable._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.ListTable.COLUMN_NAME + " TEXT)";

    private static final String SQL_CREATE_ITEM_TABLE =
            "CREATE TABLE " + FeedReaderContract.ItemTable.TABLE_NAME + " (" +
                    FeedReaderContract.ItemTable._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.ItemTable.COLUMN_NAME + " TEXT," +
                    FeedReaderContract.ItemTable.COLUMN_QUANTITY + " INTEGER," +
                    FeedReaderContract.ItemTable.COLUMN_LIST_ID + " INTEGER," +
                    "FOREIGN KEY ("+FeedReaderContract.ItemTable.COLUMN_LIST_ID+") REFERENCES "+FeedReaderContract.ListTable.TABLE_NAME+" ("+FeedReaderContract.ListTable._ID+"));";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedReaderContract.ListTable.TABLE_NAME;

    public static class FeedReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "shopwise.db";

        public FeedReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(@NonNull SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_LIST_TABLE);
            db.execSQL(SQL_CREATE_ITEM_TABLE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}
