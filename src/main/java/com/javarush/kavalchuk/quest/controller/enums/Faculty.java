package com.javarush.kavalchuk.quest.controller.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Faculty {
    RAVENCLAW("ravenclaw"),
    HUFFLEPUFF("hufflepuff"),
    SLYTHERIN("slytherin"),
    GRYFFINDOR("gryffindor");

    private final String name;
}
