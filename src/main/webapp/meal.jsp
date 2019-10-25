<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<form method="POST" action='meals'>
    <jsp:useBean id="updated" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
     <input hidden="hidden" type="text" readonly="readonly" name="id" value="<c:out value="${updated.id}" />"/>
    Дата : <input type="datetime-local" name="date"  value="<c:out value="${updated.dateTime}" />"/> <br/>
    Описание : <input type="text" name="description" value="<c:out value="${updated.description}" />"/> <br/>
    Калории : <input type="number" name="calories" value="${updated.calories}" /> <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
