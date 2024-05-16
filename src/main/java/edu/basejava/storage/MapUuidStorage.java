package edu.basejava.storage;

import edu.basejava.model.Resume;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class MapUuidStorage extends AbstractMapStorage<String> {

    // for test purposes only
    protected MapUuidStorage( Map<String, Resume> internalStorage ) {
        super( internalStorage );
    }

    @Override
    protected boolean isExist( String uuid ) {
        return storage.containsKey( uuid );
    }

    @Override
    protected String getSearchKey( String uuid ) {
        return uuid;
    }

    @Override
    protected Resume doGet( String uuid ) {
        return storage.get( uuid );
    }

    @Override
    protected void doSave( Resume resume, String uuid ) {
        storage.put( uuid, resume );
    }

    @Override
    protected void doUpdate( Resume resume, String uuid ) {
        storage.put( uuid, resume );
    }

    @Override
    protected void doDelete( String uuid ) {
        storage.remove( uuid );
    }
}
