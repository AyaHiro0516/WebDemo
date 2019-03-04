package ATMClient.dao.impl;

import ATMClient.dao.UserDao;
import ATMClient.model.accountType.*;
import ATMClient.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;


public class UserImplDataBase implements UserDao {
    private String[] acType={"Credit", "LoanCredit", "Saving", "LoanSaving"};
    @Override
    public Account find(String username, String password) {

        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());

        try {
            Account user=null;
            for(String type:acType){
                String sql = "SELECT * FROM "+type+"Account WHERE username=? AND password=?";
                Object[] params={username,password};
                switch (type){
                    case "Credit":
                        user=queryRunner.query(sql, new BeanHandler<>(CreditAccount.class), params);
                        if (user!=null) user.setAccountType("CreditAccount");
                        break;
                    case "LoanCredit":
                        user=queryRunner.query(sql, new BeanHandler<>(LoanCreditAccount.class), params);
                        if (user!=null) user.setAccountType("LoanCreditAccount");
                        break;
                    case "Saving":
                        user=queryRunner.query(sql, new BeanHandler<>(SavingAccount.class), params);
                        if (user!=null) user.setAccountType("SavingAccount");
                        break;
                    case "LoanSaving":
                        user=queryRunner.query(sql, new BeanHandler<>(LoanSavingAccount.class), params);
                        if (user!=null) user.setAccountType("LoanSavingAccount");
                        break;
                }
                if (user!=null)
                    break;
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("登陆失败了！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account getUser(String username) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());

        try {
            Account user=null;
            for(String type:acType){
                String sql = "SELECT * FROM "+type+"Account WHERE username=?";
                Object[] params={username};
                switch (type){
                    case "Credit":
                        user=queryRunner.query(sql, new BeanHandler<>(CreditAccount.class), params);
                        if (user!=null) user.setAccountType("CreditAccount");
                        break;
                    case "LoanCredit":
                        user=queryRunner.query(sql, new BeanHandler<>(LoanCreditAccount.class), params);
                        if (user!=null) user.setAccountType("LoanCreditAccount");
                        break;
                    case "Saving":
                        user=queryRunner.query(sql, new BeanHandler<>(SavingAccount.class), params);
                        if (user!=null) user.setAccountType("SavingAccount");
                        break;
                    case "LoanSaving":
                        user=queryRunner.query(sql, new BeanHandler<>(LoanSavingAccount.class), params);
                        if (user!=null) user.setAccountType("LoanSavingAccount");
                        break;
                }
                if (user!=null)
                    break;
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("登陆失败了！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void register(Account user) {
        String accountType=user.getAccountType();
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "INSERT INTO "+accountType+" (userId, passWord, personId, userName, email, address, balance) VALUES (?,?,?,?,?,?,?)";
        long userId=user.getUserId();
        String passWord=user.getPassWord();
        String personId=user.getPersonId();
        String userName=user.getUserName();
        String email=user.getEmail();
        String address=user.getAddress();
        try{
            queryRunner.update(sql, new Object[]{userId,passWord,personId,userName,email, address, 0});
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("注册失败了");
        }
    }

    @Override
    public boolean isRegister(String username) {

        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        boolean flag=false;
        try {
            for(String type:acType){
                String sql="SELECT COUNT(*) FROM "+type+"Account WHERE userName=?";
                long count=queryRunner.query(sql, new ScalarHandler<Long>(), new Object[]{username});
                if (count==1){
                    flag=true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void upDateBalance(String username, double amount, String accountType) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql="UPDATE "+accountType+" SET balance=? WHERE userName=?";
        Object[] params={amount,username};
        try{
            queryRunner.update(sql, params);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void upDateCeiling(String username, double amount, String accountType) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql="UPDATE "+accountType+" SET ceiling=? WHERE userName=?";
        Object[] params={amount,username};
        try{
            queryRunner.update(sql, params);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void upDateLoan(String username, double amount, String accountType) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql="UPDATE "+accountType+" SET loan=? WHERE userName=?";
        Object[] params={amount,username};
        try{
            queryRunner.update(sql, params);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
