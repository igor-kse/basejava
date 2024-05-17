import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class MainResume {
    public static void main( String[] args ) {
        Logger LOG = LoggerFactory.getLogger( MainResume.class );
        LOG.debug( ResumeTestData.getFilledResume( UUID.randomUUID().toString(), "Григорий Кислин" ).toString() );
    }
}
