package edu.basejava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Period {

    private LocalDate start;

    private LocalDate end;

    private String title;

    private String description;

    @Override
    public String toString() {
        String string24 = StringUtils.repeat( " ", 24 );
        return string24 + "Period={" +                "start=" + start +
                ", end=" + end +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}' + "\n";
    }
}
