package edu.basejava.model;

import lombok.Getter;

@Getter
public enum SectionType {
    PERSONAL( "Личные качества" ),
    OBJECTIVE( "Позиция" ),
    ACHIEVEMENT( "Достижения" ),
    QUALIFICATION( "Квалификация" ),
    EXPERIENCE( "Опыт работы" ),
    EDUCATION( "Образование" );

    private final String title;

    SectionType( String title ) {
        this.title = title;
    }
}
