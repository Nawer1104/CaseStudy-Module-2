package account.productList_runbyuser.view;

import account.createAccount.UserPersonalInformation;
import account.user.AccountUser;
import mainmenu.MainMenu;
import productlist_runbyadmin.view.Menu_Admin;
import shoppingcart.CartView.ShoppingCart;
import userwallet.UserWalletView.WalletView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu_User {
    AccountUser user;
    Scanner scanner = new Scanner(System.in);
    ProductView_ByUser productViewByUser = new ProductView_ByUser();
    UserPersonalInformation userPersonalInformation = new UserPersonalInformation();
    ShoppingCart shoppingCart = new ShoppingCart();
    WalletView walletView = new WalletView();
    boolean isValidNumber;

    public Menu_User(AccountUser user) {
        this.user = user;
        do {
            isValidNumber = true;
            System.out.println("-----------------------------------------------------------");
            System.out.println("|---      BINANCE SMART CHAIN       --- RUNNING BY USER---|");
            System.out.println("|                      ✌︎('ω')✌︎                       |");
            System.out.println("   ---USER ARE LOGGING IN TO THE SYSTEM :" + user.getName() + "---");
            System.out.println("-----------------------------------------------------------");
            System.out.println("|1. Show Token list                                       |");
            System.out.println("|2. Search Token by Id                                    |");
            System.out.println("|3. Search Token by price range                           |");
            System.out.println("|4. Show the most expensive Token                         |");
            System.out.println("|5. Show the cheapest Token                               |");
            System.out.println("|6. Show the shopping cart                                |");
            System.out.println("|7. Edit personal profile                                 |");
            System.out.println("|8. Check user's wallet                                   |");
            System.out.println("|0. Back to Main Menu                                     |");
            System.out.println("\\_________________________________________________________/");
            System.out.print("Make your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productViewByUser.showProductList(user);
                        break;
                    case 2:
                        productViewByUser.searchProductById(user);
                        break;
                    case 3:
                        productViewByUser.searchProductByRange(user);
                        break;
                    case 4:
                        productViewByUser.showTheMostExpensiveProduct(user);
                        break;
                    case 5:
                        productViewByUser.showTheCheapestProduct(user);
                        break;
                    case 6:
                        shoppingCart.showShoppingCart(user);
                        break;
                    case 7:
                        userPersonalInformation.userDetail(user);
                        break;
                    case 8:
                        walletView.showWallet(user);
                        break;
                    case 0:
                        new MainMenu();
                        break;
                    default:
                        System.err.println("Input number incorrect!!");
                        new Menu_User(user);
                }
            } catch (NumberFormatException e) {
                System.err.println("Input number incorrect!!");
                isValidNumber = false;
            }
        } while (!isValidNumber);
    }
}
