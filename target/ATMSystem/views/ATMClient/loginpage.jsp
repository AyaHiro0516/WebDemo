<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2019/2/18
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<h1>欢迎来到登录页面！</h1>

<form action="${pageContext.request.contextPath}/LoginCtr" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
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
