package account.productList_runbyuser.controller;

import productlist_runbyadmin.model.Product;
import account.productList_runbyuser.service.ProductManagementImpl;


import java.util.List;

public class ProductControllerRunByUser {
    ProductManagementImpl productManagement = new ProductManagementImpl();

    public List<Product> showAll() {
        return productManagement.showAll();
    }

    public Product findProductById (int index) {
        return productManagement.findProductById(index);
    }

    public void writeToFile() {
        productManagement.writeToFile();
    }

    public void removeProduct(int index) {
        productManagement.removeProduct(index);
    }
}
