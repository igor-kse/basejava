package edu.basejava.storage;

import edu.basejava.exception.StorageException;
import edu.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    // TODO load the limit from properties
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage;

    protected int size = 0;

    // for test purposes only
    public AbstractArrayStorage() {
        storage = new Resume[STORAGE_LIMIT];
    }

    protected AbstractArrayStorage( Resume[] storage ) {
        this.storage = storage;
        this.size = Arrays.stream( storage )
                .filter( Objects::nonNull )
                .toList()
                .size();
    }

    @Override
    protected Resume doGet( Integer index ) {
        return storage[index];
    }

    @Override
    protected void doSave( Resume resume, Integer index ) {
        if ( size >= STORAGE_LIMIT ) {
            throw new StorageException( "Storage overflow" );
        }
        setResume( resume, index );
        size++;
    }

    @Override
    protected void doUpdate( Resume resume, Integer index ) {
        storage[index] = resume;
    }

    @Override
    protected void doDelete( Integer index ) {
        removeResume( index );
        storage[--size] = null;
    }

    @Override
    public void clear() {
        Arrays.fill( storage, 0, size, null );
        size = 0;
    }

    @Override
    public List<Resume> getAll() {
        return Arrays.asList( Arrays.copyOf( storage, size ) );
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist( Integer index ) {
        return index >= 0;
    }

    @Override
    protected abstract Integer getSearchKey( String uuid );

    protected abstract void setResume( Resume resume, int index );

    protected abstract void removeResume( int index );
}
