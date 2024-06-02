package com.javarush.kavalchuk.quest.controller;

import com.javarush.kavalchuk.quest.controller.enums.UserServletAttribute;
import com.javarush.kavalchuk.quest.model.Question;
import com.javarush.kavalchuk.quest.service.AnswerService;
import com.javarush.kavalchuk.quest.service.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "answerServlet", value = "/answers")
public class AnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var answerService = (AnswerService) getServletContext().getAttribute(UserServletAttribute.ANSWER_SERVICE.getName());
        var questionService = (QuestionService) getServletContext().getAttribute(UserServletAttribute.QUESTION_SERVICE.getName());
        var nextQuestion = answerService.prepareNextQuestion(request, questionService);

        request.getRequestDispatcher(getRedirectPath(nextQuestion))
                .forward(request, response);
    }

    private String getRedirectPath(Question question) {
        return question.getAnswers().isEmpty() ? "finish.jsp" : "questions.jsp";
    }
}