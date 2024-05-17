package edu.basejava.model;

import lombok.Getter;

@Getter
public enum ContactType {
    MOBILE_PHONE( "Моб. тел" ),
    SKYPE( "Skype" ),
    EMAIL( "e-mail" ),
    LINKEDIN( "Профиль LinkedIn" ),
    GITHUB( "Профиль GitHub" ),
    STACKOVERFLOW( "Профиль StackOverflow" ),
    HOMEPAGE( "Домашняя страница" );

    private final String title;

    ContactType( String title ) {
        this.title = title;
    }
}
