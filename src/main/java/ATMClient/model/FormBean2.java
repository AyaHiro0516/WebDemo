package ATMClient.model;

import ATMClient.dao.DaoFactory;
import ATMClient.dao.UserDao;

import java.util.HashMap;

public class FormBean2 {
    private String amount;
    private String fromName;
    private String toName;
    private String mode;
    private UserDao userDao= DaoFactory.getUserDao("UserImplDataBase");
    private HashMap<String, String> error = new HashMap<>();

    public FormBean2() {
    }

    public FormBean2(String amount, String fromName, String toName, String mode) {
        this.amount = amount;
        this.fromName = fromName;
        this.toName = toName;
        this.mode = mode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public HashMap<String, String> getError() {
        return error;
    }

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }
    public boolean validate() {
        boolean flag=true;
        if (this.amount == null || this.amount.trim().equals("")) {
            error.put("amount", "数额不能为空");
            flag=false;

        } else {
            if (!this.amount.matches("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$")) {
                error.put("amount", "数额必须是正浮点数");
                flag=false;
            }
        }

        if (this.mode.equals("transfer")){
            if (this.toName == null || this.toName.trim().equals("")) {
                error.put("toName", "用户名不能为空，并且要是3-8的字符");
                flag=false;

            } else if (toName.equals(fromName)){
                error.put("toName", "不能转给自己");
                flag=false;
            }else {
                if (!userDao.isRegister(toName)) {
                    error.put("toName", "该用户不存在");
                    flag=false;
                }
            }
        }
        return flag;
    }
}
