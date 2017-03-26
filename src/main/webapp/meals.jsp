<%--
  Created by IntelliJ IDEA.
  User: Suntey
  Date: 25.03.2017
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal List</title>
    <style type="text/css">
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            background: white;
            max-width: 70%;
            width: 70%;
            border-collapse: collapse;
            text-align: left;
        }
        th {
            font-weight: normal;
            color: #039;
            border-bottom: 2px solid #6678b1;
            padding: 10px 8px;
        }
        td {
            border-bottom: 1px solid #ccc;
            padding: 9px 8px;
            transition: .3s linear
        }
        .greentxt
        {
            color: green;
        }
        .redtxt
        {
            color: red;
        }
        tr:hover td{
            color: black;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>Date&Time</th>
        <th>Description</th>
        <th>Calories</th>
        <%--<th colspan = "2">Delete/Edit</th>--%>
    </tr>

    <jsp:useBean id="mealList" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealWithExceed>"/>
    <c:if test="${!empty mealList}">
        <c:set var = "selector" value=""/>

        <c:forEach var="meal" items = "${mealList}">

            <c:choose>
                <c:when test="${meal.exceed}">
                    <c:set var="selector" value="redtxt" />
                </c:when>
                <c:otherwise>
                    <c:set var="selector" value="greentxt" />
                </c:otherwise>
            </c:choose>
            <tr class="${selector}">
                <td align="left"> ${meal.dateTime.toString().replace("T"," ")}</td>
                <td align="left"> ${meal.description}</td>
                <td align="left"> ${meal.calories}</td>
            </tr>
        </c:forEach>
    </c:if>

</table>
</body>
</html>
