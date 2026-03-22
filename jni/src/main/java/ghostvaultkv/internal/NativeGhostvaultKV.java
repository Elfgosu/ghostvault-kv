package ghostvaultkv.internal;

/**
 * JNI Bridge to connect Ghostvault Storage API to Storage Engine.
 *
 * @author thilo
 *
 * @version 21.03.26
 */
public class NativeGhostvaultKV {

    static {
        System.loadLibrary("ghostvaultkvstore");
    }

    native public long open(String path);
    native public void close(long handle);

    native public byte[] get(long handle, byte[] key);
    native public void put(long handle, byte[] key, byte[] value);
    native public void delete(long handle, byte[] key);
}