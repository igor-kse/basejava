package edu.basejava.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
@EqualsAndHashCode( callSuper = true )
public class ListSection extends BaseSection {

    private List<String> list;

    @Override
    public String toString() {
        String string12 = StringUtils.repeat( " ", 12 );
        String string16 = StringUtils.repeat( " ", 16 );
        return "ListSection{\n" +
                string16 + String.join( "\n" + string16, list ) + "\n" +
                string12 + '}';
    }
}
