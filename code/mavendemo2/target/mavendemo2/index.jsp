<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="user.UserModel" %><%--
  Created by IntelliJ IDEA.
  User: JAVASM
  Date: 2023/2/9
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--表示使用jstl--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>jsp测试</title>
</head>
<body>
<%
    List<UserModel> users = new ArrayList<>();
    users.add(new UserModel(1,"Jack","123"));
    users.add(new UserModel(2,"Mark","asdf"));
    users.add(new UserModel(3,"Smith","54ds"));
    //向请求中存入参数
    request.setAttribute("userList",users);
%>
<div>
    <ul>
        <c:forEach items="${userList}" var="u">
            <c:if test="${u.id > 1}">
                <%--调用的getter方法,没有getter方法就会认为没有属性--%>
                <li>${u.name} | ${u.password}</li>
            </c:if>
        </c:forEach>
    </ul>
</div>
<%--  $END$--%>
</body>
</html>
