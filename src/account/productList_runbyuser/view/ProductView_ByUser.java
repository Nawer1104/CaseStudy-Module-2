package account.productList_runbyuser.view;

import account.productList_runbyuser.controller.ProductControllerRunByUser;
import productlist_runbyadmin.model.Product;
import account.user.AccountUser;
import shoppingcart.CartView.ShoppingCart;

import java.util.Scanner;

public class ProductView_ByUser {
    ProductControllerRunByUser productControllerRunByUser = new ProductControllerRunByUser();
    ShoppingCart shoppingCart = new ShoppingCart();
    Scanner scanner = new Scanner(System.in);
    boolean isValidNumber;


    public void showProductList(AccountUser user) {
        int count = -1;
        System.out.println("-------------------------------------------------------");
        System.out.println("----{  ID---NAME---PRICE---QUANTITY---DESCRIPTION  }---");
        System.out.println("-------------------------------------------------------");
        System.out.println("Size: " + productControllerRunByUser.showAll().size());
        System.out.println("Press Enter will show 5 tokens everytime: ");
        for (int i = 0; i < productControllerRunByUser.showAll().size(); i++) {
            count++;
            if (count % 5 == 0) {
                System.out.println("-----------------------------------------------");
                scanner.nextLine();
            }
            System.out.println("         " + productControllerRunByUser.showAll().get(i).getId()
                    + "---" + productControllerRunByUser.showAll().get(i).getName()
                    + "---" + productControllerRunByUser.showAll().get(i).getPrice()
                    + "---" + productControllerRunByUser.showAll().get(i).getQuantity()
                    + "---" + productControllerRunByUser.showAll().get(i).getDescription());

        }
        System.out.println("\\------------------------------------------------------/");
        String typeToExit;
        do {
            System.out.println("Type -quit to back to Menu");
            typeToExit = scanner.next();
            if (typeToExit.equalsIgnoreCase("quit")) {
                new Menu_User(user);
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

    public void searchProductById(AccountUser user) {
        while (true) {
            if (checkList()) {
                System.out.println("Enter token's Id number:");
                int id = checkValidInteger();
                boolean checkIdNumber = false;
                int index = -1;
                for (int i = 0; i < productControllerRunByUser.showAll().size(); i++) {
                    if (id == productControllerRunByUser.showAll().get(i).getId()) {
                        checkIdNumber = true;
                        index = i;
                    }
                }
                if (id == 0) {
                    new Menu_User(user);
                }
                if (checkIdNumber) {
                    Product product = productControllerRunByUser.findProductById(index);
                    System.out.println("---------------------------------------");
                    System.out.println("----{  WE HAVE FOUND THIS FOR YOU  }---");
                    System.out.println("---------------------------------------");
                    System.out.println("Token's id: " + product.getId() + "\n"
                            + "Token's name: " + product.getName() + "\n"
                            + "Token's price: " + product.getPrice() + "\n"
                            + "Token's quantity: " + product.getQuantity() + "\n"
                            + "Token's description: " + product.getDescription());
                    System.out.println("---------------------------------------");
                    String typeToExit;
                    do {
                        System.out.println("Type -buy to move this Token to yours shopping cart");
                        System.out.println("Type -quit to back to Menu");
                        typeToExit = scanner.nextLine();
                        if (typeToExit.equalsIgnoreCase("buy")) {
                            System.out.println("Enter amount you want to buy:");
                            int amount;
                            do {
                                amount = checkValidInteger();
                                int quantity = Math.round(amount / product.getPrice());
                                if ((product.getQuantity() - quantity > 0) && quantity > 0) {
                                    System.out.println("--->> Moved " + quantity + " of " + product.getName() + " to your shopping cart! <<---");
                                    product.setQuantity(product.getQuantity() - quantity);
                                    Product newProduct = new Product(product.getId(), product.getName(), product.getPrice(), quantity, product.getDescription());
                                    shoppingCart.addProductToShoppingCart(newProduct, user.getUserId());
                                    productControllerRunByUser.writeToFile();
                                    new Menu_User(user);
                                } else {
                                    System.err.println("Not able to do that! Enter again:");
                                }
                            } while (true);
                        }
                        if (typeToExit.equalsIgnoreCase("quit")) {
                            new Menu_User(user);
                        }
                    } while (!(typeToExit.equals("quit")) || !typeToExit.equals("buy"));
                } else {
                    System.err.println("Wrong input. Enter Token's id again, of press -0 to back to Menu");
                }
            } else {
                System.err.println("Token List are empty!!!");
                new Menu_User(user);
            }
        }
    }

    public void searchProductByRange(AccountUser user) {
        while (true) {
            if (checkList()) {
                boolean isIn_Range = true;
                System.out.println("Enter lower price");
                int lowerPrice = checkValidInteger();
                System.out.println("Enter higher price, highest price is: " + findHighestPrice());
                int higherPrice = checkValidInteger();
                if (lowerPrice < higherPrice && higherPrice <= findHighestPrice()) {
                    for (int i = 0; i < productControllerRunByUser.showAll().size(); i++) {
                        if (productControllerRunByUser.showAll().get(i).getPrice() >= lowerPrice &&
                                productControllerRunByUser.showAll().get(i).getPrice() <= higherPrice) {
                            Product product = productControllerRunByUser.findProductById(i);
                            System.out.println("  " + product.getId()
                                    + "---" + product.getName()
                                    + "---" + product.getPrice()
                                    + "---" + product.getQuantity()
                                    + "---" + product.getDescription());
                        } else {
                            isIn_Range = false;
                        }
                    }
                    if (!isIn_Range) {
                        System.err.println("No products in-range!");
                    }
                    String typeToExit;
                    do {
                        System.out.println("Type -quit to back to Menu");
                        typeToExit = scanner.next();
                        if (typeToExit.equalsIgnoreCase("quit")) {
                            new Menu_User(user);
                        }
                    } while (!(typeToExit.equals("quit")));
                } else {
                    System.err.println("Wrong input!");
                    System.err.println("Enter again:");
                }
            }
        }
    }

    public int findHighestPrice() {
        int highestPrice = 0;
        for (int i = 0; i < productControllerRunByUser.showAll().size(); i++) {
            if (productControllerRunByUser.showAll().get(i).getPrice() > highestPrice) {
                highestPrice = productControllerRunByUser.showAll().get(i).getPrice();
            }
        }
        return highestPrice;
    }

    public int findCheapestPrice() {
        int cheapestPrice = productControllerRunByUser.showAll().get(0).getPrice();
        for (int i = 0; i < productControllerRunByUser.showAll().size(); i++) {
            if (productControllerRunByUser.showAll().get(i).getPrice() < cheapestPrice) {
                cheapestPrice = productControllerRunByUser.showAll().get(i).getPrice();
            }
        }
        return cheapestPrice;
    }

    public void showTheMostExpensiveProduct(AccountUser user) {
        if (checkList()) {
            int index = -1;
            int highestPrice = findHighestPrice();
            System.out.println("------------------------------------------");
            System.out.println("----{  ARE YOU LOOKING FOR THIS ONE?  }---");
            System.out.println("------------------------------------------");
            for (int i = 0; i < productControllerRunByUser.showAll().size(); i++) {
                if (highestPrice == productControllerRunByUser.showAll().get(i).getPrice()) {
                    index = i;
                    Product product = productControllerRunByUser.findProductById(index);

                    System.out.println("  >>>> " + product.getId()
                            + "---" + product.getName()
                            + "---" + product.getPrice()
                            + "---" + product.getQuantity()
                            + "---" + product.getDescription());
                }
            }
            System.out.println("------------------------------------------");
            String typeToExit;
            do {
                System.out.println("Type -quit to back to Menu");
                typeToExit = scanner.next();
                if (typeToExit.equalsIgnoreCase("quit")) {
                    new Menu_User(user);
                }
            } while (!(typeToExit.equals("quit")));
        } else {
            System.err.println("Token List are empty!!!");
            new Menu_User(user);
        }
    }

    public void showTheCheapestProduct(AccountUser user) {
        if (checkList()) {
            int index = -1;
            int cheapestPrice = findCheapestPrice();
            System.out.println("------------------------------------------");
            System.out.println("----{  ARE YOU LOOKING FOR THIS ONE?  }---");
            System.out.println("------------------------------------------");
            for (int i = 0; i < productControllerRunByUser.showAll().size(); i++) {
                if (cheapestPrice == productControllerRunByUser.showAll().get(i).getPrice()) {
                    index = i;
                    Product product = productControllerRunByUser.findProductById(index);

                    System.out.println("  " + product.getId()
                            + "---" + product.getName()
                            + "---" + product.getPrice()
                            + "---" + product.getQuantity()
                            + "---" + product.getDescription());
                }
            }
            System.out.println("------------------------------------------");
            String typeToExit;
            do {
                System.out.println("Type -quit to back to Menu");
                typeToExit = scanner.next();
                if (typeToExit.equalsIgnoreCase("quit")) {
                    new Menu_User(user);
                }
            } while (!(typeToExit.equals("quit")));
        } else {
            System.err.println("Token List are empty now!!!");
            new Menu_User(user);
        }
    }

    public boolean checkList() {
        return productControllerRunByUser.showAll().size() > 0;
    }
}
