package edu.basejava.storage;

import edu.basejava.exception.ResumeExistStorageException;
import edu.basejava.exception.ResumeNotExistStorageException;
import edu.basejava.exception.StorageException;
import edu.basejava.model.Resume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

public abstract class AbstractArrayStorage implements Storage {
    private static final String RESUME_NOT_EXIST = "Resume (%s) doesn't exist";
    private static final String RESUME_ALREADY_EXIST = "Resume (%s) already exists";

    // TODO load the limit from properties
    protected static final int STORAGE_LIMIT = 10000;

    protected final Logger LOG = LoggerFactory.getLogger( AbstractArrayStorage.class );
    protected final Resume[] storage;
    protected int size = 0;

    public AbstractArrayStorage() {
        storage = new Resume[STORAGE_LIMIT];
    }

    // for test purposes only
    protected AbstractArrayStorage( Resume[] storage ) {
        this.storage = storage;
        this.size = Arrays.stream( storage )
                .filter( Objects::nonNull )
                .toList()
                .size();
    }

    @Override
    public Resume get( String uuid ) {
        LOG.debug( uuid );

        int searchKey = getSearchKey( uuid );
        if ( !isExist( searchKey ) ) {
            throw new ResumeNotExistStorageException( uuid );
        }
        return storage[searchKey];
    }

    @Override
    public void save( Resume resume ) {
        LOG.debug( "Resume to save:\n{}", resume );

        int searchKey = getSearchKey( resume.getUuid() );
        if ( size >= STORAGE_LIMIT ) {
            throw new StorageException( format( "Storage overflow. Cannot save resume\n%s", resume ) );
        } else if ( isExist( searchKey ) ) {
            throw new ResumeExistStorageException( format( RESUME_ALREADY_EXIST, resume.getUuid() ) );
        }
        setResume( resume, searchKey );
        size++;
    }

    @Override
    public void update( Resume resume ) {
        LOG.debug( "Resume to update by:\n{}", resume );

        int searchKey = getSearchKey( resume.getUuid() );
        if ( !isExist( searchKey ) ) {
            throw new ResumeNotExistStorageException( format( RESUME_ALREADY_EXIST, resume.getUuid() ) );
        }
        storage[searchKey] = resume;
    }

    @Override
    public void delete( String uuid ) {
        LOG.debug( uuid );

        int searchKey = getSearchKey( uuid );
        if ( !isExist( searchKey ) ) {
            throw new ResumeNotExistStorageException( format( RESUME_NOT_EXIST, uuid ) );
        }
        removeResume( searchKey );
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

    protected boolean isExist( int searchKey ) {
        return searchKey >= 0;
    }

    protected abstract int getSearchKey( String uuid );

    protected abstract void setResume( Resume resume, int index );

    protected abstract void removeResume( int index );
}
