package edu.basejava.storage;

import edu.basejava.exception.ResumeExistStorageException;
import edu.basejava.exception.ResumeNotExistStorageException;
import edu.basejava.model.Resume;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static edu.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractStorageTest {

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String FULL_NAME_1 = "John Doe";
    protected static final String FULL_NAME_2 = "Not John Doe";
    protected static final String FULL_NAME_3 = "The John Doe";
    protected static final String UUID_NOT_EXISTING = "dummy";

    protected static final Resume resume1 = new Resume( UUID_1, FULL_NAME_1 );
    protected static final Resume resume2 = new Resume( UUID_2, FULL_NAME_2 );
    protected static final Resume resume3 = new Resume( UUID_3, FULL_NAME_3 );

    protected Storage storage;

    protected abstract Storage createInitialStorage();

    protected abstract int getInitialStorageSize();

    protected abstract Storage createInitialStorage( Resume... resumes );

    protected abstract Resume[] getInitiallySavedResumes();

    @BeforeEach
    public void setUp() {
        storage = createInitialStorage();
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

    @Test
    void save() {
        final Resume savingResume = new Resume( UUID_NOT_EXISTING, StringUtils.EMPTY );
        storage.save( savingResume );
        assertGet( savingResume );
        assertSize( getInitialStorageSize() + 1 );
    }

    @Test
    void saveExisting() {
        Assertions.assertThrows( ResumeExistStorageException.class, () -> storage.save( resume1 ) );
    }

    @Test
    void update() {
        assertUpdate( UUID_1 );
        assertUpdate( UUID_2 );
        assertUpdate( UUID_3 );
    }

    @Test
    void updateNotExisting() {
        Resume notExisting = new Resume( UUID_NOT_EXISTING, StringUtils.EMPTY );
        Assertions.assertThrows( ResumeNotExistStorageException.class, () -> storage.update( notExisting ) );
    }

    @Test
    void delete() {
        assertDelete( getInitialStorageSize(), UUID_1 );
        storage = createInitialStorage();

        assertDelete( getInitialStorageSize(), UUID_2 );
        storage = createInitialStorage();

        assertDelete( getInitialStorageSize(), UUID_3 );
    }

    @Test
    void deleteNotExisting() {
        Assertions.assertThrows( ResumeNotExistStorageException.class, () -> storage.delete( UUID_NOT_EXISTING ) );
    }

    @Test
    void clear() {
        Resume[] internalStorage = getInitiallySavedResumes();
        storage = createInitialStorage( internalStorage );

        assertSize( getInitialStorageSize() );
        storage.clear();
        assertSize( 0 );
        Assertions.assertIterableEquals( Collections.emptyList(), storage.getAllSorted() );
    }

    @Test
    void getAll() {
        List<Resume> expected = Arrays.asList( getInitiallySavedResumes() );
        expected.sort( AbstractStorage.RESUME_COMPARATOR );
        Assertions.assertIterableEquals( expected, storage.getAllSorted() );
    }

    @Test
    void getAllEmptyStorage() {
        storage = createInitialStorage( new Resume[0] );
        Assertions.assertIterableEquals( Collections.emptyList(), storage.getAllSorted() );
    }

    @Test
    void size() {
        storage = createInitialStorage( getInitiallySavedResumes() );
        assertSize( getInitialStorageSize() );
    }

    @Test
    void sizeEmptyStorage() {
        storage = createInitialStorage( new Resume[STORAGE_LIMIT] );
        assertSize( 0 );
    }

    protected void assertGet( Resume resume ) {
        Assertions.assertEquals( resume, storage.get( resume.getUuid() ) );
    }

    protected void assertUpdate( String uuid ) {
        Resume updated = new Resume( uuid, StringUtils.EMPTY );
        storage.update( updated );
        Assertions.assertSame( updated, storage.get( uuid ) );
    }

    protected void assertDelete( int size, String uuid ) {
        Assertions.assertNotNull( storage.get( uuid ) );
        storage.delete( uuid );

        Assertions.assertEquals( size - 1, storage.size() );
        Assertions.assertThrows( ResumeNotExistStorageException.class, () -> storage.get( uuid ) );
    }

    protected void assertSize( int size ) {
        Assertions.assertEquals( size, storage.size() );
    }
}
