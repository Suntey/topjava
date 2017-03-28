<%--
  Created by IntelliJ IDEA.
  User: Suntey
  Date: 28.03.2017
  Time: 1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add&Edit</title>
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
        tr:hover td{
            color: black;
        }
    </style>
</head>
<body>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <table>
        <tr>
            <td>
                <i>Дата и время</i>
            </td>
            <td>
                <input type="datetime-local" name="date" value="<c:out value="${meal.dateTime}"/>"/>
            </td>
        </tr>
        <tr>
            <td>
                <i>Описание</i>
            </td>
            <td>
                <input type="text" name="description" value="<c:out value="${meal.description}"/>"/>
            </td>
        </tr>
        <tr>
            <td>
                <i>Калории</i>
            </td>
            <td>
                <input type="text" name="calories" value="<c:out value="${meal.calories}"/>"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Отправить">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
