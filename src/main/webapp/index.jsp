<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lab 2</title>
    <link rel="stylesheet" href="styles/main.css">
    <script type="module" src="scripts/validator.js"></script>
    <script type="module" src="scripts/canvas.js"></script>
</head>
<body>
<header>
    <div id="info">
        <h1>Абрамов Егор P3219</h1>
        <h1>Вариант 464905</h1>
    </div>
</header>

<main class="container">
    <section id="input-section">
        <form action="/lab_2/controller" method="POST" id="input-form">
            <div class="form-group">
                <label for="checkbox-x">Choose x</label>
                <div id="checkbox-x" class="options-group">
                    <div class="option-item">
                        <input type="checkbox" id="x-option-1" name="x" value="-5">
                        <label for="x-option-1">-5</label>
                    </div>
                    <div class="option-item">
                        <input type="checkbox" id="x-option-2" name="x" value="-4">
                        <label for="x-option-2">-4</label>
                    </div>
                    <div class="option-item">
                        <input type="checkbox" id="x-option-3" name="x" value="-3">
                        <label for="x-option-3">-3</label>
                    </div>
                    <div class="option-item">
                        <input type="checkbox" id="x-option-4" name="x" value="-2">
                        <label for="x-option-4">-2</label>
                    </div>
                    <div class="option-item">
                        <input type="checkbox" id="x-option-5" name="x" value="-1">
                        <label for="x-option-5">-1</label>
                    </div>
                    <div class="option-item">
                        <input type="checkbox" id="x-option-6" name="x" value="0">
                        <label for="x-option-6">0</label>
                    </div>
                    <div class="option-item">
                        <input type="checkbox" id="x-option-7" name="x" value="1">
                        <label for="x-option-7">1</label>
                    </div>
                    <div class="option-item">
                        <input type="checkbox" id="x-option-8" name="x" value="2">
                        <label for="x-option-8">2</label>
                    </div>
                    <div class="option-item">
                        <input type="checkbox" id="x-option-9" name="x" value="3">
                        <label for="x-option-9">3</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="enter-y">Enter y</label>
                <input id="enter-y" name="y" type="number" pattern="^-?\d+([\.,]\d{0,3})?$" step="0.001" required>
            </div>
            <div class="form-group">
                <label for="radio-r">Choose r</label>
                <div id="radio-r" class="options-group">
                    <div class="option-item">
                        <input type="radio" id="r-option-1" name="r" value="1" required>
                        <label for="r-option-1">1</label>
                    </div>
                    <div class="option-item">
                        <input type="radio" id="r-option-2" name="r" value="2">
                        <label for="r-option-2">2</label>
                    </div>
                    <div class="option-item">
                        <input type="radio" id="r-option-3" name="r" value="3">
                        <label for="r-option-3">3</label>
                    </div>
                    <div class="option-item">
                        <input type="radio" id="r-option-4" name="r" value="4">
                        <label for="r-option-4">4</label>
                    </div>
                    <div class="option-item">
                        <input type="radio" id="r-option-5" name="r" value="5">
                        <label for="r-option-5">5</label>
                    </div>
                </div>
            </div>

            <button type="submit" id="submit-button">Submit</button>
        </form>

        <div id="error-message">
            <c:if test="${not empty applicationScope.error and applicationScope.error.error}">
                ${applicationScope.error}
            </c:if>
        </div>
    </section>

    <section id="result">
        <table id="result-table">
            <thead>
                <tr>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Result</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty applicationScope.repository}">
                    <c:forEach var="result" items="${applicationScope.repository.results}">
                        <tr>
                            <td> ${result.point.x} </td>
                            <td> ${result.point.y} </td>
                            <td> ${result.point.r} </td>
                            <td> ${result.hit} </td>
                        </tr>
                    </c:forEach>
               </c:if>
            </tbody>
        </table>
    </section>

    <section id="graph-section">
        <canvas id="graph" width="500" height="500"></canvas>
    </section>
</main>
<script type="module" src="scripts/client.js"></script>
</body>
</html>