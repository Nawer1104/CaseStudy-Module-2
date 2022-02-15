package productlist_runbyadmin.view;

import account.admin.AccountAdmin;
import mainmenu.MainMenu;

import java.io.IOException;
import java.util.Scanner;

public class Menu_Admin {
    Scanner scanner = new Scanner(System.in);
    ProductView_ByAdmin productViewByAdmin = new ProductView_ByAdmin();
    boolean isValidNumber;


    public Menu_Admin() {

        do {
            isValidNumber = true;
            System.out.println("------------------------------------------------------------");
            System.out.println("|---      BINANCE SMART CHAIN       --- RUNNING BY ADMIN---|");
            System.out.println("------------------------------------------------------------");
            System.out.println("|1. Show token list                                        |");
            System.out.println("|2. Add new token to the list                              |");
            System.out.println("|3. Search token by Id                                     |");
            System.out.println("|4. Edit token detail by Id                                |");
            System.out.println("|5. Remove token by Id                                     |");
            System.out.println("|6. Sort token list                                        |");
            System.out.println("|7. Show the most expensive token                          |");
            System.out.println("|8. Clear token File List                                  |");
            System.out.println("|9. Clear User File List                                   |");
            System.out.println("|10. Show User File List                                   |");
            System.out.println("|0. Back to Main Menu                                      |");
            System.out.println("\\__________________________________________________________/");
            System.out.print("Make your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productViewByAdmin.showProductList();
                        break;
                    case 2:
                        productViewByAdmin.addNewProduct();
                        break;
                    case 3:
                        productViewByAdmin.searchProductById();
                        break;
                    case 4:
                        productViewByAdmin.editProductById();
                        break;
                    case 5:
                        productViewByAdmin.removeProductById();
                        break;
                    case 6:
                        productViewByAdmin.sortProductListByPrice();
                        break;
                    case 7:
                        productViewByAdmin.showTheMostExpensiveProduct();
                        break;
                    case 8:
                        productViewByAdmin.clearFile();
                        break;
                    case 9:
                        productViewByAdmin.clearUserFile();
                        break;
                    case 10:
                        productViewByAdmin.showUserFileList();
                        break;
                    case 0:
                        new MainMenu();
                        break;
                    default:
                        System.err.println("Input number incorrect!!");
                        new Menu_Admin();
                }
            } catch (NumberFormatException | IOException e) {
                System.err.println("Input number incorrect!!");
                isValidNumber = false;
            }
        } while (!isValidNumber);
    }
}

