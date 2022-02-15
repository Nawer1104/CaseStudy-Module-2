package account.createAccount;

import account.productList_runbyuser.view.Menu_User;
import account.user.AccountUser;
import account.user.AccountUserManagement;
import productlist_runbyadmin.view.Menu_Admin;

import java.util.Scanner;

public class UserPersonalInformation {
    public static Scanner scanner = new Scanner(System.in);
    public static AccountUserManagement accountUserManagement = new AccountUserManagement();
    boolean isValidNumber;

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

    public void userDetail(AccountUser user) {

        do {
            isValidNumber = true;
            System.out.println("----------------------------------------------------");
            System.out.println("----|           PERSONAL INFORMATION           |----");
            System.out.println("----------------------------------------------------");
            System.out.println("|                    {  [*]  }                     |");
            System.out.println("-----{User's name : " + user.getName() + "                 }-----");
            System.out.println("-----{User's country : " + user.getCountry() + "                 }-----");
            System.out.println("-----{User's age : " + user.getAge() + "                 }-----");
            System.out.println("-----{Balance available : "+ user.getAccountSummary() + "                 }-----");
            System.out.println("\\---------------------------------------------------/");
            System.out.println("| Choose 1 to edit your profile.                       ");
            System.out.println("| Choose 2 to deposit your current balance.         ");
            System.out.println("| Choose 3 to back to Menu.                         ");
            System.out.println("----------------------------------------------------");
            System.out.print("Make your choice: ");
            try {
                int choice = checkValidInteger();
                switch (choice) {
                    case 1:
                        setUserProfile(user);
                        break;
                    case 2:
                        depositBalance(user);
                        break;
                    case 3:
                        new Menu_User(user);
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

    public void setUserProfile(AccountUser user) {
        System.out.println("----------------------------------------------------");
        System.out.println("----|      SET PERSONAL INFORMATION FORM       |----");
        System.out.println("----------------------------------------------------");
        System.out.println("What yours name?: ");
        String name = scanner.nextLine();
        System.out.println("How old are you?: ");
        int age;
        do {
            age = checkValidInteger();
            if (age <= 0) {
                System.err.println("You weren't born yet??");
                System.err.println("Re-enter again: ");
                isValidNumber = false;
            } else {
                isValidNumber = true;
            }
        } while (!isValidNumber);
        System.out.println("Where are you come from?: ");
        String country = scanner.nextLine();
        user.setName(name);
        user.setCountry(country);
        user.setAge(age);
        System.out.println("Edit Success:");
        accountUserManagement.setNewListAccountUser(user, findIndexOfUser(user));
        String typeToExit;
        do {
            System.out.println("Type -quit to back to Menu");
            typeToExit = scanner.next();
            if (typeToExit.equalsIgnoreCase("quit")) {
                new Menu_User(user);
            }
        } while (!(typeToExit.equals("quit")));
    }

    public int findIndexOfUser(AccountUser user) {
        for (int i = 0; i < accountUserManagement.getAccountUserList().size(); i++) {
            if (user.getUserId().equals(accountUserManagement.getAccountUserList().get(i).getUserId())){
                return i;
            }
        }
       return -1;
    }

    public void depositBalance(AccountUser user) {
        System.out.println("--------------------------------------------------");
        System.out.println("----|      CHARGE YOUR ACCOUNT BALANCE       |----");
        System.out.println("--------------------------------------------------");
        System.out.println("How manny you want to deposit on your account balance?: ");
        int charge = checkValidInteger();
        user.setAccountSummary(user.getAccountSummary() + charge);
        accountUserManagement.setNewListAccountUser(user, findIndexOfUser(user));
        System.out.println("Charge Success:");
        String typeToExit;
        do {
            System.out.println("Type -quit to back to Menu");
            typeToExit = scanner.next();
            if (typeToExit.equalsIgnoreCase("quit")) {
                new Menu_User(user);
            }
        } while (!(typeToExit.equals("quit")));
    }
}
