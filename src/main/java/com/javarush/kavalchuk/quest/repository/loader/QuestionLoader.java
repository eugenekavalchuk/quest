package com.javarush.kavalchuk.quest.repository.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.javarush.kavalchuk.quest.model.Question;
import com.javarush.kavalchuk.quest.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class QuestionLoader implements Loader {
    private static final String QUESTIONS_FILE = "questions.yaml";

    private final QuestionRepository questionRepository;

    @Override
    public void load() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(QUESTIONS_FILE);

        if (inputStream == null) {
            throw new RuntimeException(QUESTIONS_FILE + " not found");
        }

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Map<String, List<Question>> data;

        try {
            data = mapper.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Question> questions = data.get("questions");
        questionRepository.saveAll(questions);
    }
}
