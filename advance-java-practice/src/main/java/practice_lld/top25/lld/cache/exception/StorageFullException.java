package practice_lld.top25.lld.cache.exception;

public class StorageFullException extends RuntimeException{

    public StorageFullException(final String msg) {
            super(msg);
    }
}
