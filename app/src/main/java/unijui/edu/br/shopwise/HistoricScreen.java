package unijui.edu.br.shopwise;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoricScreen extends AppCompatActivity {

    private LinearLayout verticalLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        verticalLayout = findViewById(R.id.verticalItems);
        renderHistoricList();
        renderHistoricList();
    }

    public void renderHistoricList(){
        LayoutInflater inflater = LayoutInflater.from(this);

        //verticalLayout.removeAllViews();
        View customView = inflater.inflate(R.layout._item_historic, null);
        TextView textViewNome = customView.findViewById(R.id.listName);
        textViewNome.setText("Lista de teste");

        TextView textViewQtd = customView.findViewById(R.id.quantityItem);
        textViewQtd.setText("2");

        verticalLayout.addView(customView);
    }

    public void onListItemsClick(View view){
        Intent intent = new Intent(HistoricScreen.this, MainActivity.class);
        startActivity(intent);
    }

}
