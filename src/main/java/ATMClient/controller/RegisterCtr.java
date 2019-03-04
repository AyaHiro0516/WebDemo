package ATMClient.controller;

import ATMClient.model.FormBean;
import ATMClient.model.accountType.*;
import ATMClient.model.exceptionType.RegisterException;
import ATMClient.service.ServiceBusiness;
import ATMClient.service.impl.BankService;
import ATMClient.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name="RegisterCtr", urlPatterns = {"/RegisterCtr"})
public class RegisterCtr extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormBean formBean = WebUtils.requestToBean(req, FormBean.class);
        //验证表单的数据是否合法，如果不合法就跳转回去注册的页面
        if(!formBean.validate()){
            System.out.println(formBean.getError().toString());
            req.setAttribute("formbean", formBean);
            req.getRequestDispatcher("/views/ATMClient/registerpage.jsp").forward(req, resp);
            return;
        }
        try{
            Account user;
            String accountType=req.getParameter("accountType");
            System.out.println(accountType);
            switch (accountType) {
                case "SavingAccount":
                    user=WebUtils.requestToBean(req, SavingAccount.class).setAccountType("SavingAccount");
                break;
                case "CreditAccount":
                    user=WebUtils.requestToBean(req, CreditAccount.class).setAccountType("CreditAccount");
                break;
                case "LoanSavingAccount":
                    user=WebUtils.requestToBean(req, LoanSavingAccount.class).setAccountType("LoanSavingAccount");
                break;
                case "LoanCreditAccount":
                    user=WebUtils.requestToBean(req, LoanCreditAccount.class).setAccountType("LoanCreditAccount");
                break;
                    default: throw new RegisterException("开户失败，未知账户类型。");
            }
            user.setUserId(WebUtils.makeId());
            System.out.println(user.toString());

            ServiceBusiness serviceBusiness = new BankService();
            serviceBusiness.register(user);

            req.setAttribute("message","注册成功！");
        }catch (Exception e){
            req.setAttribute("message","注册失败！");
            e.printStackTrace();
        }
        req.getRequestDispatcher("/views/ATMClient/registerpage.jsp").forward(req, resp);
    }
}