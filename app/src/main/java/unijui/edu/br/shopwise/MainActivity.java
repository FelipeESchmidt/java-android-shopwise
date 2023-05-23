package unijui.edu.br.shopwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private EditText inputItem;
    private LinearLayout verticalLayout;
    private ListHandler productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productsList = new ListHandler("Nova Lista");
        setContentView(R.layout.activity_main);

        inputItem = findViewById(R.id.inputItem);
        verticalLayout = findViewById(R.id.verticalItems);

        //Aplique isso no lugar certo e no seu contexto :)
        SaveList.FeedReaderDbHelper dbHelper = new SaveList.FeedReaderDbHelper(getApplicationContext());

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SaveList.FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "alguma coisa");
        values.put(SaveList.FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, "alguma outra coisa");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(SaveList.FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, null, values);
    }

    private void renderItems(){
        LayoutInflater inflater = LayoutInflater.from(this);

        productsList.renderListOn(verticalLayout, inflater);
    }

    public void onAddItem(View view) {
        String item = inputItem.getText().toString();
        inputItem.setText("");

        if(item.length() < 1) return;

        productsList.addProduct(new Product(item));
        renderItems();
    }

    public void onIncreaseQuantity(View view){
        Button buttonPlus = view.findViewById(R.id.plusButton);
        String productId = (String) buttonPlus.getTag();

        productsList.increaseProductQuantity(productId);
        renderItems();
    }

    public void onDecreaseQuantity(View view){
        Button buttonPlus = view.findViewById(R.id.minusButton);
        String productId = (String) buttonPlus.getTag();

        productsList.decreaseProductQuantity(productId);
        renderItems();
    }

    public void onHistoricClick(View view){
        Intent intent = new Intent(MainActivity.this, HistoricScreen.class);
        startActivity(intent);
    }

}