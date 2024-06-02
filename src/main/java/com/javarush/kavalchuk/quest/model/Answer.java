package com.javarush.kavalchuk.quest.model;

import lombok.Data;

@Data
public class Answer {
    private Integer id;
    private String text;
    private Integer nextQuestionId;
    private Double gryffindor;
    private Double hufflepuff;
    private Double ravenclaw;
    private Double slytherin;
}
