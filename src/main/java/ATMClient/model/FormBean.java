package ATMClient.model;


import ATMClient.dao.DaoFactory;
import ATMClient.dao.UserDao;

import java.util.HashMap;

public class FormBean {
    private String userName;
    private String passWord;
    private String passWord2;
    private String personId;
    private String email;
    private String address;
    private UserDao userDao= DaoFactory.getUserDao("UserImplDataBase");
    private HashMap<String, String> error = new HashMap<>();

    public FormBean() {

    }

    public FormBean(String userName, String passWord, String passWord2, String personId, String email, String address) {
        this.userName = userName;
        this.passWord = passWord;
        this.passWord2 = passWord2;
        this.personId = personId;
        this.email = email;
        this.address = address;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String password) {
        this.passWord = password;
    }

    public String getPassWord2() {
        return passWord2;
    }

    public void setPassWord2(String password2) {
        this.passWord2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, String> getError() {
        return error;
    }

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }

    /*用于判断表单提交过来的数据是否合法*/
    public boolean validate() {
        //用户名不能为空，并且要是3-8的字符 abcdABcd
        boolean flag=true;
        if (this.userName == null || this.userName.trim().equals("")) {
            error.put("userName", "用户名不能为空，并且要是3-8的字符");
            flag=false;

        } else {
            if (!this.userName.matches("[a-zA-Z]{3,8}")) {
                error.put("userName", "用户名不能为空，并且要是3-8的字符");
                flag=false;
            }
        }

        //密码不能为空，并且要是3-8的数字
        if (this.passWord == null || this.passWord.trim().equals("")) {
            error.put("passWord", "密码不能为空，并且要是3-8的数字");
            flag=false;
        } else {
            if (!this.passWord.matches("\\d{3,8}")) {
                error.put("passWord", "密码不能为空，并且要是3-8的数字");
                flag=false;
            }
        }

        //两次密码要一致
        if (this.passWord2 != null && !this.passWord2.trim().equals("")) {
            if (!this.passWord2.equals(this.passWord)) {
                error.put("passWord2", "两次密码要一致");
                flag=false;
            }
        }

        //邮箱可以为空，如果为空就必须合法
        if (this.email != null && !this.email.trim().equals("")) {
            if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
                error.put("email", "邮箱不合法！");
                flag=false;
            }
        }
        if (userDao.isRegister(userName)){
            error.put("isRegister", "该用户名已被注册！");
            flag=false;
        }
        //如果上面都没有执行，那么就是合法的了，返回true
        return flag;
    }

}
