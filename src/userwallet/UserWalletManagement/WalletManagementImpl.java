package userwallet.UserWalletManagement;

import productlist_runbyadmin.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WalletManagementImpl implements IWalletManagement {
    public static List<Product> walletList = new ArrayList<>();


    @Override
    public List<Product> getWalletList(String idName) {
        return walletList = readFile(idName);
    }

    @Override
    public void writeFile(String idName) {
        try {
            FileWriter fileWriter = new FileWriter(idName + "wallet.txt");
            for (Product x : walletList) {
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
            FileReader fileReader = new FileReader(idName + "wallet.txt");
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

    @Override
    public void removeToken(int index, String idName) {
        getWalletList(idName).remove(index);
        writeFile(idName);
    }

    @Override
    public void addTokenIntoList(Product product, String idName) {
        getWalletList(idName).add(product);
        writeFile(idName);
    }

    public void setTokenOnList(Product product, String idName, int index) {
        getWalletList(idName).set(index, product);
        writeFile(idName);
    }


    @Override
    public void clearFile(String idName) {
        try {
            FileWriter fileWriter = new FileWriter(idName + "wallet.txt");
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
