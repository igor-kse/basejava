package edu.basejava.storage;

import edu.basejava.model.Resume;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArrayStorage extends AbstractArrayStorage {

    // for test purposes only
    protected ArrayStorage( Resume[] storage ) {
        super( storage );
    }

    @Override
    protected int getSearchKey( String uuid ) {
        int key = -1;
        LOG.trace( "Looking for a searchKey through a cycle: " );
        for ( int i = 0; i < size; i++ ) {
            LOG.trace( "i = {}, size = {}", i, size );
            if ( uuid.equals( storage[i].getUuid() ) ) {
                key = i;
                break;
            }
        }

        LOG.debug( "return searchKey: {}", key );
        return key;
    }

    @Override
    protected void setResume( Resume resume, int index ) {
        storage[size] = resume;
    }

    @Override
    protected void removeResume( int index ) {
        storage[index] = storage[size - 1];
    }
}
