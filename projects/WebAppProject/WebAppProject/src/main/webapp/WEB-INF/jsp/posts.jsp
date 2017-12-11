<%@ page import="entity.Post" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Users
  Date: 03.12.2017
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="application/xml" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="postsNumber" value="${fn:length(posts)}" />

<c:forEach var="i" begin="1" end="${postsNumber}" step="1">
    <c:set var="post" value="${posts[postsNumber-i]}" />
    <!-- one post -->
    <div class="top-margin">
        <div class="post-card">
            <div class="col-sm-8 col-sm-offset-2">
                <div class="row">
                    <div class="panel panel-default">
                        <div id="panel-heading" class="panel-heading">
                            <h4 class="display-4">
                                <c:out value="${post.name}"/>
                            </h4>
                        </div>
                        <div class="panel-body">${post.text}</div>
                        <div class="panel-footer">
                            <a href="user_profile?id=${post.owner.id}" class="text-primary">
                                <c:out value="${post.owner.email}"/>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>