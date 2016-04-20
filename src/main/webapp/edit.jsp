<%-- 
    Document   : edit
    Created on : Feb 27, 2016, 1:50:33 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="POST" action="AuthorGenerator?action=change">
            
                        <table width="500" border="1" cellspacing="0" cellpadding="4">
                            <!--
                                In the EL expression below using 'not empty' is better than
                                author != null because it tests for both null and empty string
                            -->
                            <c:choose>
                                <c:when test="${not empty authorId}">
                                    <tr>
                                        <td style="background-color: black;color:white;" align="left">AuthorID</td>
                                        <td align="left"><input type="text" value="${authorId}" name="authorId" readonly /></td>
                                    </tr>         
                                </c:when>
                            </c:choose>

                            <tr>
                                <td style="background-color: black;color:white;" align="left">Name</td>
                                <td align="left"><input type="text"  name="authorName" /></td>
                            </tr>

                            <c:choose>
                                <c:when test="${not empty authorId}">
                                    <tr>
                                        <td style="background-color: black;color:white;" align="left">Date Added</td>
                                        <td align="left"><input type="text" value="${dateAdded}"  name="dateAdded" readonly /></td>
                                    </tr>         
                                </c:when>
                            </c:choose>

                            <tr>

                            <input type="submit" value="Change" name="action" />
                            </tr>
                        </table>
                    </form>
    </body>
</html>
