package ATMClient.service;

import ATMClient.model.accountType.Account;
import ATMClient.model.exceptionType.ATMException;

public interface ServiceBusiness {
    void register(Account user);

    Account login(String username, String password);

    Account getUser(String username);

    void deposit(String username, double amount);

    void withdraw(String username, double amount);

    void requestLoan(String username, double amount);

    void payLoan(String username, double amount);

    void transfer(String fromname, String toname, double amount) throws ATMException;
}
