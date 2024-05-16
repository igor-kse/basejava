package edu.basejava.storage;

import edu.basejava.exception.StorageException;
import edu.basejava.model.Resume;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static edu.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected static final Resume[] internalStorage = new Resume[STORAGE_LIMIT];
    protected final int INITIAL_SIZE;

    public AbstractArrayStorageTest( Resume[] initialValues ) {
        System.arraycopy( initialValues, 0, internalStorage, 0, initialValues.length );
        INITIAL_SIZE = initialValues.length;
    }

    private Storage createFilledStorage() {
        Resume[] internalStorage = createFilledInternalStorage();
        return createInitialStorage( internalStorage );
    }

    @Test
    void deleteLastFromFilled() {
        Resume[] internalStorage = createFilledInternalStorage();
        String uuidLast = internalStorage[STORAGE_LIMIT - 1].getUuid();

        storage = createInitialStorage( internalStorage );
        assertDelete( STORAGE_LIMIT, uuidLast );
    }

    @Test
    void sizeFullStorage() {
        storage = createFilledStorage();
        Assertions.assertEquals( STORAGE_LIMIT, storage.size() );
    }

    @Test
    void saveOverflow() {
        storage = createFilledStorage();
        Assertions.assertThrows( StorageException.class, () -> storage.save( new Resume( StringUtils.EMPTY ) ) );
    }

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