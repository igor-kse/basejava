package edu.basejava.exception;

public class ResumeNotExistStorageException extends StorageException {

    private static final String MESSAGE = "Resume(%s) does not exist";

    public ResumeNotExistStorageException( String uuid ) {
        super( String.format( MESSAGE, uuid ) );
    }

    public ResumeNotExistStorageException( String uuid, Throwable cause ) {
        super( String.format( MESSAGE, uuid ), cause );
    }
}
