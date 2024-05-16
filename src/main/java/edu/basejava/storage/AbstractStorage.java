package edu.basejava.storage;

import edu.basejava.exception.ResumeExistStorageException;
import edu.basejava.exception.ResumeNotExistStorageException;
import edu.basejava.model.Resume;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
public abstract class AbstractStorage<SK> implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing( Resume::getFullName, Comparator.naturalOrder() ).thenComparing( Resume::getUuid );

    protected final Logger LOG = LoggerFactory.getLogger( AbstractStorage.class );

    @Override
    public Resume get( String uuid ) {
        LOG.debug( uuid );
        SK searchKey = getExistingSearchKey( uuid );
        return doGet( searchKey );
    }

    @Override
    public void save( Resume resume ) {
        LOG.debug( "Resume to save:\n{}", resume );
        SK searchKey = getNotExistingSearchKey( resume.getUuid() );
        doSave( resume, searchKey );
    }

    @Override
    public void update( Resume resume ) {
        LOG.debug( "Resume to update by:\n{}", resume );
        SK searchKey = getExistingSearchKey( resume.getUuid() );
        doUpdate( resume, searchKey );
    }

    @Override
    public void delete( String uuid ) {
        LOG.debug( uuid );
        SK searchKey = getExistingSearchKey( uuid );
        doDelete( searchKey );
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = getAll();
        resumes.sort( RESUME_COMPARATOR );
        return resumes;
    }

    protected SK getExistingSearchKey( String uuid ) {
        SK searchKey = getSearchKey( uuid );
        if ( !isExist( searchKey ) ) {
            throw new ResumeNotExistStorageException( String.format( "Resume (%s) doesn't exist", uuid ) );
        }
        return searchKey;
    }

    protected SK getNotExistingSearchKey( String uuid ) {
        SK searchKey = getSearchKey( uuid );
        if ( isExist( searchKey ) ) {
            throw new ResumeExistStorageException( String.format( "Resume (%s) already exists", uuid ) );
        }
        return searchKey;
    }

    protected abstract boolean isExist( SK searchKey );

    protected abstract SK getSearchKey( String uuid );

    protected abstract Resume doGet( SK searchKey );

    protected abstract void doSave( Resume resume, SK searchKey );

    protected abstract void doUpdate( Resume resume, SK searchKey );

    protected abstract void doDelete( SK searchKey );

    protected abstract List<Resume> getAll();
}
