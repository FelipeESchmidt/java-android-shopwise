package unijui.edu.br.shopwise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HistoricScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
    }

    public void onListItemsClick(View view){
        Intent intent = new Intent(HistoricScreen.this, MainActivity.class);
        startActivity(intent);
    }

}
