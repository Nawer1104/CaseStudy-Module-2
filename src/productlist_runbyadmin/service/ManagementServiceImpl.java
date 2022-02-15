package productlist_runbyadmin.service;

import productlist_runbyadmin.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ManagementServiceImpl implements IManagementService {
    public static List<Product> productList = new ArrayList<>();
    public static String filePatch = "products.txt";

    @Override
    public List<Product> showAll() {
        return productList = readFile();
    }

    @Override
    public void add(Product product) {
        showAll();
        productList.add(product);
        writeToFile();
    }

    @Override
    public void removeById(int index) {
        showAll();
        productList.remove(index);
        writeToFile();
    }

    @Override
    public Product findProductById(int index) {
        return productList.get(index);
    }

    @Override
    public void sortListByPriceFromLower() {

        Collections.sort(showAll(), new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() < o2.getPrice()) {
                    return -1;
                } else {
                    if (o1.getPrice() == o2.getPrice()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        });
        writeToFile();
    }

    @Override
    public void sortListById() {
        Collections.sort(showAll(), new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getId() < o2.getId()) {
                    return -1;
                } else {
                    if (o1.getPrice() == o2.getPrice()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        });
        writeToFile();
    }

    @Override
    public void sortListByPriceFromHigher() {
        Collections.sort(showAll(), new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() < o2.getPrice()) {
                    return 1;
                } else {
                    if (o1.getPrice() == o2.getPrice()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });
        writeToFile();
    }

    @Override
    public List<Product> readFile() {
        List<Product> newProductsList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePatch);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                String products[] = line.split(",");
                Product newProduct1 = new Product(Integer.parseInt(products[0]), products[1], Integer.parseInt(products[2]), Integer.parseInt(products[3]), products[4]);
                newProductsList.add(newProduct1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newProductsList;
    }

    @Override
    public void writeToFile() {
        try {
            FileWriter fileWriter = new FileWriter(filePatch);
            for (Product x : productList) {
                String productLine = x.getId() + ","
                        + x.getName() + ","
                        + x.getPrice() + ","
                        + x.getQuantity() + ","
                        + x.getDescription() + "\n";
                fileWriter.write(productLine);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearFile() {
        try {
            FileWriter fileWriter = new FileWriter(filePatch);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
