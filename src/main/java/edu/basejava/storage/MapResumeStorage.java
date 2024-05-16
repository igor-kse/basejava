package edu.basejava.storage;

import edu.basejava.model.Resume;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
public class MapResumeStorage extends AbstractMapStorage<Resume> {

    private static final String SEARCH_KEY_CANNOT_BE_NULL = "searchKey must not be a null";

    // for test purposes only
    protected MapResumeStorage( Map<String, Resume> internalStorage ) {
        super( internalStorage );
    }

    @Override
    protected boolean isExist( Resume searchKey ) {
        return searchKey != null;
    }

    @Override
    protected Resume getSearchKey( String uuid ) {
        return storage.get( uuid );
    }

    @Override
    protected Resume doGet( Resume searchKey ) {
        Objects.requireNonNull( searchKey, SEARCH_KEY_CANNOT_BE_NULL );
        return searchKey;
    }

    @Override
    protected void doSave( Resume resume, Resume searchKey ) {
        Objects.requireNonNull( searchKey, SEARCH_KEY_CANNOT_BE_NULL );
        storage.put( searchKey.getUuid(), resume );
    }

    @Override
    protected void doUpdate( Resume resume, Resume searchKey ) {
        Objects.requireNonNull( searchKey, SEARCH_KEY_CANNOT_BE_NULL );
        storage.put( searchKey.getUuid(), resume );
    }

    @Override
    protected void doDelete( Resume searchKey ) {
        Objects.requireNonNull( searchKey, SEARCH_KEY_CANNOT_BE_NULL );
        storage.remove( searchKey.getUuid() );
    }
}
