<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 6/2/2021
  Time: 8:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Blog Home - Start Bootstrap Template</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles-home.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="assets/stylesheets/application-a07755f5.css" rel="stylesheet" type="text/css" /><link href="//netdna.bootstrapcdn.com/font-awesome/3.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="assets/images/favicon.ico" rel="icon" type="image/ico" />
    <style>
        .post-content {
            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 2; /* number of lines to show */
            -webkit-box-orient: vertical;
        }
    </style>
</head>
<body>
<!-- Responsive navbar-->
<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/blogs?action=home" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
                <i class="fas fa-feather-alt fa-3x" style="color: #1abc9c"></i>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">

            </ul>

            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
            </form>

            <div class="dropdown text-end">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                    <%--                    <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">--%>
                    <i class="fas fa-user-circle rounded-circle" style="font-size: 32px"></i>
                </a>
                <c:choose>
                    <c:when test="${account.getUsername() == 'admin'}">
                        <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                            <li><a class="dropdown-item" ><strong>Username: </strong><span name="username">${account.getUsername()}</span></a></li>
                            <li><a class="dropdown-item" ><strong>Email: </strong><span>${account.getEmail()}</span></a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/blogs?action=wall&username=${account.username}">Wall</a></li>
                            <li><a class="dropdown-item" href="/blogs?action=admin">Setting page</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/blogs?action=logout">Sign out</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                            <li><a class="dropdown-item" ><strong>Username: </strong><span name="username">${account.getUsername()}</span></a></li>
                            <li><a class="dropdown-item" ><strong>Email: </strong><span>${account.getEmail()}</span></a></li>
                            <li><a class="dropdown-item" href="/blogs?action=wall&username=${account.username}">Wall</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/blogs?action=logout">Sign out</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>
</header>
<!-- Page header with logo and tagline-->
<!-- Page content-->


<div class="container">
    <div class="row">
        <!-- Blog entries-->
        <div class="col-lg-8">
            <div class="panel panel-default">
                <div class="panel-body">
                    <section class="post-heading">
                        <div class="row">
                            <div class="col-md-10">
                                <div class="media">

                                    <div class="media-body">
                                        <a href="/blogs?action=wall&username=${detailPost.username}" class="anchor-username"><h4 class="media-heading">${detailPost.username}</h4></a>
                                        <p class="anchor-time">${detailPost.date}</p>
                                        <hr>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <c:if test="${account.username == detailPost.username}">
                                    <div class="">
                                        <a href="/blogs?action=edit&post_id=${detailPost.post_id}"><i class="fas fa-edit"></i></a> | <a href="/blogs?action=delete-post&post_id=${detailPost.post_id}"><i class="fas fa-minus-square"></i></a>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </section>
                    <section class="post-body">
                        <h3>${detailPost.title}</h3>
                        <p>${detailPost.content}</p>
                        <c:if test="${detailPost.image != null}">
                            <img src="${detailPost.image}" alt="..." width="100%" height="auto">
                        </c:if>
                    </section>
                    <section class="post-footer">
                        <hr>
                        <div class="post-footer-comment-wrapper">
                            <div class="comment-form">

                            </div>
                            <div class="comment">
                                <c:forEach items="${comments}" var="comment">
                                    <div class="media">
                                        <div class="media-body">
                                            <table style="width: 100%">
                                                <tr>
                                                    <td class="align-items-start"><a href="#" class="anchor-username"><h4 class="media-heading"><strong>${comment.username}</strong> </h4></a></td>
                                                    <c:if test="${account.username == comment.username}">
                                                        <td class="align-items-end"><a href="/blogs?action=delete-comment&comment_id=${comment.comment_id}"><i class="fas fa-minus-square"></i></a></td>
                                                    </c:if>
                                                </tr>
                                            </table>


                                            <a href="#" class="anchor-time">${comment.date}</a>
                                            <p>${comment.content}</p>
                                        </div>
                                    </div>
                                    <hr>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="post-footer-comment-wrapper">
                            <div class='panel-group'>
                                <div class='panel panel-default'>
                                    <div class="panel-body">
                                        <form method="post" action="/blogs?action=add-comment">
                                            <fieldset>
                                                <legend>Comment</legend>
                                                <input type="text" name="post_id" value="${detailPost.post_id}" hidden>
                                                <div class='form-group' hidden >
                                                    <label class='control-label'>Username</label>
                                                    <input class='form-control' placeholder='Enter username' name='username' type='text'  value="${account.username}">
                                                </div>
                                                <div class='form-group'>
                                                    <textarea class='form-control' rows='4' name="content" ></textarea>
                                                </div>

                                            </fieldset>
                                            <div class='form-actions'>
                                                <button class='btn btn-default' type='submit'>Submit</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>

        </div>
        <!-- Side widgets-->
        <div class="col-lg-4">
            <!-- Search widget-->
            <div class="card mb-4">
                <div class="card-header">Search</div>
                <div class="card-body">
                    <div class="input-group">
                        <form action="/blogs?action=search" method="post">
                            <input name="search" class="form-control" type="text" placeholder="Enter search term..." aria-label="Enter search term..." aria-describedby="button-search" />
                            <button class="btn btn-primary" id="button-search" type="submit">Go!</button>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Categories widget-->
            <div class="card mb-4">
                <div class="card-header">Categories</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <li><a href="#!">Web Design</a></li>
                                <li><a href="#!">HTML</a></li>
                                <li><a href="#!">Freebies</a></li>
                            </ul>
                        </div>
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <li><a href="#!">JavaScript</a></li>
                                <li><a href="#!">CSS</a></li>
                                <li><a href="#!">Tutorials</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Side widget-->
            <div class="card mb-4">
                <div class="card-header">Side Widget</div>
                <div class="card-body">You can put anything you want inside of these side widgets. They are easy to use, and feature the Bootstrap 5 card component!</div>
            </div>
        </div>
    </div>
</div>

<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script>
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
</script>
<script src="js/scripts.js"></script>
</body>
</html>

