package edu.basejava.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = true )
public class TextSection extends BaseSection {

    private String text;

    @Override
    public String toString() {
        return "TextSection={ " +
                "text='" + text + '\'' + " }";
    }
}
