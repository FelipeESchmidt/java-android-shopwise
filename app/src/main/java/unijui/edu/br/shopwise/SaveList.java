package unijui.edu.br.shopwise;

public class SaveList {

    public final class FeedReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedReaderContract() {}

        /* Inner class that defines the table contents */
        public static class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "compras";
            public static final String COLUMN_NAME_TITLE = "listadecompras";
            public static final String COLUMN_NAME_SUBTITLE = "walker";
        }
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.compras + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.produto + " TEXT," +
                    FeedEntry.quantidade + " TEXT)";


    FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(getContext());

    // Gets the data repository in write mode
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    // Create a new map of values, where column names are the keys
    ContentValues values = new ContentValues();
values.put(FeedEntry.produto, title);
values.put(FeedEntry.quantidade, subtitle);

    // Insert the new row, returning the primary key value of the new row
    long newRowId = db.insert(FeedEntry.produtos, null, values);


}
