package edu.basejava.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
@EqualsAndHashCode( callSuper = true )
public class CompanySection extends BaseSection {

    private List<Company> companies;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        companies.forEach( builder::append );

        return "CompanySection={" + builder +
                "\n" + StringUtils.repeat( " ", 12 ) + "}";
    }
}
