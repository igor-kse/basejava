package edu.basejava.storage;

import edu.basejava.model.Resume;

import java.util.List;

public interface Storage {

    Resume get( String uuid );

    void save( Resume resume );

    void update( Resume resume );

    void delete( String uuid );

    void clear();

    List<Resume> getAll();

    int size();
}
