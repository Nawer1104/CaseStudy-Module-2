package shoppingcart.CartManagement;

import productlist_runbyadmin.model.Product;

import java.util.List;

public interface ICartManagement {
    public List<Product> getShoppingList(String idName);

    public void writeFile(String idName);

    public List<Product> readFile(String idName);

    public void removeProduct(int index, String idName);

    public void addProductIntoList(Product product, String idName);

    public void clearFile(String idName);
}
