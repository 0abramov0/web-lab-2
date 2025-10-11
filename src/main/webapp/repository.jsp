<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lab 2</title>
    <link rel="stylesheet" href="styles/main.css">
</head>
<body>
<header>
    <div id="info">
        <h1>Абрамов Егор P3219</h1>
        <h1>Вариант 464905</h1>
    </div>
</header>

<main class="container">
    <section id="result">
        <table id="result-table">
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Result</th>
            </tr>
            <c:if test="${not empty applicationScope.repository}">
                <c:forEach var="result" items="${applicationScope.repository.results}">
                    <tr>
                        <td> ${result.point.x} </td>
                        <td> ${result.point.y} </td>
                        <td> ${result.point.r} </td>
                        <td> ${result.hit ? "Hit" : "Miss"} </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </section>
</main>
</body>
</html>