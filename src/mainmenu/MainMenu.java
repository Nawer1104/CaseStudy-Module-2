package mainmenu;

import account.createAccount.CreateAccount;
import account.login.Login;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    Login login = new Login();
    CreateAccount createAccount = new CreateAccount();

    public MainMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("|---WELCOME TO BINANCE CRYPTOCURRENCY EXCHANGE---|");
            System.out.println("|                    (๑>◡<๑)                     |");
            System.out.println("|               [Written by Nawer]               |");
            System.out.println("|                                                |");
            System.out.println("|        Already have account? Type -yes!        |");
            System.out.println("|           Not have yet? Type -create!          |");
            System.out.println("\\________________________________________________/");
            System.out.print("Make your choice: ");

            try {
                String choice = scanner.nextLine();
                System.out.println(".............");
                switch (choice) {
                    case "yes": login.loginSystems();
                        break;
                    case "create": createAccount.createAcc();
                        break;
                    default:
                        System.err.println("Wrong input!");
                }
            } catch (InputMismatchException | IOException e) {
                System.err.println("Input number incorrect!!");
            }
        } while (true);
    }

    public static void main(String[] args) {
       new MainMenu();

    }
}

