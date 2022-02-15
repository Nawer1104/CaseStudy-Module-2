package shoppingcart.CartView;

import account.productList_runbyuser.controller.ProductControllerRunByUser;
import account.productList_runbyuser.view.Menu_User;
import account.user.AccountUser;
import account.user.AccountUserManagement;
import productlist_runbyadmin.model.Product;
import shoppingcart.CartManagement.CartManagementImpl;
import userwallet.UserWalletManagement.WalletManagementImpl;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    ProductControllerRunByUser productControllerRunByUser = new ProductControllerRunByUser();
    AccountUserManagement accountUserManagement = new AccountUserManagement();
    CartManagementImpl cartManagementImpl = new CartManagementImpl();
    WalletManagementImpl walletManagement = new WalletManagementImpl();
    Scanner scanner = new Scanner(System.in);

    public void addProductToShoppingCart (Product product, String idName) {
        cartManagementImpl.addProductIntoList(product, idName);
    }

    public void showShoppingCart(AccountUser user) {
        int count = 0;
        System.out.println("---------------------------------------------");
        System.out.println("----|           SHOPPING CART           |----");
        System.out.println("---------------------------------------------");

        while (true) {
            int sumOfTotal = 0;
            for (int i = 0; i < cartManagementImpl.getShoppingList(user.getUserId()).size(); i++) {
                count++;
                System.out.println("|                 {  [" + count + "]  }                 |");
                System.out.println("| Token's Name: " + cartManagementImpl.getShoppingList(user.getUserId()).get(i).getId() + "\n"
                + "| Token's Name: " + cartManagementImpl.getShoppingList(user.getUserId()).get(i).getName() + "\n"
                + "| Token's Price: " + cartManagementImpl.getShoppingList(user.getUserId()).get(i).getPrice() + "\n"
                + "| Token's Quantity: " + cartManagementImpl.getShoppingList(user.getUserId()).get(i).getQuantity() + "\n"
                + "| Token's Description: " + cartManagementImpl.getShoppingList(user.getUserId()).get(i).getDescription() + "\n"
                + "| Total is: " + cartManagementImpl.getShoppingList(user.getUserId()).get(i).getTotal() + "\n"
                );
                sumOfTotal += cartManagementImpl.getShoppingList(user.getUserId()).get(i).getTotal();
            }
            System.out.println("| >>>>> Total Bill is: " + sumOfTotal + " <<<<<<");
            System.out.println("\\--------------------------------------------/");
            System.out.println("| Type -yes to confirm buy                     ");
            System.out.println("| Type -quit to back to User Menu              ");
            System.out.println("| Type -clear to clear these token            ");
            System.out.println("-----------------------------------------------");
            System.out.print("Make your choice: ");
            try {
                String choice = scanner.nextLine();
                System.out.println(".............");
                switch (choice) {
                    case "yes":
                        if (checkList()) {
                            int result = user.getAccountSummary() - sumOfTotal;
                            if (result > 0) {
                                System.out.println("Paid Success!");
                                System.out.println("Your current balance now is: " + result);
                                for (int i = 0; i < productControllerRunByUser.showAll().size(); i++) {
                                    if (productControllerRunByUser.showAll().get(i).getQuantity() == 0) {
                                        productControllerRunByUser.removeProduct(i);
                                    }
                                }
                                for (Product x : cartManagementImpl.getShoppingList(user.getUserId())) {
                                    walletManagement.addTokenIntoList(x, user.getUserId());
                                }
                                user.setAccountSummary(result);
                                accountUserManagement.setNewListAccountUser(user, findIndexOfUser(user));
                                cartManagementImpl.clearFile(user.getUserId());
                                String typeToExit;
                                do {
                                    System.out.println("Type -quit to back to Menu");
                                    typeToExit = scanner.next();
                                    if (typeToExit.equalsIgnoreCase("quit")) {
                                        new Menu_User(user);
                                    }
                                } while (!(typeToExit.equals("quit")));
                            } else {
                                System.err.println("Your current balance are not enough to pay!");
                                new Menu_User(user);
                            }
                        } else {
                            System.err.println("   -Shopping list are now empty!!-   ");
                            System.err.println("    -Going back to the User Menu-    ");
                            new Menu_User(user);
                        }
                        break;
                    case "clear":
                        while(true) {
                            System.out.println("Type -yes to confirm clear all token, -quit to back to Menu");
                            String typeConfirm = scanner.nextLine();
                            if (typeConfirm.equals("yes")) {
                                List<Product> productList = productControllerRunByUser.showAll();
                                List<Product> shoppingList = cartManagementImpl.getShoppingList(user.getUserId());
                                for (int i = 0; i < productList.size(); i++) {
                                    for (int j = 0; j < shoppingList.size(); j++) {
                                        if (productList.get(i).getId() == shoppingList.get(j).getId()) {
                                            productList.get(i).setQuantity(shoppingList.get(j).getQuantity() + productList.get(i).getQuantity());
                                        }
                                    }
                                }
                                productControllerRunByUser.writeToFile();
                                cartManagementImpl.clearFile(user.getUserId());
                                System.out.println("Clear success");
                                String typeToExit;
                                do {
                                    System.out.println("Type -quit to back to Menu");
                                    typeToExit = scanner.next();
                                    if (typeToExit.equalsIgnoreCase("quit")) {
                                        new Menu_User(user);
                                    }
                                } while (!(typeToExit.equals("quit")));
                            } else if ((typeConfirm.equals("quit"))) {
                                new Menu_User(user);
                            }
                        }
                    case "quit": new Menu_User(user);
                        break;
                    default:
                        System.err.println("Wrong input!");
                }
            } catch (InputMismatchException e) {
                System.err.println("Input number incorrect!!");
            }
        }
    }

    public int findIndexOfUser(AccountUser user) {
        for (int i = 0; i < accountUserManagement.getAccountUserList().size(); i++) {
            if (user.getUserId().equals(accountUserManagement.getAccountUserList().get(i).getUserId())){
                return i;
            }
        }
        return -1;
    }

    public boolean checkList() {
        return accountUserManagement.getAccountUserList().size() > 0;
    }
}
