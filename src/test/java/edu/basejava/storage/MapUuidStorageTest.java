package edu.basejava.storage;

import edu.basejava.model.Resume;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MapUuidStorageTest extends AbstractMapStorageTest {

    @Override
    protected Storage createInitialStorage() {
        return new MapUuidStorage( getInternalStorage() );
    }

    @Override
    protected Storage createInitialStorage( Resume... resumes ) {
        Map<String, Resume> resumesMap =
                Arrays.stream( resumes )
                        .filter( Objects::nonNull )
                        .collect( Collectors.toMap( Resume::getUuid, resume -> resume ) );

        return new MapUuidStorage( new TreeMap<>( resumesMap ) );
    }
}
