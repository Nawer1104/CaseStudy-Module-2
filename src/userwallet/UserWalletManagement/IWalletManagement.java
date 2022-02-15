package userwallet.UserWalletManagement;

import productlist_runbyadmin.model.Product;

import java.util.List;

public interface IWalletManagement {
    public List<Product> getWalletList(String idName);

    public void writeFile(String idName);

    public List<Product> readFile(String name);

    public void removeToken(int index, String idName);

    public void addTokenIntoList(Product product, String idName);

    public void clearFile(String idName);
}
