package com.javarush.kavalchuk.quest.model;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private Integer id;
    private String text;
    private List<Answer> answers;
}
