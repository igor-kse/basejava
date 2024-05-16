package edu.basejava.storage;

import edu.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage<SK> extends AbstractStorage<SK> {

    protected final Map<String, Resume> storage;

    public AbstractMapStorage() {
        storage = new HashMap<>();
    }

    // for test purposes only
    protected AbstractMapStorage( Map<String, Resume> storage ) {
        this.storage = storage;
    }

    @Override
    protected final List<Resume> getAll() {
        return new ArrayList<>( storage.values() );
    }

    @Override
    public final void clear() {
        storage.clear();
    }

    @Override
    public final int size() {
        return storage.size();
    }
}
