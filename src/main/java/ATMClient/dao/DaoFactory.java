package ATMClient.dao;

import ATMClient.dao.impl.UserImplDataBase;


public class DaoFactory {
    public static UserDao getUserDao(String type){
        switch (type){
            case "UserImplDataBase":
                return new UserImplDataBase();
            default:
                return null;
        }
    }
}
