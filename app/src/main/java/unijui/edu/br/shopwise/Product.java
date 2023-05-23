package unijui.edu.br.shopwise;

import static unijui.edu.br.shopwise.RandomCodeGenerator.generateRandomCode;

public class Product {
    private String id;
    private String name;
    private int quantity;

    public Product(String name) {
        this.id = generateRandomCode();
        this.name = name;
        this.quantity = 1;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
