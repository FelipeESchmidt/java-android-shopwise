package unijui.edu.br.shopwise;
import android.app.Application;

import java.util.ArrayList;

public class ApplicationData extends Application {
    private ArrayList<ListHandler> historicList;

    public ArrayList<ListHandler> getHistoricList() {
        return historicList;
    }

    public void addHistoricItem(ListHandler listToBeAdded) {
        this.historicList.add(listToBeAdded);
    }

}
