import edu.basejava.model.Resume;
import edu.basejava.storage.ArrayStorage;
import edu.basejava.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainArray {
    private final static Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main( String[] args ) throws IOException {
        try ( BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) ) ) {
            Resume r;
            while ( true ) {
                System.out.print( "Type a command: [list | size | save uuid | delete uuid | get uuid | clear | exit]: " );
                String[] params = reader.readLine().trim().toLowerCase().split( " " );
                if ( params.length < 1 || params.length > 2 ) {
                    System.out.println( "Wrong command" );
                    continue;
                }

                String uuid = null;
                if ( params.length == 2 ) {
                    uuid = params[1].intern();
                }

                switch ( params[0] ) {
                    case "list" -> printAll();
                    case "size" -> System.out.println( ARRAY_STORAGE.size() );
                    case "save" -> {
                        r = new Resume();
                        r.setUuid( uuid );
                        ARRAY_STORAGE.save( r );
                        printAll();
                    }
                    case "delete" -> {
                        ARRAY_STORAGE.delete( uuid );
                        printAll();
                    }
                    case "get" -> System.out.println( ARRAY_STORAGE.get( uuid ) );
                    case "clear" -> {
                        ARRAY_STORAGE.clear();
                        printAll();
                    }
                    case "exit" -> {
                        return;
                    }
                    default -> System.out.println( "Wrong command" );
                }
            }
        }
    }

    static void printAll() {
        System.out.println( "----------------------------" );

        List<Resume> all = ARRAY_STORAGE.getAllSorted();
        if ( all.size() == 0 ) {
            System.out.println( "Empty" );
            return;
        } else {
            for ( Resume r : all ) {
                System.out.println( r );
            }
        }

        System.out.println( "----------------------------" );
    }
}
