package ghostvaultkv.internal;

/**
 * JNI Bridge to connect Ghostvault Storage API to Storage Engine.
 *
 * @author thilo
 *
 * @version 21.03.26
 */
class NativeGhostvaultKV {

    static {
        System.loadLibrary("ghostvaultkvstore");
    }

    native long open(String path);
    native void close(long handle);

    native byte[] get(long handle, byte[] key);
    native void put(long handle, byte[] key, byte[] value);
    native void delete(long handle, byte[] key);
}