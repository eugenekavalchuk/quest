package com.javarush.kavalchuk.quest.controller;

import com.javarush.kavalchuk.quest.controller.enums.Faculty;
import com.javarush.kavalchuk.quest.controller.enums.UserServletAttribute;
import com.javarush.kavalchuk.quest.controller.enums.UserSessionAttribute;
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var answerService = (AnswerService) getServletContext().getAttribute(UserServletAttribute.ANSWER_SERVICE.getName());
        var questionService = (QuestionService) getServletContext().getAttribute(UserServletAttribute.QUESTION_SERVICE.getName());
        var nextQuestion = answerService.prepareNextQuestion(request, questionService);

        request.setAttribute(UserSessionAttribute.USERNAME.getName(), request.getSession().getAttribute(UserSessionAttribute.USERNAME.getName()));
        request.setAttribute(Faculty.GRYFFINDOR.getName(), request.getSession().getAttribute(Faculty.GRYFFINDOR.getName()));
        request.setAttribute(Faculty.HUFFLEPUFF.getName(), request.getSession().getAttribute(Faculty.HUFFLEPUFF.getName()));
        request.setAttribute(Faculty.RAVENCLAW.getName(), request.getSession().getAttribute(Faculty.RAVENCLAW.getName()));
        request.setAttribute(Faculty.SLYTHERIN.getName(), request.getSession().getAttribute(Faculty.SLYTHERIN.getName()));

        request.getRequestDispatcher(getRedirectPath(nextQuestion))
                .forward(request, response);
    }

    private String getRedirectPath(Question question) {
        return question.getAnswers().isEmpty() ? "finish.jsp" : "questions.jsp";
    }
}