package userwallet.UserWalletView;

import account.productList_runbyuser.view.Menu_User;
import account.user.AccountUser;
import account.user.AccountUserManagement;
import productlist_runbyadmin.model.Product;
import userwallet.UserWalletManagement.WalletManagementImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WalletView {
    AccountUserManagement accountUserManagement = new AccountUserManagement();
    WalletManagementImpl walletManagement = new WalletManagementImpl();
    Scanner scanner = new Scanner(System.in);
    boolean isValidNumber;
    boolean isValidName;

    public int checkValidInteger() {
        int value = -1;
        do {
            isValidNumber = true;
            try {
                value = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Wrong input, must be number!!");
                System.out.println("Enter again:");
                isValidNumber = false;
            }
        } while (!isValidNumber);
        return value;
    }

    public int findIndexByName(String name, AccountUser user) {
        for (int i = 0; i < walletManagement.getWalletList(user.getUserId()).size(); i++) {
            if (walletManagement.getWalletList(user.getUserId()).get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public int findIndexOfUser(AccountUser user) {
        for (int i = 0; i < accountUserManagement.getAccountUserList().size(); i++) {
            if (user.getUserId().equals(accountUserManagement.getAccountUserList().get(i).getUserId())){
                return i;
            }
        }
        return -1;
    }

    public boolean isValidName(String name, AccountUser user) {
        for (Product  x :  walletManagement.getWalletList(user.getUserId())) {
            if (name.equals(x.getName())) {
                return true;
            }
        }
        return false;
    }

    public void showWallet (AccountUser user) {
        int count = 0;
        System.out.println("-------------------------------------------");
        System.out.println("----|           Your Wallet           |----");
        System.out.println("-------------------------------------------");

        while (true) {
            int sumOfTotal = 0;
            for (int i = 0; i < walletManagement.getWalletList(user.getUserId()).size(); i++) {
                count++;
                System.out.println("|                 {  [" + count + "]  }                 |");
                System.out.println("| Token's Id: " + walletManagement.getWalletList(user.getUserId()).get(i).getId() + "\n"
                        + "| Token's Name: " + walletManagement.getWalletList(user.getUserId()).get(i).getName() + "\n"
                        + "| Token's Price: " + walletManagement.getWalletList(user.getUserId()).get(i).getPrice() + "\n"
                        + "| Token's Quantity: " + walletManagement.getWalletList(user.getUserId()).get(i).getQuantity() + "\n"
                        + "| Token's Description: " + walletManagement.getWalletList(user.getUserId()).get(i).getDescription() + "\n"
                        + "| Total is: " + walletManagement.getWalletList(user.getUserId()).get(i).getTotal() + "\n"
                );
                sumOfTotal += walletManagement.getWalletList(user.getUserId()).get(i).getTotal();
            }
            System.out.println("| >>>>> Total Profit is: " + sumOfTotal + " <<<<<<");
            System.out.println("\\--------------------------------------------/");
            System.out.println("| Type -sell to sell your token                ");
            System.out.println("| Type -quit to back to User Menu              ");
            System.out.println("-----------------------------------------------");
            System.out.print("Make your choice: ");
            try {
                String choice = scanner.nextLine();
                System.out.println(".............");
                switch (choice) {
                    case "sell":
                        System.out.println("-----------------------------------------------");
                        if (walletManagement.getWalletList(user.getUserId()).size() > 0) {
                            for (Product  x :  walletManagement.getWalletList(user.getUserId())) {
                                System.out.println(" { [ " + x.getName() + " ] } ");
                            }
                            System.out.println(" {-->>>  Choose your token: <<<--} ");
                            System.out.println(".............");
                            do {
                                String sellingChoice = scanner.nextLine();
                                if (isValidName(sellingChoice, user)) {
                                    isValidName = true;
                                    for (Product  x :  walletManagement.getWalletList(user.getUserId())) {
                                        if (sellingChoice.equals(x.getName())) {
                                            System.out.println("Enter numbers token of " + x.getName());
                                            do {
                                                int numberOfToken = checkValidInteger();
                                                if (numberOfToken <= x.getQuantity() || numberOfToken <= 0) {
                                                    int result = numberOfToken * x.getPrice();
                                                    System.out.println("Do you want to sell " + numberOfToken + " of " +   x.getName() + "???");
                                                    System.out.println("You gonna take : " + result + "$ as profit after this transaction \\ $_$ /");
                                                    System.out.println("Type -yes to confirm, -quit to back to Menu");
                                                    String typeConfirm = scanner.nextLine();
                                                    if (typeConfirm.equals("yes")) {
                                                        x.setQuantity(x.getQuantity() - numberOfToken);
                                                        walletManagement.setTokenOnList(x, user.getUserId(), findIndexByName(x.getName(), user));
                                                        if (x.getQuantity() == 0) {
                                                            walletManagement.removeToken(findIndexByName(x.getName(), user), user.getUserId());
                                                        }
                                                        user.setAccountSummary(result + user.getAccountSummary());
                                                        accountUserManagement.setNewListAccountUser(user, findIndexOfUser(user));
                                                        System.out.println("|-------------------------------------------|");
                                                        System.out.println("|     SOLD!!!  \\ (^ 3 ^) /  ~ (^ . ^) ~     |");
                                                        System.out.println("|                                           |");
                                                        System.out.println("|  THANKS YOU FOR USING BINANCE SMART CHAIN |");
                                                        System.out.println("|-------------------------------------------|");
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
                                                } else {
                                                    System.err.println("Not able to do that! Enter again: ");
                                                }
                                            } while (true);
                                        }
                                    }
                                } else {
                                    System.err.println("Your token wasn't found!! Re-enter again:");
                                }
                            } while (!isValidName);
                        } else {
                            System.err.println("   -Your token list are now empty!!-   ");
                            System.err.println("       -Going back to User Menu-       ");
                            new Menu_User(user);
                        }
                        break;
                    case "quit":
                        new Menu_User(user);
                        break;
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }
        }
    }
}
