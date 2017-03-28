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
            border-collapse: collapse;
            text-align: left;
        }
        input.txt {
            color: black;
            background-color: lightgoldenrodyellow;
            border: 1px inset #00008B;
            width: 200px;
        }
        th {
            font-weight: normal;
            color: #039;
            padding: 10px 8px;
        }

    </style>
</head>
<body>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <table>
        <tr>
            <th>Дата и время</th>
            <td>
                <input class="txt" type="datetime-local" name="date" value="<c:out value="${meal.dateTime}"/>"/>
            </td>
        </tr>
        <tr>
            <th>Описание</th>
            <td>
                <input class="txt" type="text" name="description" value="<c:out value="${meal.description}"/>"/>
            </td>
        </tr>
        <tr>
            <th>Калории</th>
            <td>
                <input class="txt" type="text" name="calories" value="<c:out value="${meal.calories}"/>"/>
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
