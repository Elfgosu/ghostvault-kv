package ghostvaultkv.internal;

import java.nio.charset.StandardCharsets;

/**
 * Wrapper class of Ghostvault KV JNI.
 *
 * @author thilo
 *
 * @version 21.03.26
 */
public class NativeGhostvaultKVWrapper {
    private final NativeGhostvaultKV nativeStore;
    private final long handle;

    public NativeGhostvaultKVWrapper(String path) {
        this.nativeStore = new NativeGhostvaultKV();
        this.handle = nativeStore.open(path);
    }
    public void put(String key, String value) {
        validateKey(key);
        validateValue(value);

        nativeStore.put(handle,
                key.getBytes(StandardCharsets.UTF_8),
                value.getBytes(StandardCharsets.UTF_8));
    }

    public String get(String key) {
        validateKey(key);

        byte[] result = nativeStore.get(handle,
                key.getBytes(StandardCharsets.UTF_8));

        if (result == null) {
            throw new RuntimeException("Key not found: " + key);
        }

        return new String(result, StandardCharsets.UTF_8);
    }

    public void delete(String key) {
        validateKey(key);

        nativeStore.delete(handle,
                key.getBytes(StandardCharsets.UTF_8));
    }

    public void close() {
        nativeStore.close(handle);
    }

    private void validateKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be empty");
        }
    }

    private void validateValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null");
        }
    }
}
