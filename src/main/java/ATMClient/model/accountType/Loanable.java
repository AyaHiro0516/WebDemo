package ATMClient.model.accountType;

import ATMClient.model.exceptionType.ATMException;

public interface Loanable {
    void requestLoan(double money);
    void payLoan(double money) throws ATMException;
    double getLoan();
}
