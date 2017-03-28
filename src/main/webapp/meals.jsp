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
<h2><a href="index.html">Home</a></h2>
<table>
    <tr>
        <th>ID</th>
        <th>Date&Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan = "2" align="center">Delete/Edit</th>
    </tr>

    <jsp:useBean id="mealList" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealWithExceed>"/>
    <c:if test="${!empty mealList}">
        <c:forEach var="meal" items = "${mealList}">
            <tr class="${meal.exceed? "redtxt":"greentxt"}">
                <td align="left"> ${meal.id}</td>
                <td align="left"> ${meal.dateTime.toString().replace("T"," ")}</td>
                <td align="left"> ${meal.description}</td>
                <td align="left"> ${meal.calories}</td>
                <td> <a href="meals?action=delete&mealID=<c:out value="${meal.id}"/>">Delete</a> </td>
                <td> <a href="meals?action=edit&mealID=<c:out value="${meal.id}"/>">Edit</a> </td>
            </tr>
        </c:forEach>
    </c:if>

</table>
<p><a href="meals?action=add">Add User</a></p>
</body>
</html>
