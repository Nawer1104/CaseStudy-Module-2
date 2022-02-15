package shoppingcart.CartManagement;

import account.user.AccountUser;
import productlist_runbyadmin.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartManagementImpl implements ICartManagement {
    public static List<Product> shoppingList = new ArrayList<>();

    public List<Product> getShoppingList(String idName) {
        return shoppingList = readFile(idName);
    }

    public void setTokenOnList(Product product, String idName, int index) {
        getShoppingList(idName).set(index, product);
        writeFile(idName);
    }

    public void addProductIntoList (Product product, String idName) {
        getShoppingList(idName).add(product);
        writeFile(idName);
    }

    @Override
    public void clearFile(String idName) {
        try {
            FileWriter fileWriter = new FileWriter(idName + ".txt");
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProduct(int index, String idName) {
        getShoppingList(idName);
        shoppingList.remove(index);
        writeFile(idName);
    }


    @Override
    public void writeFile(String idName) {
        try {
            FileWriter fileWriter = new FileWriter(idName + ".txt");
            for (Product x : shoppingList) {
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
    public List<Product> readFile(String idName) {
        List<Product> newShoppingList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(idName + ".txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                String products[] = line.split(",");
                Product newProduct1 = new Product(Integer.parseInt(products[0]), products[1], Integer.parseInt(products[2]), Integer.parseInt(products[3]), products[4]);
                newShoppingList.add(newProduct1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newShoppingList;
    }
}
