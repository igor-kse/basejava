package edu.basejava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String name;

    private String website;

    private List<Period> periods;

    @Override
    public String toString() {
        String string16 = StringUtils.repeat( " ", 16 );
        String string20 = StringUtils.repeat( " ", 20 );

        StringBuilder builder = new StringBuilder();
        periods.forEach( builder::append );
        return "\n" + string16 + "Company={\n" +
                string20 + "name='" + name + '\'' + ", website='" + website + '\'' + ",\n" +
                string20 + "periods={\n" + builder +
                string20 + '}' + "\n" +
                string16 + "}";

    }
}
