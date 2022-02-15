package productlist_runbyadmin.view;

import _ReadAndWriteFile.IOFile;
import account.user.AccountUser;
import account.user.AccountUserManagement;
import productlist_runbyadmin.controller.ProductControllerRunByAdmin;
import productlist_runbyadmin.model.Product;
import shoppingcart.CartManagement.CartManagementImpl;
import userwallet.UserWalletManagement.WalletManagementImpl;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ProductView_ByAdmin {
    Scanner scanner = new Scanner(System.in);
    ProductControllerRunByAdmin productControllerRunByAdmin = new ProductControllerRunByAdmin();
    private final IOFile<AccountUser> ioFile = new IOFile<>();
    AccountUserManagement accountUserManagement = new AccountUserManagement();
    CartManagementImpl cartManagement = new CartManagementImpl();
    WalletManagementImpl walletManagement = new WalletManagementImpl();
    boolean isValidNumber;

    public void showProductList() {

        int count = -1;
        System.out.println("-------------------------------------------------------");
        System.out.println("----{  ID---NAME---PRICE---QUANTITY---DESCRIPTION  }---");
        System.out.println("-------------------------------------------------------");
        System.out.println("Size : " + productControllerRunByAdmin.readFile().size());
        System.out.println("Press Enter will show 5 tokens everytime: ");
        for (int i = 0; i < productControllerRunByAdmin.readFile().size(); i++) {
            count++;
            if (count % 5 == 0) {
                System.out.println("-----------------------------------------------");
                scanner.nextLine();
            }
            System.out.println("         " + productControllerRunByAdmin.readFile().get(i).getId()
                    + "---" + productControllerRunByAdmin.readFile().get(i).getName()
                    + "---" + productControllerRunByAdmin.readFile().get(i).getPrice()
                    + "---" + productControllerRunByAdmin.readFile().get(i).getQuantity()
                    + "---" + productControllerRunByAdmin.readFile().get(i).getDescription());
        }
        System.out.println("\\------------------------------------------------------/");
        String typeToExit;
        do {
            System.out.println("Type -quit to back to Menu");
            typeToExit = scanner.next();
            if (typeToExit.equalsIgnoreCase("quit")) {
                new Menu_Admin();
            }
        } while (!(typeToExit.equals("quit")));
    }


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


    public void addNewProduct() {
        while (true) {
            System.out.println("-------------------------------------");
            System.out.println("----{  ADDING TOKEN TO THE LIST  }---");
            System.out.println("-------------------------------------");
            System.out.println("Enter token's id, id must not equal or less than 0 and be duplicated:");

            int id = checkValidInteger();
            while (id <= 0 || isIdExist(id)) {
                System.err.println("What did i say? Please strictly follow the guideline. Now re-enter token's id again:");
                id = checkValidInteger();
            }
            System.out.println("Enter token's name, also not able to be duplicate:");
            String name = scanner.nextLine();
            while (isNameExist(name)) {
                System.err.println("What did i say? Please strictly follow the guideline. Now re-enter token's id again:");
                name = scanner.nextLine();
            }
            System.out.println("Enter token's price, price is also not equal or less than 0.:");
            int price = checkValidInteger();
            while (price <= 0) {
                System.err.println("What did i say? Please strictly follow the guideline. Now re-enter token's price again:");
                price = checkValidInteger();
            }
            System.out.println("Enter token's quantity,the quantity can't be less than 0:");
            int quantity = checkValidInteger();
            while (quantity < 0) {
                System.err.println("What did i say? Please strictly follow the guideline. Now re-enter token's quantity again:");
                quantity = checkValidInteger();
            }
            System.out.println("Enter token's description:");
            String description = scanner.nextLine();
            Product product = new Product(id, name, price, quantity, description);
            productControllerRunByAdmin.addProduct(product);
            System.out.println("Created success");
            System.out.println("Type -quit to back to Menu");
            String typeToExit;
            do {
                typeToExit = scanner.next();
                if (typeToExit.equalsIgnoreCase("quit")) {
                    new Menu_Admin();
                }
            } while (!(typeToExit.equals("quit")));
        }
    }

    public void searchProductById() {
        while (true) {
            if (checkList()) {
                System.out.println("Enter token's Id number:");
                int id = checkValidInteger();
                boolean checkIdNumber = false;
                int index = -1;
                for (int i = 0; i < productControllerRunByAdmin.showListProduct().size(); i++) {
                    if (id == productControllerRunByAdmin.showListProduct().get(i).getId()) {
                        checkIdNumber = true;
                        index = i;
                    }
                }
                if (id == 0) {
                    new Menu_Admin();
                }
                if (checkIdNumber) {
                    Product product = productControllerRunByAdmin.findProductById(index);
                    System.out.println("---------------------------------------");
                    System.out.println("----{  WE HAVE FOUND THIS FOR YOU  }---");
                    System.out.println("---------------------------------------");
                    System.out.println("|---Token's ID: " + product.getId() + "\n"
                            + "|---Token's Name: " + product.getName() + "\n"
                            + "|---Token's Price: " + product.getPrice() + "\n"
                            + "|---Token's Quantity: " + product.getQuantity() + "\n"
                            + "|---Token's Description : " + product.getDescription());
                    System.out.println("\\--------------------------------------/");
                    String typeToExit;
                    do {
                        System.out.println("Type -quit to back to Menu");
                        typeToExit = scanner.next();
                        if (typeToExit.equalsIgnoreCase("quit")) {
                            new Menu_Admin();
                        }
                    } while (!(typeToExit.equals("quit")));
                } else {
                    System.err.println("Wrong input. Enter token's id again, of press -0 to back to Menu");
                }
            } else {
                System.err.println("Token List are empty!!!");
                new Menu_Admin();
            }
        }
    }

    public void editProductById() {
        while (true) {
            System.out.println("Enter Token's Id number:");
            int id = checkValidInteger();
            boolean checkIdNumber = false;
            int index = -1;
            for (int i = 0; i < productControllerRunByAdmin.showListProduct().size(); i++) {
                if (id == productControllerRunByAdmin.showListProduct().get(i).getId()) {
                    checkIdNumber = true;
                    index = i;
                }
            }
            if (checkIdNumber) {
                Product product = productControllerRunByAdmin.findProductById(index);
                System.out.println("------------------------------------------");
                System.out.println("----{  ARE YOU LOOKING FOR THIS ONE?  }---");
                System.out.println("------------------------------------------");
                System.out.println("  >>>> " + product.getId()
                        + "---" + product.getName()
                        + "---" + product.getPrice()
                        + "---" + product.getQuantity()
                        + "---" + product.getDescription());
                System.out.println("------------------------------------------");
                System.out.println("---------------------------------------------------------------------------");
                System.out.println("----------{                  EDIT TOKEN MENU              }----------------");
                System.out.println("*");
                System.out.println("Which value of token's detail you want to edit?");
                System.out.println("Type -name if you want to edit name of " + product.getName());
                System.out.println("Type -price if you want to edit price of " + product.getName());
                System.out.println("Type -quantity if you want to edit quantity of " + product.getName());
                System.out.println("Type -description if you want to edit description of " + product.getName());
                System.out.println("Type -quit to back to Menu");
                System.out.println("Type -all to change all token's information");
                System.out.println(" >>>> *Type anything else to chose another token! <<<");
                System.out.println("*");
                System.out.println("\\----------------------------------------------------------------------------/");
                System.out.print("Make your choice: ");
                String typeToEdit = scanner.nextLine();
                while (typeToEdit.equals("name") ||
                        typeToEdit.equals("price") ||
                        typeToEdit.equals("quantity") ||
                        typeToEdit.equals("description") ||
                        typeToEdit.equals("quit") ||
                        typeToEdit.equals("all")) {
                    if (typeToEdit.equals("name")) {
                        System.out.println("-------------------------------------------------------------------");
                        System.out.println("----------{             LET'S MAKE A GREAT NAME        }-----------");
                        System.out.println("-------------------------------------------------------------------");
                        System.out.println("Enter product's name:");
                        String newName = scanner.nextLine();
                        product.setName(newName);
                        changeElements_Name(product, newName);
                    } else if (typeToEdit.equals("price")) {
                        System.out.println("-------------------------------------------------------");
                        System.out.println("----------{             TO THE MOON        }-----------");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("Enter product's price, to the moonnnnnnnnnnn:");
                        int newPrice = checkValidInteger();
                        while (newPrice <= 0) {
                            System.err.println("Do you know what To the moon is? Now re-enter product's price again:");
                            newPrice = checkValidInteger();
                        }
                        product.setPrice(newPrice);
                        changeElements_Price(product, newPrice);
                    } else if (typeToEdit.equals("quantity")) {
                        System.out.println("-----------------------------------------------------------------------------------");
                        System.out.println("----------{             YOU GET FUCKED IF YOU CAN'T SELL IT ALL        }-----------");
                        System.out.println("-----------------------------------------------------------------------------------");
                        System.out.println("Enter token's quantity, what the point if you set negative number here?");
                        int newQuantity = checkValidInteger();
                        while (newQuantity < 0) {
                            System.err.println("What is that? Now re-enter token's quantity again:");
                            newQuantity = checkValidInteger();
                        }
                        product.setQuantity(newQuantity);
                        changeElements_Quantity(product, newQuantity);
                    } else if (typeToEdit.equals("description")) {
                        System.out.println("--------------------------------------------------------------------");
                        System.out.println("----------{             LET'S MAKE A GREAT STORY        }-----------");
                        System.out.println("--------------------------------------------------------------------");
                        System.out.println("Enter token's description:");
                        String newDescription = scanner.nextLine();
                        product.setDescription(newDescription);
                        changeElements_Description(product, newDescription);
                    } else if (typeToEdit.equals("all")) {
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("----------{             LET'S MAKE SOMETHING SPECIAL        }-----------");
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Enter token's name:");
                        String name = scanner.nextLine();
                        System.out.println("Enter token's price:");
                        int price = checkValidInteger();
                        System.out.println("Enter token's quantity:");
                        int quantity = checkValidInteger();
                        System.out.println("Enter token's description:");
                        String description = scanner.nextLine();
                        product.setName(name);
                        product.setPrice(price);
                        product.setQuantity(quantity);
                        product.setDescription(description);
                        changeElements_Name(product, name);
                        changeElements_Price(product, price);
                        changeElements_Quantity(product, quantity);
                        changeElements_Description(product, description);
                    } else if (typeToEdit.equals("quit")) {
                        new Menu_Admin();
                    }
                    System.out.println("Edit success");
                    productControllerRunByAdmin.writeToFile();
                    String typeToExit;
                    do {
                        System.out.println("Type -quit to back to Menu");
                        typeToExit = scanner.next();
                        if (typeToExit.equalsIgnoreCase("quit")) {
                            new Menu_Admin();
                        }
                    } while (!(typeToExit.equals("quit")));
                }
            } else {
                System.err.println("Wrong input. Enter token's id again");
            }
        }
    }

    public void removeProductById() {
        while (true) {
            if (checkList()) {
                System.out.println("Enter Token's Id number:");
                int id = checkValidInteger();
                boolean checkIdNumber = false;
                int index = -1;
                for (int i = 0; i < productControllerRunByAdmin.showListProduct().size(); i++) {
                    if (id == productControllerRunByAdmin.showListProduct().get(i).getId()) {
                        checkIdNumber = true;
                        index = i;
                    }
                }
                if (checkIdNumber) {
                    System.out.println("Type -yes to confirm delete, Type -quit to back to Menu");
                    String typeConfirm;
                    do {
                        typeConfirm = scanner.nextLine();
                        if (typeConfirm.equals("yes")) {
                            productControllerRunByAdmin.removeProduct(index);

                            System.out.println("Deleted success");
                            String typeToExit;
                            do {
                                System.out.println("Type -quit to back to Menu");
                                typeToExit = scanner.next();
                                if (typeToExit.equalsIgnoreCase("quit")) {
                                    new Menu_Admin();
                                }
                            } while (!(typeToExit.equals("quit")));
                        } else if (typeConfirm.equals("quit")) {
                            new Menu_Admin();
                        } else {
                            System.err.println("Wrong input!!!");
                            System.err.println("Enter again:");
                        }
                    } while (!typeConfirm.equals("yes"));
                } else {
                    System.err.println("Wrong input. Enter token's id again");
                }
            } else {
                System.err.println("Token List are empty!!!");
                new Menu_Admin();
            }
        }
    }

    public void changeElements_Price (Product product, int newPrice) {
        List<AccountUser> list = accountUserManagement.getAccountUserList();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < cartManagement.getShoppingList(list.get(i).getUserId()).size(); j++) {
                if (cartManagement.getShoppingList(list.get(i).getUserId()).get(j).getId() == product.getId()) {
                    cartManagement.getShoppingList(list.get(i).getUserId()).get(j).setPrice(newPrice);
                    cartManagement.writeFile(list.get(i).getUserId());
                }
            }
            for (int j = 0; j < walletManagement.getWalletList(list.get(i).getUserId()).size(); j++) {
                if (walletManagement.getWalletList(list.get(i).getUserId()).get(j).getId() == product.getId()) {
                    walletManagement.getWalletList(list.get(i).getUserId()).get(j).setPrice(newPrice);
                    walletManagement.writeFile(list.get(i).getUserId());
                }
            }
        }
    }

    public void changeElements_Name (Product product, String newName) {
        List<AccountUser> list = accountUserManagement.getAccountUserList();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < cartManagement.getShoppingList(list.get(i).getUserId()).size(); j++) {
                if (cartManagement.getShoppingList(list.get(i).getUserId()).get(j).getId() == product.getId()) {
                    cartManagement.getShoppingList(list.get(i).getUserId()).get(j).setName(newName);
                    cartManagement.writeFile(list.get(i).getUserId());
                }
            }
            for (int j = 0; j < walletManagement.getWalletList(list.get(i).getUserId()).size(); j++) {
                if (walletManagement.getWalletList(list.get(i).getUserId()).get(j).getId() == product.getId()) {
                    walletManagement.getWalletList(list.get(i).getUserId()).get(j).setName(newName);
                    walletManagement.writeFile(list.get(i).getUserId());
                }
            }
        }
    }

    public void changeElements_Quantity (Product product, int newQuantity) {
        List<AccountUser> list = accountUserManagement.getAccountUserList();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < cartManagement.getShoppingList(list.get(i).getUserId()).size(); j++) {
                if (cartManagement.getShoppingList(list.get(i).getUserId()).get(j).getId() == product.getId()) {
                    cartManagement.getShoppingList(list.get(i).getUserId()).get(j).setQuantity(newQuantity);
                    cartManagement.writeFile(list.get(i).getUserId());
                }
            }
            for (int j = 0; j < walletManagement.getWalletList(list.get(i).getUserId()).size(); j++) {
                if (walletManagement.getWalletList(list.get(i).getUserId()).get(j).getId() == product.getId()) {
                    walletManagement.getWalletList(list.get(i).getUserId()).get(j).setQuantity(newQuantity);
                    walletManagement.writeFile(list.get(i).getUserId());
                }
            }
        }
    }

    public void changeElements_Description (Product product, String newDescription) {
        List<AccountUser> list = accountUserManagement.getAccountUserList();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < cartManagement.getShoppingList(list.get(i).getUserId()).size(); j++) {
                if (cartManagement.getShoppingList(list.get(i).getUserId()).get(j).getId() == product.getId()) {
                    cartManagement.getShoppingList(list.get(i).getUserId()).get(j).setDescription(newDescription);
                    cartManagement.writeFile(list.get(i).getUserId());
                }
            }
            for (int j = 0; j < walletManagement.getWalletList(list.get(i).getUserId()).size(); j++) {
                if (walletManagement.getWalletList(list.get(i).getUserId()).get(j).getId() == product.getId()) {
                    walletManagement.getWalletList(list.get(i).getUserId()).get(j).setDescription(newDescription);
                    walletManagement.writeFile(list.get(i).getUserId());
                }
            }
        }
    }

    public void sortProductListByPrice() {
        while (true) {
            if (checkList()) {
                System.out.println("Choose 1 to sort token list from lower price to higher price");
                System.out.println("Choose 2 to sort token list from higher price to lower price");
                System.out.println("Choose 3 to sort token list by id");
                System.out.println("Choose 4 to back to Menu");

                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
                    switch (choice) {
                        case 1:
                            productControllerRunByAdmin.sortProductListByPriceFromLower();
                            break;
                        case 2:
                            productControllerRunByAdmin.sortProductListByPriceFromHigher();
                            break;
                        case 3:
                            productControllerRunByAdmin.sortProductListById();
                            break;
                        case 4:
                            new Menu_Admin();
                            break;
                    }
                    System.out.println("Sort success");
                    productControllerRunByAdmin.writeToFile();
                    String typeToExit;
                    do {
                        System.out.println("Type -quit to back to Menu");
                        typeToExit = scanner.next();
                        if (typeToExit.equalsIgnoreCase("quit")) {
                            new Menu_Admin();
                        }
                    } while (!(typeToExit.equals("quit")));
                } else {
                    System.err.println("Wrong input, enter again!");
                }
            } else {
                System.err.println("Token List are empty!!!");
                new Menu_Admin();
            }
        }
    }

    public void showTheMostExpensiveProduct() {
        if (checkList()) {
            int index = -1;
            int highestPrice = 0;
            for (int i = 0; i < productControllerRunByAdmin.showListProduct().size(); i++) {
                if (productControllerRunByAdmin.showListProduct().get(i).getPrice() > highestPrice) {
                    highestPrice = productControllerRunByAdmin.showListProduct().get(i).getPrice();
                }
            }
            System.out.println("------------------------------------------");
            System.out.println("----{  ARE YOU LOOKING FOR THIS ONE?  }---");
            System.out.println("------------------------------------------");
            for (int i = 0; i < productControllerRunByAdmin.showListProduct().size(); i++) {
                if (highestPrice == productControllerRunByAdmin.showListProduct().get(i).getPrice()) {
                    index = i ;
                    Product product = productControllerRunByAdmin.findProductById(index);

                    System.out.println("  >>>> " + product.getId()
                            + "---" + product.getName()
                            + "---" + product.getPrice()
                            + "---" + product.getQuantity()
                            + "---" + product.getDescription());
                    System.out.println();

                }
            }
            System.out.println("------------------------------------------");
            String typeToExit;
            do {
                System.out.println("Type -quit to back to Menu");
                typeToExit = scanner.next();
                if (typeToExit.equalsIgnoreCase("quit")) {
                    new Menu_Admin();
                }
            } while (!(typeToExit.equals("quit")));
        } else {
            System.err.println("Token List are empty!!!");
            new Menu_Admin();
        }
    }

    public void clearFile() {
        while (true) {
            System.out.println("Type -yes to confirm delete all file, -quit to back to Menu");
            String typeConfirm = scanner.nextLine();
            if (typeConfirm.equals("yes")) {
                productControllerRunByAdmin.clearFile();
                System.out.println("Clear success");
                String typeToExit;
                do {
                    System.out.println("Type -quit to back to Menu");
                    typeToExit = scanner.next();
                    if (typeToExit.equalsIgnoreCase("quit")) {
                        new Menu_Admin();
                    }
                } while (!(typeToExit.equals("quit")));
            } else if ((typeConfirm.equals("quit"))) {
                new Menu_Admin();
            }
        }
    }

    public boolean checkList() {
        return productControllerRunByAdmin.showListProduct().size() > 0;
    }

    public boolean isIdExist(int id) {
        for (int i = 0; i < productControllerRunByAdmin.showListProduct().size(); i++) {
            if (id == productControllerRunByAdmin.showListProduct().get(i).getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean isNameExist(String name) {
        for (int i = 0; i < productControllerRunByAdmin.showListProduct().size(); i++) {
            if (name.equals(productControllerRunByAdmin.showListProduct().get(i).getName())) {
                return true;
            }
        }
        return false;
    }

    public void clearUserFile() throws IOException {
        while (true) {
            System.out.println("Type -yes to confirm delete all users data, -quit to back to Menu");
            String typeConfirm = scanner.nextLine();
            if (typeConfirm.equals("yes")) {
                List<AccountUser> list = null;
                ioFile.clearFileData(list,"C:\\Users\\hihih\\IdeaProjects\\CaseStudy2\\accountuser.txt");
                System.out.println("Clear success");
                String typeToExit;
                do {
                    System.out.println("Type -quit to back to Menu");
                    typeToExit = scanner.next();
                    if (typeToExit.equalsIgnoreCase("quit")) {
                        new Menu_Admin();
                    }
                } while (!(typeToExit.equals("quit")));
            } else if ((typeConfirm.equals("quit"))) {
                new Menu_Admin();
            }
        }
    }

    public void showUserFileList() throws IOException {
        if (accountUserManagement.getAccountUserList() != null) {
            System.out.println("-----------------------------------------------");
            System.out.println("----{   USERS COULD ACCESS TO THE SYSTEM   }---");
            System.out.println("-----------------------------------------------");
            System.out.println();
            int count = 0;
            List<AccountUser> list = accountUserManagement.getAccountUserList();
            for (int i = 0; i < list.size(); i++) {
                count++;

                System.out.println("Account User number " + count + "\n"
                        + "Account id: " + list.get(i).getUserId() + "---"
                        + "Account password: " + list.get(i).getUserPassword());
            }
            System.out.println();
            System.out.println("-----------------------------------------------");
            String typeToExit;
            do {
                System.out.println("Type -quit to back to Menu");
                typeToExit = scanner.next();
                if (typeToExit.equalsIgnoreCase("quit")) {
                    new Menu_Admin();
                }
            } while (!(typeToExit.equals("quit")));
        } else {
            System.err.println("List is empty now!");
            new Menu_Admin();
        }
    }
}
