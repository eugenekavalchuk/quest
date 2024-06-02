package com.javarush.kavalchuk.quest.repository;

import com.javarush.kavalchuk.quest.model.Answer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerRepository {
    private final Map<Integer, Answer> answers = new HashMap<>();

    public void saveAll(List<Answer> answers) {
        answers.forEach(answer -> this.answers.put(answer.getId(), answer));
    }

    public Answer findById(Integer id) {
        return this.answers.get(id);
    }
}
