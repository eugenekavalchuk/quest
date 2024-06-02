package com.javarush.kavalchuk.quest.repository.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.javarush.kavalchuk.quest.model.Answer;
import com.javarush.kavalchuk.quest.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class AnswerLoader implements Loader {
    private static final String ANSWERS_FILE = "answers.yaml";

    private final AnswerRepository answerRepository;

    @Override
    public void load() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ANSWERS_FILE);

        if (inputStream == null) {
            throw new RuntimeException(ANSWERS_FILE + " not found");
        }

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Map<String, List<Answer>> data;

        try {
            data = mapper.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Answer> answers = data.get("answers");
        answerRepository.saveAll(answers);
    }
}
