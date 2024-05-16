package edu.basejava.storage;

import edu.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super( new Resume[]{resume1, resume2, resume3} );
    }

    @Override
    protected SortedArrayStorage createInitialStorage() {
        return new SortedArrayStorage( Arrays.copyOf( internalStorage, internalStorage.length ) );
    }

    @Override
    protected SortedArrayStorage createInitialStorage( Resume[] internalStorage ) {
        Resume[] resumes = new Resume[AbstractArrayStorage.STORAGE_LIMIT];
        System.arraycopy( internalStorage, 0, resumes, 0, internalStorage.length );
        return new SortedArrayStorage( resumes );
    }

    @Override
    protected Resume[] getInitiallySavedResumes() {
        return Arrays.copyOf( internalStorage, getInitialStorageSize() );
    }

    @Override
    protected int getInitialStorageSize() {
        return INITIAL_SIZE;
    }

    @Override
    protected Resume[] createFilledInternalStorage() {
        Resume[] internalStorage = super.createFilledInternalStorage();
        Arrays.sort( internalStorage, Comparator.comparing( Resume::getUuid ) );
        return internalStorage;
    }
}
