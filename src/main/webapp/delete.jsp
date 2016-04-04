<%-- 
    Document   : editDelete
    Created on : Feb 27, 2016, 1:29:43 PM
    Author     : John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List</title>
    </head>
    <body>
    <center><h1>Author List</h1></center>
        
    <center><form method="POST" action="AuthorGenerator?action=delete">
                        
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
        <p>Return to <a href="index.jsp">Home Page</a></p></center>
        
    </body>
</html>
