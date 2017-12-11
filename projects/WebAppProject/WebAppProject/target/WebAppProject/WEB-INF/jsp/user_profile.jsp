<%--
  Created by IntelliJ IDEA.
  User: Users
  Date: 11.10.2017
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>User</title>

    <link href="/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="/css/web-app.css" rel="stylesheet" type="text/css">

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/notify.js"></script>
    <script src="/js/user_profile.js"></script>
</head>
<body>
<div class="container">
    <!-- delete confirmation modal-->
    <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="confirmDeleteLabel">Confirm Delete</h4>
                </div>

                <div class="modal-body">
                    <p>Your profile with corresponding data will be erased.</p>
                    <p>Do you want to delete?</p>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <a id="btn-delete" class="btn btn-danger btn-ok">Delete</a>
                </div>
            </div>
        </div>
    </div>

    <!-- change profile modal-->
    <div class="modal fade" id="change-profile" tabindex="-1" role="dialog" aria-labelledby="changeProfileLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="changeProfileLabel">Change profile</h4>
                </div>

                <div class="modal-body">
                    <form id="change-profile-form" role="form">
                        <div id="div-email" class="form-group">
                            <label for="email" class="control-label">Email:
                                <i class="text-info">* ${user.email}</i>
                            </label>
                            <div>
                                <input type="text" id="email" name="email"
                                       class="form-control"
                                       placeholder="Enter new email here">
                                <span id="error-email-invalid" class="text-danger"></span>
                            </div>
                        </div>

                        <div id="div-old-password" class="form-group">
                            <label for="old-password" class="control-label">Password:</label>
                            <div>
                                <input id="old-password" type="password" name="old-password"
                                       class="form-control"
                                       placeholder="Enter old password here">
                            </div>
                        </div>

                        <div id="div-new-password" class="form-group">
                            <div>
                                <input id="new-password" type="password" name="new-password"
                                       class="form-control"
                                       placeholder="Enter new password here">
                                <span id="error-passwords-same" class="text-danger"></span>
                            </div>
                        </div>

                        <div id="div-gender" class="form-group">
                            <label for="gender">Gender:</label>
                            <div>
                                <select id="gender" class="form-control" name="gender">
                                    <c:choose>
                                        <c:when test="${user.gender eq 'Male'}">
                                            <option name="male" selected>Male</option>
                                            <option name="female">Female</option>
                                        </c:when>
                                        <c:when test="${user.gender eq 'Female'}">
                                            <option name="male">Male</option>
                                            <option name="female" selected>Female</option>
                                        </c:when>
                                    </c:choose>
                                </select>
                            </div>
                        </div>

                        <div id="div-country" class="form-group">
                            <label for="country" class="control-label">Country:</label>
                            <div>
                                <select id="country" class="form-control input-group-lg" name="country">
                                    <c:choose>
                                        <c:when test="${user.country eq 'Russia'}">
                                            <option name="russia" selected>Russia</option>
                                            <option name="england">England</option>
                                        </c:when>
                                        <c:when test="${user.country eq 'England'}">
                                            <option name="russia">Russia</option>
                                            <option name="england" selected>England</option>
                                        </c:when>
                                    </c:choose>

                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div>
                                <span id="error-email-is-busy" class="text-danger"></span>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <input id="btn-change" type="submit" class="btn btn-primary btn-ok" value="Change"/>
                </div>
            </div>
        </div>
    </div>

    <div class="row top-margin">
        <div class="col-sm-8 col-sm-offset-2">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-8">
                            <h2><c:out value="${user.email}"/></h2>
                            <p><strong>Gender: </strong> <c:out value="${user.gender}"/></p>
                            <p><strong>Country: </strong> <c:out value="${user.country}"/></p>
                        </div><!--/col-->
                        <div class="col-xs-12 col-sm-4 text-center">
                            <img src="/resources/avatar.png" alt="" class="center-block img-thumbnail img-responsive">
                            <%--raiting--%>
                            <ul class="list-inline ratings text-center top-margin" title="Ratings">
                                <li><a href="#"><span class="fa fa-star fa-lg"></span></a></li>
                                <li><a href="#"><span class="fa fa-star fa-lg"></span></a></li>
                                <li><a href="#"><span class="fa fa-star fa-lg"></span></a></li>
                                <li><a href="#"><span class="fa fa-star fa-lg"></span></a></li>
                                <li><a href="#"><span class="fa fa-star fa-lg"></span></a></li>
                            </ul>
                        </div><!--/col-->

                        <div class="col-xs-12 col-sm-4 col-sm-offset-8">
                            <h2><strong><c:out value="${fn:length(user.posts)}"/></strong></h2>
                            <p>
                                <small>Publications</small>
                            </p>
                            <c:if test="${isUserPage}">
                                <div>
                                    <div class="btn-group width-full">
                                        <button type="button" class="btn btn-primary btn-block dropdown-toggle"
                                                data-toggle="dropdown">
                                            <span class="fa fa-gear"></span>
                                            Options
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu width-full" role="menu">
                                            <li>
                                                <a href="#" data-href="change?id=${user.id}" data-toggle="modal"
                                                   data-target="#change-profile">
                                                    Change profile
                                                </a>
                                            </li>
                                            <li class="divider"></li>
                                            <li>
                                                <a href="#" data-href="delete?id=${user.id}" data-toggle="modal"
                                                   data-target="#confirm-delete">
                                                    Delete profile
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </c:if>
                        </div><!--/col-->
                    </div><!--/row-->
                </div><!--/panel-body-->
            </div><!--/panel-->
        </div>
    </div>

    <c:set var="postsNumber" value="${fn:length(posts)}"/>
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
            <div id="posts-container" class="container-fluid">
                <c:forEach var="i" begin="1" end="${postsNumber}" step="1">
                    <c:set var="post" value="${posts[postsNumber-i]}"/>
                    <div class="row">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <c:if test="${isUserPage}">
                                    <button id="${post.id}" type="button" class="close delete-post">&times;</button>
                                </c:if>
                                <h3 class="panel-title">
                                    <c:out value="${post.name}"/>
                                </h3>
                            </div>
                            <div class="panel-body">
                                <c:out value="${post.text}"/>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
