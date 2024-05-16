package edu.basejava.storage;


import edu.basejava.model.Resume;

import java.util.Arrays;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super( new Resume[]{resume1, resume3, resume2} );
    }

    @Override
    protected ArrayStorage createInitialStorage() {
        return new ArrayStorage( Arrays.copyOf( internalStorage, internalStorage.length ) );
    }

    @Override
    protected ArrayStorage createInitialStorage( Resume[] internalStorage ) {
        Resume[] resumes = new Resume[AbstractArrayStorage.STORAGE_LIMIT];
        System.arraycopy( internalStorage, 0, resumes, 0, internalStorage.length );
        return new ArrayStorage( resumes );
    }

    @Override
    protected Resume[] getInitiallySavedResumes() {
        return Arrays.copyOf( internalStorage, getInitialStorageSize() );
    }

    @Override
    protected int getInitialStorageSize() {
        return INITIAL_SIZE;
    }
}
