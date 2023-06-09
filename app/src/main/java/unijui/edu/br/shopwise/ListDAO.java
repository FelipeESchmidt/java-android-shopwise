package unijui.edu.br.shopwise;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListDAO {

    public static long saveList(SQLiteDatabase db, ListHandler listToSave) throws SQLException {
        ContentValues values = new ContentValues();
        values.put(DBHelper.FeedReaderContract.ListTable.COLUMN_NAME, listToSave.getName());

        return db.insertOrThrow(DBHelper.FeedReaderContract.ListTable.TABLE_NAME, null, values);
    }

    public static void saveListItem(SQLiteDatabase db, Product productItem, long listId) throws SQLException{
        ContentValues values = new ContentValues();
        values.put(DBHelper.FeedReaderContract.ItemTable.COLUMN_NAME, productItem.getName());
        values.put(DBHelper.FeedReaderContract.ItemTable.COLUMN_QUANTITY, productItem.getQuantity());
        values.put(DBHelper.FeedReaderContract.ItemTable.COLUMN_LIST_ID, listId);

        db.insertOrThrow(DBHelper.FeedReaderContract.ItemTable.TABLE_NAME, null, values);
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

    public static ArrayList<ListHandler> getAllLists(SQLiteDatabase db){
        Map<Integer, ListHandler> lists = new HashMap<>();

        Cursor cursor = db.rawQuery(DBHelper.SQL_SELECT_ALL_LISTS, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int listId = cursor.getInt(cursor.getColumnIndex("listId"));
                @SuppressLint("Range") String listName = cursor.getString(cursor.getColumnIndex("listName"));

                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex("itemName"));
                @SuppressLint("Range") int productQuantity = cursor.getInt(cursor.getColumnIndex("itemQuantity"));

                ListHandler list;
                if (lists.containsKey(listId)) {
                    list = lists.get(listId);
                } else {
                    list = new ListHandler(listName);
                    lists.put(listId, list);
                }

                Product product = new Product(productName);
                product.setQuantity(productQuantity);
                list.addProduct(product);

            } while (cursor.moveToNext());
        }

        return new ArrayList<>(lists.values());
    }

}
