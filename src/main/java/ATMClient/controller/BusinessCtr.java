package ATMClient.controller;

import ATMClient.model.FormBean2;
import ATMClient.model.accountType.Account;
import ATMClient.service.HostHolder;
import ATMClient.service.ServiceBusiness;
import ATMClient.service.impl.BankService;
import ATMClient.utils.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="BusinessCtr", urlPatterns = {"/BusinessCtr"})
public class BusinessCtr extends HttpServlet {
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
        FormBean2 formBean2 = WebUtils.requestToBean(req, FormBean2.class);

        HttpSession session=req.getSession();
        host.setUser((Account)session.getAttribute("host"));
        Account user=host.getUser();
        System.out.println(user);

        String userName = user.getUserName();
        String toName = req.getParameter("toName");

        formBean2.setFromName(userName);
        formBean2.setToName(toName);

        if (!formBean2.validate()) {
            System.out.println(formBean2.getError().toString());
            req.setAttribute("formbean2", formBean2);
            req.getRequestDispatcher("/views/ATMClient/business.jsp").forward(req, resp);
            return;
        }
        try {
            Double amount = new Double(req.getParameter("amount"));
            System.out.println(userName);
            System.out.println(amount);

            ServiceBusiness serviceBusiness = new BankService();
            String mode = req.getParameter("mode");
            System.out.println(mode);
            switch (mode) {
                case "deposit":
                    serviceBusiness.deposit(userName, amount);
                    break;
                case "withdraw":
                    serviceBusiness.withdraw(userName, amount);
                    break;
                case "requestLoan":
                    serviceBusiness.requestLoan(userName, amount);
                    break;
                case "payLoan":
                    serviceBusiness.payLoan(userName, amount);
                    break;
                case "transfer":
                    serviceBusiness.transfer(userName, toName, amount);
                    break;
            }
            host.setUser(serviceBusiness.getUser(userName));
            req.setAttribute("message", "操作成功！");
            session.setAttribute("host", host.getUser());
            req.getRequestDispatcher("/views/ATMClient/business.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("message", "操作失败！");
            req.getRequestDispatcher("/views/ATMClient/business.jsp").forward(req, resp);
            e.printStackTrace();
        }

    }
}
