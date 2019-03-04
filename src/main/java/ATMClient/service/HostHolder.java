package ATMClient.service;

import ATMClient.model.accountType.Account;
import ATMClient.utils.ConcurrentUtils;

public class HostHolder {
    public  Account getUser() {
        return ConcurrentUtils.getHost();
    }
    public  void setUser(Account user) {
        ConcurrentUtils.setHost(user);
    }
}
