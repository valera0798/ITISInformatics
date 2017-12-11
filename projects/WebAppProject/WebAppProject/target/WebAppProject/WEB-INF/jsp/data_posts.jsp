<%--
  Created by IntelliJ IDEA.
  User: Users
  Date: 03.12.2017
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="application/xml" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<data>
    <jsp:include page="/WEB-INF/jsp/posts.jsp">
        <jsp:param name="posts" value="${posts}"/>
    </jsp:include>
</data>

