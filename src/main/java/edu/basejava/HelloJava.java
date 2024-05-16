package edu.basejava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJava {

    private static final Logger LOG = LoggerFactory.getLogger( HelloJava.class );

    public static void main( String[] args ) {
        LOG.debug( "Hello World" );
    }
}
