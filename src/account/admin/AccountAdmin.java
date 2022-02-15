package account.admin;

import java.util.ArrayList;
import java.util.List;

public class AccountAdmin {
    private String adminId;
    private String adminPass;
    private final ArrayList<AccountAdmin> accountAccountAdminList = new ArrayList<>();

    public AccountAdmin(String adminId, String adminPass) {
        this.adminId = adminId;
        this.adminPass = adminPass;

    }

    public AccountAdmin() {
        accountAccountAdminList.add(new AccountAdmin("admin1", "123"));
        accountAccountAdminList.add(new AccountAdmin("admin2", "123"));
        accountAccountAdminList.add(new AccountAdmin("admin3", "123"));
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public List<AccountAdmin> getAdminList() {
        return accountAccountAdminList;
    }
}
