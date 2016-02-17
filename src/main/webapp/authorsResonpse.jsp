<%-- 
    Document   : authorsResonpse
    Created on : Feb 8, 2016, 12:40:51 PM
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
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authors</title>
        <style>
            body{
                
                background-image: url(http://www.planwallpaper.com/static/images/Cool-background-random-17506456-1869-1168.jpg);
            }
            
            h1{
                background-color:  chocolate;
                color: white;
                
            }
            th{
                background-color: blue;
                color: white;
            }
            td{
                font-size: 16px;
                text-align: center;
            }
            
        </style>
    </head>
    <body>
        <div class="container">
    <center><h1>Author Table</h1></center>

        <table class="table table-hover" width="600" border="1" cellspacing="2" cellpadding="5">

            <tr>
            <th  class="primary">Author ID</th>
            <th  class="primary">Name</th>
            <th  class="primary">Date Added</th>
            </tr>
            <c:forEach var="a" items="${authors}">
                <tr>

                    <td class="danger"> ${a.authorId} </td>
                    <td class="success"> ${a.authorName} </td>
                    <td class="info">
                         <fmt:formatDate pattern="MM/dd/yyyy" value="${a.dateAdded}"></fmt:formatDate>
                            </td>
                    </tr>
                </c:forEach>
        </table></div>
    
    <center><a class="btn btn-danger" href="index.html">Go Back</a></center>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
