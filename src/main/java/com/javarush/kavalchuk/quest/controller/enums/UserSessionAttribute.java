package com.javarush.kavalchuk.quest.controller.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserSessionAttribute {
    USERNAME("userName"),
    QUESTION("question"),
    RESULT("result"),
    ANSWER_ID("answerId");

    private final String name;
}
