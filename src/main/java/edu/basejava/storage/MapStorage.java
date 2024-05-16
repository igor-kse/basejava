package edu.basejava.storage;

import edu.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {

    private final Map<String, Resume> storage;

    public MapStorage() {
        storage = new HashMap<>();
    }

    // for test purposes only
    protected MapStorage(Map<String, Resume> internalStorage) {
        storage = internalStorage;
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

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>( storage.values() );
    }

    @Override
    public int size() {
        return storage.size();
    }
}
