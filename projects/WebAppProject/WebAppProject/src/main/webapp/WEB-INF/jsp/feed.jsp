<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Users
  Date: 12.11.2017
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Feed</title>

    <link href="/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="/css/web-app.css" rel="stylesheet" type="text/css">

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/notify.js"></script>
    <script src="/js/feed.js"></script>
    <script src="/js/navigation_bar.js"></script>
</head>
<body>
<!-- add post modal-->
<div class="modal fade" id="add-post" tabindex="-1" role="dialog" aria-labelledby="addPostLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="addPostLabel">Add post</h4>
            </div>

            <div class="modal-body">
                <form id="add-post-form" role="form">
                    <div id="div-name" class="form-group">
                        <label for="name" class="control-label">Name:</label>
                        <div>
                            <input type="text" id="name" name="name"
                                   class="form-control"
                                   placeholder="Enter post name here">
                        </div>
                    </div>

                    <div id="div-text" class="form-group">
                        <label for="text" class="control-label">Text:</label>
                        <div>
                            <textarea id="text" type="password" name="old-password"
                                   class="form-control"
                                   placeholder="Enter post text here" rows="5" style="resize: vertical;"></textarea>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a id="btn-add" class="btn btn-primary btn-ok">Add</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/navigation_bar.jsp">
    <jsp:param name="id" value="${sessionScope.loggedUser.id}"/>
</jsp:include>
<div id="posts-container" class="container">
    <jsp:include page="/WEB-INF/jsp/posts.jsp">
        <jsp:param name="posts" value="${posts}"/>
    </jsp:include>
</div>
</body>
</html>
