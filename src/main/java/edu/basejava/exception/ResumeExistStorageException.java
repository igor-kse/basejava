package edu.basejava.exception;

public class ResumeExistStorageException extends StorageException {

    private static final String MESSAGE = "Resume(%s) already exists";

    public ResumeExistStorageException( String uuid ) {
        super( String.format( MESSAGE, uuid ) );
    }

    public ResumeExistStorageException( String uuid, Throwable cause ) {
        super( String.format( MESSAGE, uuid ), cause );
    }
}
