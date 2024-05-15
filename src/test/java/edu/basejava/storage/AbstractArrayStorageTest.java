package edu.basejava.storage;

import edu.basejava.exception.ResumeExistStorageException;
import edu.basejava.exception.ResumeNotExistStorageException;
import edu.basejava.exception.StorageException;
import edu.basejava.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static edu.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";
    protected static final String UUID_NOT_EXISTING = "dummy";

    protected static final Resume resume1 = new Resume( UUID_1 );
    protected static final Resume resume2 = new Resume( UUID_2 );
    protected static final Resume resume3 = new Resume( UUID_3 );
    protected static final Resume resumeNotSaved = new Resume( UUID_4 );

    protected Storage storage;
    protected int initialSize;

    @BeforeEach
    public void setUp() {
        storage = createInitialArrayBasedStorage();
        initialSize = getInitialStorageSize();
    }

    private Storage createFilledStorage() {
        Resume[] internalStorage = createFilledInternalStorage();
        return createInitialArrayBasedStorage( internalStorage );
    }

    @Test
    void get() {
        assertGet( resume1 );
        assertGet( resume2 );
        assertGet( resume3 );
    }

    @Test
    void getNotExisting() {
        Assertions.assertThrows( ResumeNotExistStorageException.class, () -> storage.get( UUID_NOT_EXISTING ) );
    }

    private void assertGet( Resume resume ) {
        Assertions.assertEquals( resume, storage.get( resume.getUuid() ) );
    }

    @Test
    void save() {
        storage.save( resumeNotSaved );
        assertGet( resumeNotSaved );
        Assertions.assertEquals( storage.size(), initialSize + 1 );
    }

    @Test
    void saveExisting() {
        Assertions.assertThrows( ResumeExistStorageException.class, () -> storage.save( resume1 ) );
    }

    @Test
    void saveOverflow() {
        storage = createFilledStorage();
        Assertions.assertThrows( StorageException.class, () -> storage.save( resumeNotSaved ) );
    }

    @Test
    void update() {
        assertUpdate( UUID_1 );
        assertUpdate( UUID_2 );
        assertUpdate( UUID_3 );
    }

    @Test
    void updateNotExisting() {
        Resume notExisting = new Resume( UUID_NOT_EXISTING );
        Assertions.assertThrows( ResumeNotExistStorageException.class, () -> storage.update( notExisting ) );
    }

    private void assertUpdate( String uuid ) {
        Resume updated = new Resume( uuid );
        storage.update( updated );
        Assertions.assertSame( updated, storage.get( uuid ) );
    }

    @Test
    void delete() {
        assertDelete( initialSize, UUID_1 );
        storage = createInitialArrayBasedStorage();

        assertDelete( initialSize, UUID_2 );
        storage = createInitialArrayBasedStorage();

        assertDelete( initialSize, UUID_3 );
    }

    @Test
    void deleteLastFromFilled() {
        Resume[] internalStorage = createFilledInternalStorage();
        String uuidLast = internalStorage[STORAGE_LIMIT - 1].getUuid();

        storage = createInitialArrayBasedStorage( internalStorage );
        assertDelete( STORAGE_LIMIT, uuidLast );
    }

    private void assertDelete( int size, String uuid ) {
        Assertions.assertNotNull( storage.get( uuid ) );
        storage.delete( uuid );

        Assertions.assertEquals( size - 1, storage.size() );
        Assertions.assertThrows( ResumeNotExistStorageException.class, () -> storage.get( uuid ) );
    }

    @Test
    void deleteNotExisting() {
        Assertions.assertThrows( ResumeNotExistStorageException.class, () -> storage.delete( UUID_NOT_EXISTING ) );
    }

    @Test
    void clear() {
        Resume[] internalStorage = getInitialInternalStorage();
        storage = createInitialArrayBasedStorage( internalStorage );

        Assertions.assertEquals( initialSize, storage.size() );
        storage.clear();
        Assertions.assertEquals( 0, storage.size() );
        Assertions.assertArrayEquals( new Resume[STORAGE_LIMIT], internalStorage );
    }

    @Test
    void getAll() {
        Resume[] internalStorage = getInitialInternalStorage();
        storage = createInitialArrayBasedStorage( internalStorage );
        List<Resume> expected = Arrays.stream( internalStorage )
                .filter( Objects::nonNull )
                .toList();
        Assertions.assertIterableEquals( expected, storage.getAll() );
    }

    @Test
    void getAllEmptyStorage() {
        storage = createInitialArrayBasedStorage( new Resume[STORAGE_LIMIT] );
        Assertions.assertIterableEquals( Collections.emptyList(), storage.getAll() );
    }

    @Test
    void size() {
        storage = createInitialArrayBasedStorage( getInitialInternalStorage() );
        Assertions.assertEquals( getInitialStorageSize(), storage.size() );
    }

    @Test
    void sizeEmptyStorage() {
        storage = createInitialArrayBasedStorage( new Resume[STORAGE_LIMIT] );
        Assertions.assertEquals( 0, storage.size() );
    }

    @Test
    void sizeFullStorage() {
        storage = createFilledStorage();
        Assertions.assertEquals( STORAGE_LIMIT, storage.size() );
    }

    protected abstract Storage createInitialArrayBasedStorage();

    protected abstract Storage createInitialArrayBasedStorage( Resume[] internalStorage );

    protected abstract Resume[] getInitialInternalStorage();

    protected abstract Resume[] createFilledInternalStorage();

    protected abstract int getInitialStorageSize();
}