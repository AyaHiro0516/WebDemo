<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2019/2/19
  Time: 2:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<h1>欢迎来到注册页面！</h1>

<%--提交给处理注册的处理Servlet--%>

<form method="post" action="${pageContext.request.contextPath}/RegisterCtr">

    <table>
        <%--对于id来讲，是服务器分配的！不需要用户自己输入--%>
        <tr>
            <td>账户类型</td>
            <td>
                <select name="accountType">
                    <option value="CreditAccount">CreditAccount</option>
                    <option value="LoanCreditAccount">LoanCreditAccount</option>
                    <option value="SavingAccount">SavingAccount</option>
                    <option value="LoanSavingAccount">LoanSavingAccount</option>
                </select>
            </td>
        </tr><tr>
            <td>用户名</td>
            <td>
                <input type="text " name="userName" value="${formbean.userName}">${formbean.error.userName} ${formbean.error.isRegister}
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="text" name="passWord" value="${formbean.passWord}">${formbean.error.passWord}
            </td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td>
                <input type="text" name="passWord2" value="${formbean.passWord2}">${formbean.error.passWord2}
            </td>
        </tr>
            <tr>
                <td>身份证号</td>
                <td>
                    <input type="text " name="personId" value="${formbean.personId}"> ${formbean.error.personId}
                </td>
            </tr>
        <tr>
            <td>E-mail</td>
            <td>
                <input type="text" name="email" value="${formbean.email}">${formbean.error.email}
            </td>
        </tr>
            <tr>
                <td>详细地址</td>
                <td>
                    <input type="text" name="address" value="${formbean.address}">
                </td>
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
