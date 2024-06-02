package com.javarush.kavalchuk.quest.service;

import com.javarush.kavalchuk.quest.controller.enums.Faculty;
import com.javarush.kavalchuk.quest.controller.enums.UserSessionAttribute;
import com.javarush.kavalchuk.quest.model.Answer;
import com.javarush.kavalchuk.quest.model.Question;
import com.javarush.kavalchuk.quest.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Objects;

@RequiredArgsConstructor
public class AnswerService {
    public static final double MIN_RANDOM = 1.00d;
    public static final double MAX_RANDOM = 1.03d;
    public static final double DEFAULT_VALUE = 1.00d;

    private final AnswerRepository answerRepository;

    public Answer getAnswerById(Integer answerId) {
        return answerRepository.findById(answerId);
    }

    public Question prepareNextQuestion(HttpServletRequest request, QuestionService questionService) {
        var answerId = Integer.parseInt(request.getParameter(UserSessionAttribute.ANSWER_ID.getName()));
        var answer = getAnswerById(answerId);
        var nextQuestion = questionService.getQuestionById(answer.getNextQuestionId());
        var answers = nextQuestion.getAnswers();
        Collections.shuffle(answers);
        nextQuestion.setAnswers(answers);

        request.setAttribute(UserSessionAttribute.QUESTION.getName(), nextQuestion);
        updateUserSession(request);

        if (nextQuestion.getAnswers().isEmpty()) {
            var faculty = calculateFinalFaculty(request);
            request.setAttribute(UserSessionAttribute.RESULT.getName(), faculty.getName());
        }

        return  nextQuestion;
    }

    private void updateUserSession(HttpServletRequest request) {
        var answerId = Integer.parseInt(request.getParameter(UserSessionAttribute.ANSWER_ID.getName()));
        var answer = getAnswerById(answerId);
        var session = request.getSession();

        for (Faculty faculty : Faculty.values()) {
            var currentChance = getCurrentFacultyChance(faculty, answer);

            session.setAttribute(
                    faculty.getName(),
                    Objects.isNull(session.getAttribute(faculty.getName()))
                            ? DEFAULT_VALUE * currentChance
                            : (double) session.getAttribute(faculty.getName()) * currentChance
            );
        }
    }

    public double getCurrentFacultyChance(Faculty faculty, Answer answer) {
        return switch (faculty) {
            case GRYFFINDOR -> answer.getGryffindor();
            case HUFFLEPUFF -> answer.getHufflepuff();
            case RAVENCLAW -> answer.getRavenclaw();
            case SLYTHERIN -> answer.getSlytherin();
        };
    }

    public Faculty calculateFinalFaculty(HttpServletRequest request) {
        var randomFactor = MIN_RANDOM + (MAX_RANDOM - MIN_RANDOM) * Math.random();
        var maxValue = 0d;
        var finalFaculty = Faculty.GRYFFINDOR;

        for (Faculty faculty : Faculty.values()) {
            var chanceValue = (double) request.getSession().getAttribute(faculty.getName()) * randomFactor;
            if (chanceValue > maxValue) {
                maxValue = chanceValue;
                finalFaculty = faculty;
            }
        }

        return finalFaculty;
    }
}
