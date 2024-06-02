package com.javarush.kavalchuk.quest.controller;

import com.javarush.kavalchuk.quest.controller.enums.UserServletAttribute;
import com.javarush.kavalchuk.quest.controller.enums.UserSessionAttribute;
import com.javarush.kavalchuk.quest.service.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "questionServlet", value = "/questions")
public class QuestionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var questionService = (QuestionService) getServletContext().getAttribute(UserServletAttribute.QUESTION_SERVICE.getName());
        var question = questionService.getFirstQuestion();

        request.setAttribute(UserSessionAttribute.QUESTION.getName(), question);
        request.getRequestDispatcher("questions.jsp").forward(request, response);
    }
}
