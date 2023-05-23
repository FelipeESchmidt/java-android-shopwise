package unijui.edu.br.shopwise;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListHandler {
    private String name;
    private ArrayList<Product> productList;

    public ListHandler(String name) {
        this.name = name;
        productList = new ArrayList<Product>();
    }

    public String getName() {
        return name;
    }

    public int getProductsLength() {
        return productList.size();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    private void updateProductQuantity(String productId, int updateBy) {
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                int newQuantity = product.getQuantity() + updateBy;
                if (newQuantity < 1) {
                    productList.remove(product);
                }else{
                    product.setQuantity(product.getQuantity() + updateBy);
                }
                break;
            }
        }
    }

    public void increaseProductQuantity(String productId) {
        updateProductQuantity(productId, 1);
    }

    public void decreaseProductQuantity(String productId) {
        updateProductQuantity(productId, -1);
    }

    public void renderListOn(LinearLayout renderView, LayoutInflater inflater){
        renderView.removeAllViews();
        for (Product product : productList) {
            View customView = inflater.inflate(R.layout._item, null);
            TextView textViewNome = customView.findViewById(R.id.nomeItem);
            textViewNome.setText(product.getName());

            TextView textViewQtd = customView.findViewById(R.id.quantidade);
            textViewQtd.setText(String.valueOf(product.getQuantity()));

            Button buttonPlus = customView.findViewById(R.id.plusButton);
            Button buttonMinus = customView.findViewById(R.id.minusButton);

            buttonPlus.setTag(product.getId());
            buttonMinus.setTag(product.getId());

            renderView.addView(customView);
        }
    }
}
