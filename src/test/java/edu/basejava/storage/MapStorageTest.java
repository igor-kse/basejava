package edu.basejava.storage;

import edu.basejava.model.Resume;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MapStorageTest extends AbstractStorageTest {

    private static final Map<String, Resume> internalStorage = new TreeMap<>();

    public MapStorageTest() {
        internalStorage.put( UUID_1, resume1 );
        internalStorage.put( UUID_2, resume2 );
        internalStorage.put( UUID_3, resume3 );
    }

    @Override
    protected Storage createInitialStorage() {
        return new MapStorage( new TreeMap<>( internalStorage ) );
    }

    @Override
    protected int getInitialStorageSize() {
        return internalStorage.size();
    }

    @Override
    protected Storage createInitialStorage( Resume... resumes ) {
        Map<String, Resume> resumesMap =
                Arrays.stream( resumes )
                        .filter( Objects::nonNull )
                        .collect( Collectors.toMap( Resume::getUuid, resume -> resume ) );

        return new MapStorage( new TreeMap<>( resumesMap ) );
    }

    @Override
    protected Resume[] getInitiallySavedResumes() {
        return internalStorage.values().toArray( new Resume[0] );
    }
}
