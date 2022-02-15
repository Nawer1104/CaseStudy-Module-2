package account.user;

import java.io.Serializable;

public class AccountUser implements Serializable { 
    private String userId;
    private String userPassword;
    private String name;
    private int age;
    private String country;
    private int accountSummary;

    public AccountUser(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }


    public AccountUser(String name, int age, String country, int accountSummary) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.accountSummary = accountSummary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAccountSummary() {
        return accountSummary;
    }

    public void setAccountSummary(int accountSummary) {
        this.accountSummary = accountSummary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
