package edu.basejava.storage;

import edu.basejava.exception.StorageException;
import edu.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage( File directory ) {
        Objects.requireNonNull( directory, "directory must not be null" );

        if ( !directory.isDirectory() ) {
            throw new IllegalArgumentException( directory.getAbsolutePath() + " is not directory" );
        }
        if ( !directory.canRead() || !directory.canWrite() ) {
            throw new IllegalArgumentException( directory.getAbsolutePath() + " is not readable/writable" );
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        List<File> files = getCheckedListFiles();
        files.forEach( this::doDelete );
    }

    @Override
    public int size() {
        return getCheckedListFiles().size();
    }

    @Override
    protected File getSearchKey( String uuid ) {
        return new File( directory, uuid );
    }

    @Override
    protected boolean isExist( File file ) {
        return file.exists();
    }

    @Override
    protected void doUpdate( Resume resume, File file ) {
        try {
            doWrite( resume, file );
        } catch ( IOException e ) {
            throw new StorageException( "IO error: " + file.getName(), e );
        }
    }

    @Override
    protected void doSave( Resume resume, File file ) {
        try {
            if ( !file.createNewFile() ) {
                throw new StorageException( "I/O error, file " + file.getAbsolutePath() + " is not created");
            }
            doWrite( resume, file );
        } catch ( IOException e ) {
            throw new StorageException( "IO error: " + file.getName(), e );
        }
    }

    @Override
    protected Resume doGet( File file ) {
        try {
            return doRead( file );
        } catch ( IOException e ) {
            throw new StorageException( "IO error: " + file.getName(), e );
        }
    }

    @Override
    protected void doDelete( File file ) {
        if ( !file.delete() ) {
            throw new StorageException( "I/O error while deleting " + file.getAbsolutePath() );
        }
    }

    @Override
    protected List<Resume> getAll() {
        return getCheckedListFiles()
                .stream()
                .map( this::doGet )
                .toList();
    }

    private List<File> getCheckedListFiles() {
        File[] files = directory.listFiles();
        if ( files == null ) {
            throw new StorageException( "I/O error while reading " + directory.getAbsolutePath() );
        }
        return Arrays.asList( files );
    }

    protected abstract void doWrite( Resume r, File file ) throws IOException;

    protected abstract Resume doRead( File file ) throws IOException;
}
