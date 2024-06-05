package com.javarush.kavalchuk.quest.controller;

import com.javarush.kavalchuk.quest.controller.enums.Faculty;
import com.javarush.kavalchuk.quest.controller.enums.UserServletAttribute;
import com.javarush.kavalchuk.quest.controller.enums.UserSessionAttribute;
import com.javarush.kavalchuk.quest.model.Question;
import com.javarush.kavalchuk.quest.service.AnswerService;
import com.javarush.kavalchuk.quest.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AnswerServletTest {

    private AnswerServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ServletContext servletContext;
    private AnswerService answerService;
    private QuestionService questionService;
    private RequestDispatcher requestDispatcher;
    private ServletConfig servletConfig;
    private Question question;

    @BeforeEach
    public void setUp() {
        servlet = new AnswerServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        servletContext = mock(ServletContext.class);
        answerService = mock(AnswerService.class);
        questionService = mock(QuestionService.class);
        requestDispatcher = mock(RequestDispatcher.class);
        servletConfig = mock(ServletConfig.class);
        question = mock(Question.class);

        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        when(servletContext.getAttribute(UserServletAttribute.ANSWER_SERVICE.getName())).thenReturn(answerService);
        when(servletContext.getAttribute(UserServletAttribute.QUESTION_SERVICE.getName())).thenReturn(questionService);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        try {
            servlet.init(servletConfig);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void doPost() throws ServletException, IOException {
        when(question.getAnswers()).thenReturn(Collections.EMPTY_LIST);
        when(answerService.prepareNextQuestion(any(HttpServletRequest.class), any(QuestionService.class))).thenReturn(question);
        when(session.getAttribute(UserSessionAttribute.USERNAME.getName())).thenReturn("testUser");
        when(session.getAttribute(Faculty.GRYFFINDOR.getName())).thenReturn(1d);
        when(session.getAttribute(Faculty.HUFFLEPUFF.getName())).thenReturn(1d);
        when(session.getAttribute(Faculty.RAVENCLAW.getName())).thenReturn(1d);
        when(session.getAttribute(Faculty.SLYTHERIN.getName())).thenReturn(1d);

        servlet.doPost(request, response);

        verify(request).setAttribute(UserSessionAttribute.USERNAME.getName(), "testUser");
        verify(request).setAttribute(Faculty.GRYFFINDOR.getName(), 1d);
        verify(request).setAttribute(Faculty.HUFFLEPUFF.getName(), 1d);
        verify(request).setAttribute(Faculty.RAVENCLAW.getName(), 1d);
        verify(request).setAttribute(Faculty.SLYTHERIN.getName(), 1d);
    }
}