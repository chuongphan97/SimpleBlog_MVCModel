<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 6/5/2021
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html class='no-js' lang='en'>
<head>
    <meta charset='utf-8'>
    <meta content='IE=edge,chrome=1' http-equiv='X-UA-Compatible'>
    <title>Forms</title>
    <meta content='lab2023' name='author'>
    <meta content='' name='description'>
    <meta content='' name='keywords'>
    <link href="assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css" /><link href="//netdna.bootstrapcdn.com/font-awesome/3.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/images/favicon.ico" rel="icon" type="image/ico" />
    <script src="http://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body class='main page'>
<jsp:include page="layout/header.jsp"></jsp:include>


<!-- Tools -->
<section id='tools'>
    <ul class='breadcrumb' id='breadcrumb'>
        <li class='title'>Add ad-post</li>
    </ul>
</section>
<!-- Content -->
<div id='content'>
    <div class='panel panel-default'>
        <div class='panel-heading'>
            <i class='icon-edit icon-large'></i>
            Add new post
        </div>
        <div class='panel-body'>
            <form method="post" action="/blogs?action=addPost">
                <fieldset>
                    <legend>Inputs</legend>
                    <div class='form-group' hidden>
                        <label class='control-label'>Username</label>
                        <input class='form-control' placeholder='Enter username' name='username' type='text'  value="${account.username}">
                    </div>
                    <div class='form-group'>
                        <label class='control-label'>Title</label>
                        <input class='form-control' placeholder='Enter title' type='text' name="title">
                    </div>

                    <div class='form-group'>
                        <label class='control-label'>Content</label>
                        <textarea class='form-control' rows='4' name="content"></textarea>
                    </div>
                    <div class='form-group'>
                        <label class='control-label'>Upload image</label>
                        <input type='file' name="image" id="image" accept="image/gif, image/jpeg, image/png">
                        <input type="text" id="b64" name="b64" value="" hidden>
                        <img id="avt" class="avt" hidden>
                    </div>
                    <div class="form-group">
                        <c:if test="${msg != null}">
                            <p>${msg}</p>
                        </c:if>
                    </div>
                </fieldset>
                <div class='form-actions'>
                    <button class='btn btn-default' type='submit'>Submit</button>
                    <a class='btn' href='/blogs?action=create'>Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
<!-- Footer -->
<!-- Javascripts -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js" type="text/javascript"></script><script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js" type="text/javascript"></script><script src="//cdnjs.cloudflare.com/ajax/libs/modernizr/2.6.2/modernizr.min.js" type="text/javascript"></script><script src="assets/javascripts/application-985b892b.js" type="text/javascript"></script>
<!-- Google Analytics -->
<script type="text/javascript">

    function readFile(){
        if (this.files && this.files[0]){
            var FR = new FileReader();
            FR.addEventListener("load",function (e){
                document.getElementById("avt").src = e.target.result;
                document.getElementById("b64").value = e.target.result;
            });
            FR.readAsDataURL(this.files[0]);
        }
    }
    document.getElementById("image").addEventListener("change",readFile);

    var _gaq=[['_setAccount','UA-XXXXX-X'],['_trackPageview']];
    (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
        g.src=('https:'===location.protocol?'//ssl':'//www')+'.google-analytics.com/ga.js';
        s.parentNode.insertBefore(g,s)}(document,'script'));
</script>
</body>
</html>

