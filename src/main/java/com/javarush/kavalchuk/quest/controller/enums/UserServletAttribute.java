package com.javarush.kavalchuk.quest.controller.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserServletAttribute {
    QUESTION_SERVICE("questionService"),
    ANSWER_SERVICE("answerService"),
    QUESTION_DATA_LOADER("questionDataLoader"),
    ANSWER_DATA_LOADER("answerDataLoader");

    private final String name;
}
