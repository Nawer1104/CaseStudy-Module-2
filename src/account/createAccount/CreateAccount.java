package account.createAccount;
import account.user.AccountUser;
import account.user.AccountUserManagement;
import mainmenu.MainMenu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CreateAccount {
    public static Scanner scanner = new Scanner(System.in);
    public static AccountUserManagement accountUserManagement = new AccountUserManagement();
    boolean isValidNumber;
    public static Pattern patternPassword = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$");
    public static Pattern patternId = Pattern.compile("^[a-zA-Z0-9]{6,12}$");


    public static void createAcc() throws InputMismatchException, IOException {
        while (true) {
            System.out.println("----------------------------------------------------");
            System.out.println("----|            REGISTRATION MENU             |----");
            System.out.println("----------------------------------------------------");
            System.out.println("Enter yours user id: ");
            String id = checkValid();
            System.out.println("Enter yours password: ");
            String password = scanner.nextLine();
            while(!patternPassword.matcher(password).matches()){
                System.err.println("Password At least 6 characters");
                System.err.println("A mixture of both uppercase and lowercase letters.");
                System.err.println("A mixture of letters and numbers.");
                System.err.println("Inclusion of at least one special character, e.g., ! @ # ? ]");
                System.err.println("Enter again:");
                password = scanner.nextLine();
            }
            System.out.println("Re-enter yours password: ");
            String secondPassword = scanner.nextLine();
            if (password.equals(secondPassword)) {
                if (checkIdUser(id)) {
                    FileWriter fw = new FileWriter(id + ".txt");
                    fw.write("");
                    fw.close();FileWriter wl = new FileWriter(id + "wallet.txt");
                    wl.write("");
                    wl.close();
                    accountUserManagement.setListAccountUser(id, password);
                    System.out.println("Successfully create new account");
                    String typeToExit;
                    do {
                        System.out.println("Type -quit to back to Main Menu");
                        typeToExit = scanner.next();
                        if (typeToExit.equalsIgnoreCase("quit")) {
                            new MainMenu();
                        }
                    } while (!(typeToExit.equals("quit")));
                } else {
                    System.err.println("         -Account id already exists!!-");
                    System.err.println("          -   Back To Main Menu  -");
                    new MainMenu();
                }
            } else {
                System.err.println("Your password doesn't match each other, enter again:");
            }
        }
    }

    public static String checkValid() {
        String value;
        boolean check = false;
        do {
            value = scanner.nextLine();
            if (patternId.matcher(value).matches()) {
                check = true;
            } else {
                System.err.println("From 6 to 12 characters required!");
                System.err.println("Special character are not allow!");
                System.err.println("Enter again:");
            }
        } while (!check);
        return value;
    }

    public static boolean checkIdUser(String id) {
        if (accountUserManagement.getAccountUserList() != null) {
            for (AccountUser acc : accountUserManagement.getAccountUserList()
            ) {
                boolean checkIdUser = id.equals(acc.getUserId());
                if (checkIdUser) {
                    return false;
                }
            }
        }
        return true;
    }
}

