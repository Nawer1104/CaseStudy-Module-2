package productlist_runbyadmin.controller;

import productlist_runbyadmin.model.Product;
import productlist_runbyadmin.service.ManagementServiceImpl;

import java.util.List;

public class ProductControllerRunByAdmin {
    ManagementServiceImpl managementService = new ManagementServiceImpl();

    public List<Product> showListProduct() {
        return managementService.showAll();
    }

    public void addProduct(Product product) {
        managementService.add(product);
    }

    public Product findProductById(int index) {
       return  managementService.findProductById(index);
    }

    public void writeToFile() {
        managementService.writeToFile();
    }

    public void clearFile() {
        managementService.clearFile();
    }

    public void removeProduct(int index){
        managementService.removeById(index);
    }

    public void sortProductListByPriceFromLower() {
        managementService.sortListByPriceFromLower();
    }

    public void sortProductListByPriceFromHigher() {
        managementService.sortListByPriceFromHigher();
    }

    public void sortProductListById() {
        managementService.sortListById();
    }

    public List<Product> readFile() {
        return managementService.readFile();
    }
}
