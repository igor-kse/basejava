package edu.basejava.storage;

import edu.basejava.model.Resume;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;

@NoArgsConstructor
public class SortedArrayStorage extends AbstractArrayStorage {

    // for test purposes only
    protected SortedArrayStorage( Resume[] storage ) {
        super( storage );
    }

    @Override
    protected int getSearchKey( String uuid ) {
        Resume searchKey = new Resume( uuid );
        return Arrays.binarySearch( storage, 0, size, searchKey, Comparator.comparing( Resume::getUuid ) );
    }

    @Override
    protected void setResume( Resume resume, int index ) {
        int insertionIndex = -index - 1;
        System.arraycopy( storage, insertionIndex, storage, insertionIndex + 1, size - insertionIndex );
        storage[insertionIndex] = resume;
    }

    @Override
    protected void removeResume( int index ) {
        int moveCount = size - index - 1;
        if ( moveCount > 0 ) {
            System.arraycopy( storage, index + 1, storage, index, moveCount );
        }
    }
}
