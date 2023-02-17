<%@ page import="java.util.List" %>
<%@ page import="book.model.BookModel" %>
<%@ page import="book.service.BookService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <%
        //获取 css 的文件路径
        String cssPath = String.valueOf(request.getRequestURL().append("/../css/table.css"));
        //查询book的初始值
    %>
    <meta charset="UTF-8">
    <title>图书列表</title>
    <link rel="stylesheet" type="text/css" href="<%=cssPath%>" />
</head>
<body>
<div class="wrapper">
    <div class="table">
        <div class="row">
            <div class="cell">
                <span class="title">珍宝堂 </span>
                <c:if test="${normal}">
                <input type="button" value="添加图书" onclick="bookAdd()">
                </c:if>
                    <a href="book">首页</a>
                <c:if test="${normal}"> |
                    <a href="?method=forePage">上一页</a> |
                    <a href="?method=nextPage">下一页</a>
                    |<span>当前您在 第${sessionScope.pageNum}页</span>
                </c:if>
                    |<span>当前共查询到 ${count} 条结果</span>
            </div>
            <div class="cell">
                <form action="book" >
                    <input id="search" type="text" name="q" class="search" placeholder="请输入图书关键字" required="required">
                </form>
            </div>
        </div>
    </div>

    <div class="table">

        <div class="row header">
            <div class="cell">封面</div>
            <div class="cell">书名</div>
            <div class="cell">作者</div>
            <div class="cell">频道</div>
            <div class="cell">状态</div>
            <div class="cell">费用</div>
            <div class="cell">简介</div>
            <div class="cell">操作</div>
        </div>
        <c:forEach items="${bookList}" var="b">
            <div class="row" >
                <div class="cell" data-title="封面">
                    <img src="${b.coverUrl}" class="cover_icon">
                </div>
                <div class="cell" data-title="书名">${b.bookName}</div>
                <div class="cell" data-title="作者">${b.authorName}</div>
                <c:if test="${b.attribution == 1}">
                <div class="cell" data-title="频道">男频</div>
                </c:if>
                <c:if test="${b.attribution == 2}">
                    <div class="cell" data-title="频道">女频</div>
                </c:if>
                <c:if test="${b.attribution == 3}">
                    <div class="cell" data-title="频道">出版</div>
                </c:if>
                <c:if test="${b.status == 1}">
                    <div class="cell" data-title="状态">连载</div>
                </c:if>
                <c:if test="${b.status == 2}">
                    <div class="cell" data-title="状态">完本</div>
                </c:if>
                <c:if test="${b.status == null}">
                    <div class="cell" data-title="状态">未知</div>
                </c:if>
                <c:if test="${b.isVip == 1}">
                    <div class="cell" data-title="费用">收费</div>
                </c:if>
                <c:if test="${b.isVip == 0}">
                    <div class="cell" data-title="费用">免费</div>
                </c:if>
                <div class="cell" data-title="简介">${b.description}</div>
                <div class="cell" data-title="操作" book-id="${b.bookId}">
                    <input type="button" value="详情" onclick="bookDetail(this)">
                    <input type="button" value="修改" onclick="bookUpdate(this)">
                    <input type="button" value="删除" onclick="bookDel(this)">
                </div>
            </div>
        </c:forEach>
    </div>

</div>

<script>
    function bookDetail(node) {
        var id = node.parentNode.getAttribute('book-id')
        location.href="detail?method=detail&id=" + id
    }
    function bookUpdate(node) {
        var id = node.parentNode.getAttribute('book-id')
        location.href="detail?method=update&id=" + id
    }
    function bookDel(node) {
        var flag = confirm("确定要删除吗？")
        if(flag){
            var id = node.parentNode.getAttribute('book-id')
            location.href="detail?method=del&id=" + id
        }
    }
    function bookAdd(){
        location.href="detail?method=add"
    }
</script>
</body>

</html>
