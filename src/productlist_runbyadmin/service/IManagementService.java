package productlist_runbyadmin.service;

import productlist_runbyadmin.model.Product;

import java.util.List;

public interface IManagementService {
    List<Product> showAll();

    void add(Product product);

    void removeById(int index);

    Product findProductById(int index);

    void sortListByPriceFromLower();

    void sortListByPriceFromHigher();

    void sortListById();

    List<Product> readFile();

    void writeToFile();

    void clearFile();

}
