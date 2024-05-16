package edu.basejava.storage;

import edu.basejava.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractMapStorageTest extends AbstractStorageTest {

    private static final Map<String, Resume> internalStorage = new TreeMap<>();

    public AbstractMapStorageTest() {
        internalStorage.put( UUID_1, resume1 );
        internalStorage.put( UUID_2, resume2 );
        internalStorage.put( UUID_3, resume3 );
    }

    public Map<String, Resume> getInternalStorage() {
        return new TreeMap<>( internalStorage );
    }

    @Override
    protected int getInitialStorageSize() {
        return internalStorage.size();
    }

    @Override
    protected Resume[] getInitiallySavedResumes() {
        return internalStorage.values().toArray( new Resume[0] );
    }
}
