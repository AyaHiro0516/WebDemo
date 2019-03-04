package ATMClient.controller;

import ATMClient.model.accountType.*;
import ATMClient.service.HostHolder;
import ATMClient.service.ServiceBusiness;
import ATMClient.service.impl.BankService;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.TreeMap;

@WebServlet(name="LoginCtr", urlPatterns = {"/LoginCtr"})
public class LoginCtr extends HttpServlet {
    private static HostHolder host;
    static {
        host=new HostHolder();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取提交过来的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try{
            ServiceBusiness serviceBusiness =new BankService();
            Account user= serviceBusiness.login(username,password);
            if (user == null) {
                req.setAttribute("message", "用户名或密码是错的");
                req.getRequestDispatcher("/views/ATMClient/loginpage.jsp").forward(req, resp);
            } else {
                host.setUser(user);
                HttpSession session=req.getSession();
                session.setAttribute("host", host.getUser());
                req.setAttribute("message","登陆成功");
                req.getRequestDispatcher("/views/ATMClient/business.jsp").forward(req, resp);
            }
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message","登陆失败咯");
            req.getRequestDispatcher("/views/ATMClient/loginpage.jsp").forward(req, resp);
        }

    }
}