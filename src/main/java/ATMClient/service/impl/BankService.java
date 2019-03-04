package ATMClient.service.impl;

import ATMClient.dao.DaoFactory;
import ATMClient.dao.UserDao;
import ATMClient.model.accountType.Account;
import ATMClient.model.accountType.LoanCreditAccount;
import ATMClient.model.accountType.LoanSavingAccount;
import ATMClient.model.exceptionType.ATMException;
import ATMClient.model.exceptionType.BalanceNotEnoughException;
import ATMClient.service.ServiceBusiness;
import org.junit.Test;

public class BankService implements ServiceBusiness {

    //Service层调用Dao层的方法，直接在类中创建Dao层的对象
    private UserDao userDao= DaoFactory.getUserDao("UserImplDataBase");
    @Override
    public void register(Account user) {
        userDao.register(user);
    }

    @Override
    public Account login(String username, String password) {
        return userDao.find(username, password);
    }

    @Override
    public Account getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public void deposit(String username, double amount) {
        Account user=userDao.getUser(username);
        user.deposit(amount);
        System.out.println(user);
        userDao.upDateBalance(username, user.getBalance(), user.getAccountType());
    }

    @Override
    public void withdraw(String username, double amount) {
        Account user=userDao.getUser(username);
        try {
            user.withdraw(amount);
            System.out.println(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        userDao.upDateBalance(username, user.getBalance(), user.getAccountType());
    }

    @Override
    public void requestLoan(String username, double amount) {
        Account user=userDao.getUser(username);
        try {
            if (user instanceof LoanCreditAccount){
                ((LoanCreditAccount) user).requestLoan(amount);
                userDao.upDateLoan(username, ((LoanCreditAccount) user).getLoan(), user.getAccountType());
            }
            if (user instanceof LoanSavingAccount){
                ((LoanSavingAccount) user).requestLoan(amount);
                userDao.upDateLoan(username, ((LoanSavingAccount) user).getLoan(), user.getAccountType());
            }
            userDao.upDateBalance(username, user.getBalance(), user.getAccountType());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(user);

    }

    @Override
    public void payLoan(String username, double amount) {
        Account user=userDao.getUser(username);
        try {
            if (user instanceof LoanCreditAccount){
                ((LoanCreditAccount) user).payLoan(amount);
                userDao.upDateLoan(username, ((LoanCreditAccount) user).getLoan(), user.getAccountType());
            }
            if (user instanceof LoanSavingAccount){
                ((LoanSavingAccount) user).payLoan(amount);
                userDao.upDateLoan(username, ((LoanSavingAccount) user).getLoan(), user.getAccountType());
            }
            userDao.upDateBalance(username, user.getBalance(), user.getAccountType());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(user);
    }

    @Override
    public void transfer(String fromname, String toname, double amount) throws ATMException{
        Account fromUser=userDao.getUser(fromname);
        Account toUser=userDao.getUser(toname);
        if (fromUser.getBalance()>=amount){
            fromUser.setBalance(fromUser.getBalance()-amount);
            toUser.setBalance(toUser.getBalance()+amount);
            userDao.upDateBalance(fromname, fromUser.getBalance(), fromUser.getAccountType());
            userDao.upDateBalance(toname, toUser.getBalance(), toUser.getAccountType());
        }else {
            throw new BalanceNotEnoughException("转账者余额不足。");
        }
    }
}
