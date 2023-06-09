package unijui.edu.br.shopwise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
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
    private Button saveButton;
    private LinearLayout verticalLayout;
    private ListHandler productsList;

    private DBHelper.FeedReaderDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productsList = new ListHandler("Nova Lista");
        setContentView(R.layout.activity_main);

        inputItem = findViewById(R.id.inputItem);
        saveButton = findViewById(R.id.saveButton);
        verticalLayout = findViewById(R.id.verticalItems);

        saveButton.setVisibility(View.GONE);

        // Cria o canal de notificação

        NotificationHelper.createNotificationChannel(this);

        // Cria a notificação
        Notification notification = NotificationHelper.createNotification(this, "Title", "Text");

        // Exibe a notificação
        NotificationHelper.sendNotification(this, notification);

        // Criação do banco de dados
        dbHelper = new DBHelper.FeedReaderDbHelper(getApplicationContext());
    }

    private void renderItems(){
        LayoutInflater inflater = LayoutInflater.from(this);

        productsList.renderListOn(verticalLayout, inflater);

        if(productsList.getProductsLength() > 0){
            saveButton.setVisibility(View.VISIBLE);
        }else{
            saveButton.setVisibility(View.GONE);
        }
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

    public void onMapClick(View view){
        Intent intent = new Intent(MainActivity.this, MapsScreen.class);
        startActivity(intent);
    }

    public void onSaveClick(View view){
        ListDAO.saveListHandler(dbHelper.getWritableDatabase(), productsList);
        productsList = new ListHandler("Nova Lista");
        renderItems();
    }

}