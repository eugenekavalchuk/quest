<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hogwarts Sorting Quiz</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background: url('<c:url value="/img/index.jpg" />') no-repeat center center fixed;
            background-size: cover;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            overflow: hidden;
            font-family: 'Arial', sans-serif;
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
            font-family: 'Georgia', serif;
            color: #f0e68c; /* Цвет текста в центре */
        }
        .magic-text {
            font-size: 1.5rem;
            white-space: pre-wrap;
            font-family: 'Georgia', serif;
            color: #f0e68c; /* Цвет текста в центре */
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
    </style>
</head>
<body>
<div class="content-container">
    <div class="container">
        <h1>Hogwarts Sorting Quiz</h1>
        <p class="magic-text" id="magicText"></p>
    </div>
</div>

<div class="form-container">
    <form id="nameForm" method="post" action="user">
        <input type="text" class="form-control" id="userName" name="userName" placeholder="Ваше имя" minlength="2" maxlength="10">
        <button type="submit" class="btn btn-primary" id="startButton" disabled>Приступить</button>
    </form>
</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const text = "Добро пожаловать на церемонию распределения в Хогвартсе! Ответьте на вопросы, чтобы узнать, к какому факультету вы принадлежите: " +
            "Гриффиндору, Пуффендую, Когтеврану или Слизерину.";
        let index = 0;
        const speed = 35;

        function typeWriter() {
            if (index < text.length) {
                document.getElementById("magicText").innerHTML += text.charAt(index);
                index++;
                setTimeout(typeWriter, speed);
            }
        }

        typeWriter();

        const userNameInput = document.getElementById('userName');
        const startButton = document.getElementById('startButton');

        userNameInput.addEventListener('input', () => {
            const name = userNameInput.value;
            const isValid = /^[A-Za-zА-Яа-яЁё]{2,10}$/.test(name);
            startButton.disabled = !isValid;
        });
    });
</script>
</body>
</html>