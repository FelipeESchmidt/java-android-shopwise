package unijui.edu.br.shopwise;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class HistoricScreen extends AppCompatActivity {

    private LinearLayout verticalLayout;

    private DBHelper.FeedReaderDbHelper dbHelper;

    ArrayList<ListViewer> historicItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        dbHelper = new DBHelper.FeedReaderDbHelper(getApplicationContext());

        verticalLayout = findViewById(R.id.verticalItems);

        historicItems = ListDAO.getAllLists(dbHelper.getReadableDatabase());

        renderHistoricList();
    }

    public void renderHistoricList(){
        LayoutInflater inflater = LayoutInflater.from(this);

        verticalLayout.removeAllViews();

        for (ListViewer item : historicItems){
            int productLength = item.getProductsLength();

            View customView = inflater.inflate(R.layout._item_historic, null);
            TextView textViewNome = customView.findViewById(R.id.listName);
            textViewNome.setText(item.getName());

            TextView textViewQtd = customView.findViewById(R.id.quantityItem);
            textViewQtd.setText(String.valueOf(productLength));

            TextView textViewQtdProducts = customView.findViewById(R.id.productText);
            textViewQtdProducts.setText(productLength == 1 ? R.string.single_product : R.string.multiple_products);

            MaterialButton showItems = customView.findViewById(R.id.showItems);

            showItems.setOnClickListener((View v) -> {
                item.changeShowingProducts();
                renderHistoricList();
            });

            if(item.isShowingProducts()){
                showItems.setIconResource(R.drawable.eye_hide);
                LinearLayout verticalLayoutItems = customView.findViewById(R.id.productsWrapper);

                for (Product itemProduct : item.getProducts()) {
                    View customItemView = inflater.inflate(R.layout._item_historic_item, null);

                    String itemProductAsText = itemProduct.getName() + " -> x" + itemProduct.getQuantity();

                    TextView textViewItemProduct = customItemView.findViewById(R.id.itemText);
                    textViewItemProduct.setText(itemProductAsText);

                    verticalLayoutItems.addView(customItemView);
                }

                verticalLayoutItems.setVisibility(View.VISIBLE);
            }

            verticalLayout.addView(customView);
        }
    }

    public void onListItemsClick(View view){
        Intent intent = new Intent(HistoricScreen.this, MainActivity.class);
        startActivity(intent);
    }

    public void onMapClick(View view){
        Intent intent = new Intent(HistoricScreen.this, MapsScreen.class);
        startActivity(intent);
    }

}
