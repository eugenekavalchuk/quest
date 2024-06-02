package com.javarush.kavalchuk.quest.controller;

import com.javarush.kavalchuk.quest.controller.enums.UserServletAttribute;
import com.javarush.kavalchuk.quest.repository.*;
import com.javarush.kavalchuk.quest.repository.loader.AnswerLoader;
import com.javarush.kavalchuk.quest.repository.loader.Loader;
import com.javarush.kavalchuk.quest.repository.loader.QuestionLoader;
import com.javarush.kavalchuk.quest.service.AnswerService;
import com.javarush.kavalchuk.quest.service.QuestionService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        var questionRepository = new QuestionRepository();
        var answerRepository = new AnswerRepository();
        var questionService = new QuestionService(questionRepository);
        var answerService = new AnswerService(answerRepository);

        Loader questionLoader = new QuestionLoader(questionRepository);
        Loader answerLoader = new AnswerLoader(answerRepository);
        questionLoader.load();
        answerLoader.load();

        sce.getServletContext().setAttribute(UserServletAttribute.QUESTION_SERVICE.getName(), questionService);
        sce.getServletContext().setAttribute(UserServletAttribute.ANSWER_SERVICE.getName(), answerService);
        sce.getServletContext().setAttribute(UserServletAttribute.QUESTION_DATA_LOADER.getName(), questionLoader);
        sce.getServletContext().setAttribute(UserServletAttribute.ANSWER_DATA_LOADER.getName(), answerLoader);
    }
}
