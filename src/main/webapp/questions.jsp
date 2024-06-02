<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hogwarts Sorting Quiz Questions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background: url('<c:url value="/img/questions.jpg" />') no-repeat center center fixed;
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
        .container {
            text-align: center;
            max-width: 600px;
            background: rgba(0, 0, 0, 0.8);
            padding: 20px;
            border-radius: 10px;
            margin-top: 50px;
            color: #f0e68c;
        }
        .question {
            font-size: 1.5rem;
            margin-bottom: 20px;
        }
        .answers {
            display: flex;
            justify-content: space-around;
            gap: 10px;
            flex-wrap: wrap;
        }
        .answers button {
            background: rgba(82, 69, 19, 0.7);
            border: none;
            color: #f0e68c;
            padding: 10px 20px;
            font-size: 1rem;
            border-radius: 5px;
            transition: background 0.3s ease, color 0.3s ease;
            width: 200px;
            height: 80px;
            white-space: pre-wrap;
            text-align: center;
            overflow: hidden;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-top: 20px;
        }
        .answers button:hover {
            background: rgba(69, 75, 45, 1);
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Вопрос ${question.id}</h1>
    <c:if test="${not empty question}">
        <div class="question">
            <h3>${question.text}</h3>
            <form action="answers" method="post" class="answers">
                <c:forEach var="answer" items="${question.answers}">
                    <button type="submit" name="answerId" value="${answer.id}">${answer.text}</button>
                </c:forEach>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>
