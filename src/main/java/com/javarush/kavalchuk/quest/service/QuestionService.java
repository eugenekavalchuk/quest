package com.javarush.kavalchuk.quest.service;

import com.javarush.kavalchuk.quest.model.Question;
import com.javarush.kavalchuk.quest.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question getFirstQuestion() {
        return questionRepository.findFirst();
    }

    public Question getQuestionById(Integer id) {
        return questionRepository.findById(id);
    }
}
