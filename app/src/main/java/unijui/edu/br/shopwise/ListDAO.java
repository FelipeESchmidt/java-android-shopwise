package unijui.edu.br.shopwise;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class ListDAO {

    public static long saveList(SQLiteDatabase db, ListHandler listToSave) throws Exception{
        ContentValues values = new ContentValues();
        values.put(DBHelper.FeedReaderContract.ListTable.COLUMN_NAME, listToSave.getName());

        return db.insert(DBHelper.FeedReaderContract.ListTable.TABLE_NAME, null, values);
    }

    public static void saveListItem(SQLiteDatabase db, Product productItem, long listId) throws Exception{
        ContentValues values = new ContentValues();
        values.put(DBHelper.FeedReaderContract.ItemTable.COLUMN_NAME, productItem.getName());
        values.put(DBHelper.FeedReaderContract.ItemTable.COLUMN_QUANTITY, productItem.getQuantity());
        values.put(DBHelper.FeedReaderContract.ItemTable.COLUMN_LIST_ID, listId);

        db.insert(DBHelper.FeedReaderContract.ItemTable.TABLE_NAME, null, values);
    }

    public static boolean saveListHandler(SQLiteDatabase db, ListHandler listToSave){
        try{
            long tableId = saveList(db, listToSave);

            for(Product product : listToSave.getProducts()){
                saveListItem(db, product, tableId);
            }

            return true;
        }catch (Exception e){
            return false;
        }
    }

}
