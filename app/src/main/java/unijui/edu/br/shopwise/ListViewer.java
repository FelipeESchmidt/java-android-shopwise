package unijui.edu.br.shopwise;

import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class ListViewer extends ListHandler {

    private boolean showingProducts;

    public ListViewer(String name) {
        super(name);
        showingProducts = false;
    }

    public boolean isShowingProducts() {
        return showingProducts;
    }

    public void changeShowingProducts() {
        this.showingProducts =! showingProducts;
    }

    @Override
    public void increaseProductQuantity(String productId) {
        return;
    }

    @Override
    public void decreaseProductQuantity(String productId) {
        return;
    }

    @Override
    public void renderListOn(LinearLayout renderView, LayoutInflater inflater) {
        return;
    }
}
