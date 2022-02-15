package account.user;

import _ReadAndWriteFile.IOFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountUserManagement {
    private final List<AccountUser> accountUserList = new ArrayList<>();
    private final IOFile<AccountUser> ioFile = new IOFile<>();
    private final String PATHNAME_OF_ACCOUNTUSER = "C:\\Users\\hihih\\IdeaProjects\\CaseStudy2\\";

    public AccountUserManagement() {
    }

    public List<AccountUser> getAccountUserList() {
        return ioFile.readFileData(PATHNAME_OF_ACCOUNTUSER + "accountuser.txt");
    }

    public boolean checkFile() {
        List<AccountUser> accountUserList = getAccountUserList();
        return accountUserList == null;
    }

    public void setNewListAccountUser(AccountUser user, int index) {
        List<AccountUser> accountUsers = getAccountUserList();
        accountUsers.set(index, user);
        ioFile.writerFileData(accountUsers, PATHNAME_OF_ACCOUNTUSER + "accountuser.txt");
    }

    public void setListAccountUser(String id, String password) {
        List<AccountUser> accountUsers;
        if (checkFile()) {
            accountUsers = accountUserList;
        } else {
            accountUsers = getAccountUserList();
        }
        accountUsers.add(new AccountUser(id, password));
        ioFile.writerFileData(accountUsers, PATHNAME_OF_ACCOUNTUSER + "accountuser.txt");
    }

    public static void main(String[] args) {
        AccountUserManagement accountUserManagement = new AccountUserManagement();
        System.out.println(accountUserManagement.checkFile());
        System.out.println(accountUserManagement.getAccountUserList().size());
        for (AccountUser x :  accountUserManagement.getAccountUserList()
             ) {
            System.out.println(x.getUserId());
            System.out.println(x.getUserPassword());
        }
    }
}
