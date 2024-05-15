package edu.basejava.storage;


import edu.basejava.model.Resume;

import java.util.Arrays;
import java.util.UUID;

import static edu.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    private static final Resume[] internalStorage = new Resume[AbstractArrayStorage.STORAGE_LIMIT];
    private static final int INITIAL_SIZE;

    static {
        internalStorage[0] = resume1;
        internalStorage[1] = resume3;
        internalStorage[2] = resume2;
        INITIAL_SIZE = 3;
    }

    @Override
    protected ArrayStorage createInitialArrayBasedStorage() {
        return new ArrayStorage( Arrays.copyOf( internalStorage, internalStorage.length ) );
    }

    @Override
    protected ArrayStorage createInitialArrayBasedStorage( Resume[] internalStorage ) {
        return new ArrayStorage( internalStorage );
    }

    @Override
    protected Resume[] getInitialInternalStorage() {
        return Arrays.copyOf( internalStorage, AbstractArrayStorage.STORAGE_LIMIT );
    }

    @Override
    protected int getInitialStorageSize() {
        return INITIAL_SIZE;
    }

    @Override
    protected Resume[] createFilledInternalStorage() {
        Resume[] internalStorage = new Resume[STORAGE_LIMIT];
        for ( int i = 0; i < STORAGE_LIMIT; i++ ) {
            Resume resume = new Resume();
            resume.setUuid( UUID.randomUUID().toString() );
            internalStorage[i] = resume;
        }
        return internalStorage;
    }
}
