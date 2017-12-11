<%@ page import="entity.User" %>
<%@ page import="util.SessionUtil" %><%--
  Created by IntelliJ IDEA.
  User: Users
  Date: 11.10.2017
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="col-md-10 col-md-offset-1">
    <nav id="custom-nav" class="navbar navbar-default">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li class="dropdown-toggle">
                    <a href="#" data-href="add?id=${param.id}" data-toggle="modal" data-target="#add-post">
                        <span class="glyphicon glyphicon-plus"></span>
                        Add post
                    </a>
                </li>
            </ul>

            <!-- search -->
            <form class="navbar-form navbar-left">
                <div class="input-group">
                    <div class="input-group">
                        <input id="search-line" type="text" class="form-control" placeholder="Search" aria-describedby="search-addon">
                        <span class="input-group-addon" id="search-addon"><i class="glyphicon glyphicon-search"></i></span>
                    </div>
                </div>
            </form>

            <!-- account managing -->
            <ul class="nav navbar-nav navbar-right">
                <li><a id="profile" href="user_profile?id=${param.id}"><span class="glyphicon glyphicon-user"></span>
                    Profile</a></li>
                <li><a id="logout" href="logout?id=${param.id}"><span class="glyphicon glyphicon-log-out"></span>
                    Logout</a></li>
            </ul>
        </div>
    </nav>
</div>
