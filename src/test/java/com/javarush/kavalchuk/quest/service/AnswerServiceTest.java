package com.javarush.kavalchuk.quest.service;

import com.javarush.kavalchuk.quest.controller.enums.Faculty;
import com.javarush.kavalchuk.quest.controller.enums.UserSessionAttribute;
import com.javarush.kavalchuk.quest.model.Answer;
import com.javarush.kavalchuk.quest.model.Question;
import com.javarush.kavalchuk.quest.repository.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

class AnswerServiceTest {
    AnswerService answerService;

    public AnswerServiceTest() {
        var answer = new Answer();
        answer.setId(1);
        answer.setText("Test text answer");
        answer.setGryffindor(1.01);
        answer.setHufflepuff(1.02);
        answer.setRavenclaw(1.03);
        answer.setSlytherin(1.04);
        answer.setNextQuestionId(2);

        AnswerRepository answerRepository = new AnswerRepository();
        answerRepository.saveAll(List.of(answer));

        answerService = new AnswerService(answerRepository);
    }

    @Test
    void getCurrentFacultyChance() {
        var answer = new Answer();
        answer.setGryffindor(1.01);
        answer.setHufflepuff(1.02);
        answer.setRavenclaw(1.03);
        answer.setSlytherin(1.04);

        double gryffindorChance = answerService.getCurrentFacultyChance(Faculty.GRYFFINDOR, answer);
        double hufflepuffChance = answerService.getCurrentFacultyChance(Faculty.HUFFLEPUFF, answer);
        double ravenclawChance = answerService.getCurrentFacultyChance(Faculty.RAVENCLAW, answer);
        double slytherinChance = answerService.getCurrentFacultyChance(Faculty.SLYTHERIN, answer);

        assertEquals(1.01, gryffindorChance);
        assertEquals(1.02, hufflepuffChance);
        assertEquals(1.03, ravenclawChance);
        assertEquals(1.04, slytherinChance);
    }

    @Test
    void calculateFinalFaculty() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Faculty.GRYFFINDOR.getName())).thenReturn(1.01d);
        when(session.getAttribute(Faculty.HUFFLEPUFF.getName())).thenReturn(1.02d);
        when(session.getAttribute(Faculty.RAVENCLAW.getName())).thenReturn(1.03d);
        when(session.getAttribute(Faculty.SLYTHERIN.getName())).thenReturn(1.04d);

        Faculty result = answerService.calculateFinalFaculty(request);
        assertEquals(Faculty.SLYTHERIN, result);
    }

    @Test
    void prepareNextQuestion() {
        var request = Mockito.mock(HttpServletRequest.class);
        var session = Mockito.mock(HttpSession.class);
        var questionService = Mockito.mock(QuestionService.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter(UserSessionAttribute.ANSWER_ID.getName())).thenReturn("1");
        when(session.getAttribute(Faculty.GRYFFINDOR.getName())).thenReturn(1.01d);
        when(session.getAttribute(Faculty.HUFFLEPUFF.getName())).thenReturn(1.02d);
        when(session.getAttribute(Faculty.RAVENCLAW.getName())).thenReturn(0.6d);
        when(session.getAttribute(Faculty.SLYTHERIN.getName())).thenReturn(0.5d);

        var nextQuestion = new Question();
        nextQuestion.setId(2);
        nextQuestion.setText("Next Question");
        nextQuestion.setAnswers(List.of());

        when(questionService.getQuestionById(2)).thenReturn(nextQuestion);

        var result = answerService.prepareNextQuestion(request, questionService);

        assertEquals(nextQuestion, result);
        verify(request).setAttribute(UserSessionAttribute.QUESTION.getName(), nextQuestion);

        if (nextQuestion.getAnswers().isEmpty()) {
            verify(request).setAttribute(eq(UserSessionAttribute.RESULT.getName()), anyString());
        }
    }
}