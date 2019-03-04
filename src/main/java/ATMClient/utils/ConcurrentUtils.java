package ATMClient.utils;

import ATMClient.model.accountType.Account;

public class ConcurrentUtils {
    private static ThreadLocal<Account> host = new ThreadLocal<>();
    public static Account getHost(){
        return host.get();
    }
    public static void setHost(Account user){
        host.set(user);
    }

}
