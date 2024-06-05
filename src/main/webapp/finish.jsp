<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finish Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-size: cover;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            overflow: hidden;
            font-family: 'Georgia', serif;
        }
        .background-gryffindor {
            background: url('<c:url value="/img/gryffindor.jpg" />') no-repeat center center fixed;
        }
        .background-slytherin {
            background: url('<c:url value="/img/slytherin.jpg" />') no-repeat center center fixed;
        }
        .background-ravenclaw {
            background: url('<c:url value="/img/ravenclaw.jpg" />') no-repeat center center fixed;
        }
        .background-hufflepuff {
            background: url('<c:url value="/img/hufflepuff.png" />') no-repeat center center fixed;
        }
        .content-container {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .container {
            text-align: center;
            max-width: 600px;
            background: rgba(0, 0, 0, 0.5);
            padding: 20px;
            border-radius: 10px;
        }
        .container h1 {
            color: #f0e68c;
            font-size: 2rem;
            margin-bottom: 10px;
        }
        .container p {
            color: #f0e68c;
            font-size: 1.2rem;
        }
        .form-container {
            width: 100%;
            display: flex;
            justify-content: center;
            position: absolute;
            bottom: 20px;
        }
        .form-container form {
            display: flex;
            gap: 10px;
            align-items: center;
        }
        .form-container input {
            max-width: 200px;
            padding: 10px;
            border-radius: 5px;
            border: none;
            font-size: 1rem;
            background: rgba(255, 255, 255, 0.3);
            color: white;
        }
        .form-container input:focus {
            outline: none;
            background: rgba(255, 255, 255, 0.5);
        }
        .form-container button {
            padding: 10px 20px;
            border-radius: 5px;
            border: none;
            font-size: 1rem;
            background: rgba(80, 80, 80, 0.7);
            color: white;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .form-container button:hover {
            background: rgba(80, 80, 80, 0.9);
        }
        .form-container button:disabled {
            background: rgba(80, 80, 80, 0.5);
            color: rgba(255, 255, 255, 0.5);
            cursor: not-allowed;
        }
        .footer-text {
            position: absolute;
            bottom: 10px;
            left: 10px;
            font-size: 12px;
            color: rgba(255, 255, 255, 0.5);
            text-align: left;
        }
    </style>
</head>
<body class="<c:choose>
                <c:when test='${result == "gryffindor"}'>background-gryffindor</c:when>
                <c:when test='${result == "slytherin"}'>background-slytherin</c:when>
                <c:when test='${result == "ravenclaw"}'>background-ravenclaw</c:when>
                <c:when test='${result == "hufflepuff"}'>background-hufflepuff</c:when>
            </c:choose>">
<div class="content-container">
    <div class="container">
        <h1>Результат распределения</h1>
        <c:if test="${result == 'gryffindor'}">
            <p>Ты принадлежишь к факультету Гриффиндор!</p>
        </c:if>
        <c:if test="${result == 'slytherin'}">
            <p>Ты принадлежишь к факультету Слизерин!</p>
        </c:if>
        <c:if test="${result == 'ravenclaw'}">
            <p>Ты принадлежишь к факультету Когтевран!</p>
        </c:if>
        <c:if test="${result == 'hufflepuff'}">
            <p>Ты принадлежишь к факультету Пуффендуй!</p>
        </c:if>
    </div>
</div>
<div class="form-container">
    <form id="nameForm" method="post" action="user">
        <input type="hidden" name="_method" value="delete"/>
        <button type="submit" class="btn btn-primary" id="startButton">Начать заново</button>
    </form>
</div>
<div class="footer-text">
    <div>Name: ${userName}</div>
    <div>Gryffindor: ${gryffindor}</div>
    <div>Hufflepuff: ${hufflepuff}</div>
    <div>Ravenclaw: ${ravenclaw}</div>
    <div>Slytherin: ${slytherin}</div>
</div>
</body>
</html>