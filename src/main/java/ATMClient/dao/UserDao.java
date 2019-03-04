package ATMClient.dao;

import ATMClient.model.accountType.Account;

public interface UserDao {
    Account find(String username, String password);

    Account getUser(String username);

    void register(Account user);

    boolean isRegister(String username);

    void upDateBalance(String username, double amount, String accountType);

    void upDateCeiling(String username, double amount, String accountType);

    void upDateLoan(String username, double amount, String accountType);

}
