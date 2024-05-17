package edu.basejava.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Resume {

    private String uuid;

    private String fullName;

    private Map<ContactType, String> contacts;

    private Map<SectionType, BaseSection> sections;

    public Resume( String fullName ) {
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
    }

    // for test purposes only
    public Resume( String uuid, String fullName ) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        String space4 = StringUtils.repeat( " ", 4 );
        String space8 = StringUtils.repeat( " ", 8 );
        String space12 = StringUtils.repeat( " ", 12 );

        StringBuilder builder = new StringBuilder();
        builder.append( "\nResume={\n" )
                .append( space4 ).append( "uuid='" ).append( uuid ).append( "', fullName='" ).append( fullName )
                .append( "',\n\n" )
                .append( space4 ).append( "contacts={\n" )
                .append( space8 ).append( String.join( "\n        ", contacts.values() ) )
                .append( "\n" ).append( space4 ).append( "}\n\n" );

        builder.append( space4 ).append( "sections={" );
        sections.forEach( ( key, section ) -> builder.append( "\n" ).append( space8 ).append( key ).append( "={\n" )
                .append( space12 ).append( section )
                .append( space8 ).append( "\n" ).append( space8 ).append( "}\n" ) );
        builder.append( space4 ).append( "}" );

        builder.append( "\n" ).append( "}" );
        return builder.toString();
    }
}
