<%-- 
    Document   : index
    Created on : Feb 24, 2016, 6:19:37 PM
    Author     : John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">


        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body{

                background-image: url(http://cdn1.theodysseyonline.com/files/2015/11/26/635841049393339322-1773368249_o-PILE-OF-BOOKS-facebook.jpg);
            }
            img{
                border-style: outset;
                border-width: 2px;
                border-color: blue;
                box-shadow: 5px 5px 5px #888888;
            }
            .jumbotron{
                background-color: chartreuse;

            }
            img:hover{
                border-style: outset;
                border-width: 2px;
                border-color: gold;
                box-shadow: 5px 5px 5px darkmagenta;

            }
            #adminTasks{
                width: 600px;
                height: 100px;
                background-color: black;
            }

        </style>

    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li></li>
                        <li>></li>
                        <li></li>

                    </ul>

                </div>
            </div>
        </div>
        <div class="container">
            <div class="jumbotron">
                <center><h1 class="panel-heading">Meet The Authors</h1></center>

                <br>
                <center><div class="row">
                        <div class="col-md-4">
                            <img class="img-rounded" data-toggle="tooltip" data-placement="bottom" title="James Brown" src="https://www.rockhall.com/media/assets/inductees/default/james-brown.jpg" height="200" width="300" alt=""/>
                        </div>
                        <div  class="col-md-4">
                            <img  class="img-rounded" data-toggle="tooltip" data-placement="bottom" title="Eric Dickerson" src="http://www.rantsports.com/nfl/files/2014/12/12.-Eric-Dickerson.jpg" alt="" height="200" width="300" />
                        </div>
                        <div class="col-md-4">
                            <img class="img-rounded" data-toggle="tooltip" data-placement="bottom" title="Brett Favre" src="http://funnycrave.com/wp-content/uploads/2009/08/brett-favre-vikings-ab081809.jpg" alt="" height="200" width="300" />
                        </div>
                    </div></center>

            </div></div>

    <center><div id="adminTasks" class="container"><div class="btn-group">
                <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addRecord">Add</button>
                <a class="btn btn-primary btn-lg" data-toggle="modal" data-target="#editRecord" href="AuthorGenerator?action=listEdit">Edit</a>
                <a class="btn btn-primary btn-lg" data-toggle="modal" data-target="#deleteRecord" href="AuthorGenerator?action=listDelete">Delete</a>
                <a class="btn btn-primary btn-lg" href="AuthorGenerator?action=find">View All Authors</a>

            </div></div></center>



    <!-- Modal -->
    <div id="addRecord" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Modal Header</h4>
                </div>
                <div class="modal-body">
                    <form method="POST" action="AuthorGenerator?action=add">
                        <table width="500" border="1" cellspacing="0" cellpadding="4">
                            <!--
                                In the EL expression below using 'not empty' is better than
                                author != null because it tests for both null and empty string
                            -->
                            <c:choose>
                                <c:when test="${not empty author}">
                                    <tr>
                                        <td style="background-color: black;color:white;" align="left">AuthorID</td>
                                        <td align="left"><input type="text" value="${author.authorId}" name="dateAdded" readonly /></td>
                                    </tr>         
                                </c:when>
                            </c:choose>

                            <tr>
                                <td style="background-color: black;color:white;" align="left">Name</td>
                                <td align="left"><input type="text" value="${author.authorName}" name="authorName" /></td>
                            </tr>

                            <c:choose>
                                <c:when test="${not empty author}">
                                    <tr>
                                        <td style="background-color: black;color:white;" align="left">Date Added</td>
                                        <td align="left"><input type="text" value="${author.dateAdded}" name="dateAdded" readonly /></td>
                                    </tr>         
                                </c:when>
                            </c:choose>

                            <tr>

                            <input type="submit" value="Add" name="action" />
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>


    <!-- Modal -->
    <div id="deleteRecord" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Modal Header</h4>
                </div>
                <div class="modal-body">
                    <form method="POST" action="AuthorGenerator?action=delete">

                        <input type="submit" value="Delete" name="submit" />
                        <br><br>
                        <table width="500" border="1" cellspacing="0" cellpadding="4">
                            <tr style="background-color: black;color:white;">
                                <th align="left" class="tableHead">ID</th>
                                <th align="left" class="tableHead">Author Name</th>
                                <th align="right" class="tableHead">Date Added</th>
                            </tr>
                            <c:forEach var="a" items="${authors}" varStatus="rowCount">
                                <c:choose>
                                    <c:when test="${rowCount.count % 2 == 0}">
                                        <tr style="background-color: white;">
                                        </c:when>
                                        <c:otherwise>
                                        <tr style="background-color: #ccffff;">
                                        </c:otherwise>
                                    </c:choose>
                                    <td><input type="checkbox" name="authorId" value="${a.authorId}" /></td>
                                    <td align="left">${a.authorName}</td>
                                    <td align="right">
                                        <fmt:formatDate pattern="M/d/yyyy" value="${a.dateAdded}"></fmt:formatDate>
                                        </td>
                                    </tr>
                            </c:forEach>
                        </table>
                        <br>

                        <input type="submit" value="Delete" name="submit" />
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>        

    <!-- Modal -->
    <div id="editRecord" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Modal Header</h4>
                </div>
                <div class="modal-body">
                    <form method="POST" action="AuthorGenerator?action=edit">

                        <input type="submit" value="Edit" name="submit" />
                        <br><br>
                        <table width="500" border="1" cellspacing="0" cellpadding="4">
                            <tr style="background-color: black;color:white;">
                                <th align="left" class="tableHead">ID</th>
                                <th align="left" class="tableHead">Author Name</th>
                                <th align="right" class="tableHead">Date Added</th>
                            </tr>
                            <c:forEach var="a" items="${authors}" varStatus="rowCount">
                                <c:choose>
                                    <c:when test="${rowCount.count % 2 == 0}">
                                        <tr style="background-color: white;">
                                        </c:when>
                                        <c:otherwise>
                                        <tr style="background-color: #ccffff;">
                                        </c:otherwise>
                                    </c:choose>
                                    <td><input type="checkbox" name="authorId" value="${a.authorId}" /></td>
                                    <td align="left">${a.authorName}</td>
                                    <td align="right">
                                        <fmt:formatDate pattern="M/d/yyyy" value="${a.dateAdded}"></fmt:formatDate>
                                        </td>
                                    </tr>
                            </c:forEach>
                        </table>
                        <br>

                        <input type="submit" value="Edit" name="submit" />
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>        

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
