package com.javarush.kavalchuk.quest.repository;

import com.javarush.kavalchuk.quest.model.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionRepository {
    private final Map<Integer, Question> questions = new HashMap<>();

    public void saveAll(List<Question> questions) {
        questions.forEach(answer -> this.questions.put(answer.getId(), answer));
    }

    public Question findFirst() {
        return this.questions.get(1);
    }

    public Question findById(Integer id) {
        return this.questions.get(id);
    }
}
