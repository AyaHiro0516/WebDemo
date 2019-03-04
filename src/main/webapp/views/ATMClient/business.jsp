<%@ page import="ATMClient.model.accountType.Account" %>
<%@ page import="ATMClient.model.accountType.CreditAccount" %>
<%@ page import="ATMClient.model.accountType.LoanCreditAccount" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2019/2/19
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>业务页面</title>
</head>
<body>
<h1>欢迎来到业务页面！</h1>
<form action="${pageContext.request.contextPath}/BusinessCtr" method="post">
    <table>
        <tr>
            <td>账户：</td>
            <td>${host.userId}</td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td>${host.userName}</td>
        </tr>
        <tr>
            <td>余额：</td>
            <td>${host.balance}</td>
        </tr>
        <tr>
            <td>信用额度：</td>
            <td>
               ${host.accountType=="CreditAccount" || host.accountType=="LoanCreditAccount" ?
                       host.ceiling : "功能受限"}
            </td>
        </tr>
        <tr>
            <td>贷款额：</td>
            <td>
                ${host.accountType=="LoanCreditAccount" || host.accountType=="LoanSavingAccount" ?
                        host.loan : "功能受限"}
            </td>
        </tr>
        <tr>
            <td>
                <select name="mode">
                    <option value="deposit">存款</option>
                    <option value="withdraw">取款</option>
                    <option value="requestLoan">借贷</option>
                    <option value="payLoan">还贷</option>
                    <option value="transfer">转账</option>
                </select>
            </td>
            <td>
                <input type="text" name="amount" value="">
            </td>
            <td>${formbean2.error.amount}</td>
        </tr>
        <tr>
            <td>接收账户：</td>
            <td>
                <input type="text" name="toName" value="">
            </td>
            <td>${formbean2.error.toName}</td>
        </tr>
        <tr>
            <td>
                <button type="submit">提交</button>
            </td>
            <td>
                <button type="reset">重置！</button>
            </td>
        </tr>
    </table>
</form>
${message}
</body>
</html>
